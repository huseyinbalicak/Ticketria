package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketria.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}