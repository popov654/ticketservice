package com.example.ticketsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<T, U> extends JpaRepository<T, U> {
    public default T getById(U id) {
        return findById(id).orElseThrow();
    }
}
