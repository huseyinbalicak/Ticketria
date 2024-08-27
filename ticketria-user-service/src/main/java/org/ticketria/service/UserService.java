package org.ticketria.service;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketria.dto.SendEmailRequest;
import org.ticketria.dto.UserUpdate;
import org.ticketria.exception.EmailActivationException;
import org.ticketria.exception.InvalidTokenException;
import org.ticketria.exception.NotFoundException;
import org.ticketria.exception.NotUniqueEmailException;
import org.ticketria.model.User;
import org.ticketria.producer.RabbitMqProducer;
import org.ticketria.repository.UserRepository;
import org.ticketria.security.PasswordUtils;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final RabbitMqProducer rabbitMqProducer;
    private final RoleService roleService;

    @Transactional(rollbackFor = MailException.class)
    public void save(User user)
    {
        try{
            user.setPassword(PasswordUtils.generateHashedPassword(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            roleService.addDefaultRole(user);
            userRepository.save(user);
            rabbitMqProducer.sendEmail(new SendEmailRequest(user.getEmail(), user.getActivationToken()));
        }
        catch (DataIntegrityViolationException exception)
        {
            throw new NotUniqueEmailException();

        }
        catch (MailException exception)
        {
            throw new EmailActivationException();

        }

    }

    public void activateUser(String token) {

        User user=userRepository.findByActivationToken(token);
        if(user==null)
        {
            throw new InvalidTokenException();
        }
        user.setActive(true);
        user.setActivationToken(null);
        userRepository.save(user);
    }

    public List<User> listUsers() {

        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User updateUser(long id, UserUpdate userUpdate) {
        User inDB = getUser(id);
        inDB.setUsername(userUpdate.username());
        return userRepository.save(inDB);
    }

    public void deleteUser(Long id) {
        User user=getUser(id);
        user.getRoles().clear();
        userRepository.delete(user);
    }
}
