package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticketria.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
