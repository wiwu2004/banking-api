package com.wiwu.bankaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;


public class ContaRequestDTO {
    @NotBlank(message = "O nome do titular é obrigatorio")
    private String titular;

    @NotNull(message = "Saldo é obrigatorio")
    @Positive(message = "saldo deve ser maior que zero")
    private  Double saldo;


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
        if (!(o instanceof ContaRequestDTO that)) return false;
        return Objects.equals(getTitular(), that.getTitular()) && Objects.equals(getSaldo(), that.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitular(), getSaldo());
    }
}
