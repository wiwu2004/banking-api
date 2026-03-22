package com.wiwu.bankaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titular;
    private Double saldo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Conta conta)) return false;
        return Objects.equals(getId(), conta.getId()) && Objects.equals(getTitular(), conta.getTitular()) && Objects.equals(getSaldo(), conta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitular(), getSaldo());
    }
}