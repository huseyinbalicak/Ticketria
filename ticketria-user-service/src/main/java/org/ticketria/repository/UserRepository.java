package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketria.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    User findByActivationToken(String token);

}
