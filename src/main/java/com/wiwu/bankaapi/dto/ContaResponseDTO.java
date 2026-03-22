package com.wiwu.bankaapi.dto;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;

public class ContaResponseDTO extends RepresentationModel<ContaResponseDTO>{
    private long id;
    private String titular;
    private Double saldo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        if (!(o instanceof ContaResponseDTO that)) return false;
        return getId() == that.getId() && Objects.equals(getTitular(), that.getTitular()) && Objects.equals(getSaldo(), that.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitular(), getSaldo());
    }
}
