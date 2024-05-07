package com.example.ticketsale.repository;

import com.example.ticketsale.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
