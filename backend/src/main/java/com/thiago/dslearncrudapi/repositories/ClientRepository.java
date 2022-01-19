package com.thiago.dslearncrudapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiago.dslearncrudapi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
