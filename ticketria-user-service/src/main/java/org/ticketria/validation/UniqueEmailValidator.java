package org.ticketria.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.ticketria.model.User;
import org.ticketria.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        User user=userRepository.findByEmail(value);
        if(user!=null)
        {
            return false;
        }
        return true;
    }
}
