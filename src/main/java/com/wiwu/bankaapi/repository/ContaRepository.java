package com.wiwu.bankaapi.repository;

import com.wiwu.bankaapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta, Long> {
}
