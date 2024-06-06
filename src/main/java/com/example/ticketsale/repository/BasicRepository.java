package com.example.ticketsale.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<T, U> extends JpaRepository<T, U> {
    public default T getById(U id) {
        return findById(id).orElseThrow(() -> {
            Class<?>[] classes = getClass().getInterfaces();
            for (Class<?> clazz: classes) {
                String className = clazz.getName().replaceAll("([A-Za-z0-9_-]+\\.)*", "");
                if (className.matches("^[A-Z][a-z]*Repository$")) {
                    return new EntityNotFoundException(className.substring(0, className.indexOf("Repository")) + " not found");
                }
            }
            return new EntityNotFoundException("Entity not found");
        });
    }
}
