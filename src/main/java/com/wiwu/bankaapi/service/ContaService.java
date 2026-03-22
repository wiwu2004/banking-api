package com.wiwu.bankaapi.service;

import com.wiwu.bankaapi.dto.ContaRequestDTO;
import com.wiwu.bankaapi.dto.ContaResponseDTO;
import com.wiwu.bankaapi.exception.ContaNaoEncontradaException;
import com.wiwu.bankaapi.model.Conta;
import com.wiwu.bankaapi.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public List<ContaResponseDTO> listar(){
        return repository.findAll().stream().map(conta -> {
            ContaResponseDTO dto = new ContaResponseDTO();
            dto.setId(conta.getId());
            dto.setTitular(conta.getTitular());
            dto.setSaldo(conta.getSaldo());
            return dto;
        }).toList();

    }

    public ContaResponseDTO buscarPorId(Long id){
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta nao encontrada"));
        return toResponseDTO(conta);
    }

    public ContaResponseDTO toResponseDTO(Conta conta){
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setId(conta.getId());
        dto.setTitular(conta.getTitular());
        dto.setSaldo(conta.getSaldo());
        return  dto;
    }

    public ContaResponseDTO criarConta(ContaRequestDTO dto){
        Conta conta = new Conta();
        conta.setTitular(dto.getTitular());
        conta.setSaldo(dto.getSaldo());

        Conta salva = repository.save(conta);

        ContaResponseDTO response = new ContaResponseDTO();
        response.setId(salva.getId());
        response.setTitular(salva.getTitular());
        response.setSaldo(salva.getSaldo());
        return response;
    }


    public  Conta depositar(Long id, Double valor){
        Conta conta = repository.findById(id).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo() + valor);
        return repository.save(conta);
    }

    public Conta sacar(Long id, Double valor) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta nao encontrada"));
        if (conta.getSaldo() < valor){
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        return  repository.save(conta);
    }

    public void transferir(Long origimId, Long destinoId, Double valor){
        Conta origem = repository.findById(origimId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta origem nao encontrada"));

        Conta destino = repository.findById(destinoId)
                .orElseThrow(( () -> new ContaNaoEncontradaException("conta destino nao encontrada")));

        if (origem.getSaldo() < valor){
            throw new RuntimeException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        repository.save(origem);
        repository.save(destino);


    }


    public void deletar(Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        repository.delete(conta);
    }

}
