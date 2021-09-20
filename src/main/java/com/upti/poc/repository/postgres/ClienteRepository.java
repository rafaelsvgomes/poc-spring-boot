package com.upti.poc.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upti.poc.model.postgres.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
