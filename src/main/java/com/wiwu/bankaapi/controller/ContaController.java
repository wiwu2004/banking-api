package com.wiwu.bankaapi.controller;

import com.wiwu.bankaapi.dto.ContaRequestDTO;
import com.wiwu.bankaapi.dto.ContaResponseDTO;
import com.wiwu.bankaapi.model.Conta;
import com.wiwu.bankaapi.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarPorId(@PathVariable Long id){
        ContaResponseDTO conta = service.buscarPorId(id);
        conta.add(linkTo(methodOn(ContaController.class)
                .buscarPorId(id)).withSelfRel());

        conta.add(linkTo(methodOn(ContaController.class)
                .listar()).withRel("listar"));

        conta.add(linkTo(methodOn(ContaController.class)
                .depositar(id, null)).withRel("deposito"));

        conta.add(linkTo(methodOn(ContaController.class)
                .sacar(id,null)).withRel("saque"));


        return ResponseEntity.ok(conta);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> listar() {
        List<ContaResponseDTO> listar  = service.listar();
        for (ContaResponseDTO conta : listar){
            conta.add(linkTo(methodOn(ContaController.class).buscarPorId(conta.getId())).withSelfRel());

            conta.add(linkTo(methodOn(ContaController.class)
                    .depositar(conta.getId(), null)).withRel("deposito"));

            conta.add(linkTo(methodOn(ContaController.class)
                    .sacar(conta.getId(), null)).withRel("saque"));
        }


        return  ResponseEntity.ok(listar);
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> criar(@RequestBody @Valid ContaRequestDTO dto){
        ContaResponseDTO response = service.criarConta(dto);
        response.add(linkTo(methodOn(ContaController.class)
                .buscarPorId(response.getId()))
                .withSelfRel());

        response.add(linkTo(methodOn(ContaController.class)
                .listar())
                .withRel("listar"));

        response.add(linkTo(methodOn(ContaController.class)
                .depositar(response.getId(), null))
                .withRel("depositar"));

        response.add(
                linkTo(methodOn(ContaController.class)
                        .transferir(null, null, null))
                        .withRel("transferir")
        );


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("{id}/deposito")
    public  ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestParam Double valor){
        return  ResponseEntity.ok(service.depositar(id, valor));
    }

    @PostMapping("{id}/saque")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestParam Double valor){
        return ResponseEntity.ok(service.sacar(id, valor));
    }



    @PostMapping("/transferencia")
    public ResponseEntity<Void> transferir(
            @RequestParam Long origemId,
            @RequestParam Long destinoId,
            @RequestParam Double valor) {
        service.transferir(origemId,destinoId,valor);
        return  ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // retorna 204 vazio
    }


}
