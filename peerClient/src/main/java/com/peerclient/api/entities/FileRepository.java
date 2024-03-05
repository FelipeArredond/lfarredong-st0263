package com.peerclient.api.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<Files, Integer> {

    public Optional<Files> findByName(String name);
    public boolean existsByName(String name);

}
