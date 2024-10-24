package com.freitas.clientecep.repository;

import com.freitas.clientecep.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    // Interface que estende JpaRepository para operações CRUD de ClienteModel.
}
