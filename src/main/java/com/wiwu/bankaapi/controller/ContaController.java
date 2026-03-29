package com.wiwu.bankaapi.controller;

import com.wiwu.bankaapi.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<ContaResponseDTO>> buscarPorId(@PathVariable Long id){
        ContaResponseDTO conta = service.buscarPorId(id);

        ApiResponse<ContaResponseDTO> response = new ApiResponse<>(200, "Conta encontrada com sucesso", conta);

        conta.add(linkTo(methodOn(ContaController.class)
                .buscarPorId(id)).withSelfRel());

        conta.add(linkTo(methodOn(ContaController.class)
                .listar()).withRel("listar"));

        conta.add(linkTo(methodOn(ContaController.class)
                .depositar(id, null)).withRel("deposito"));

        conta.add(linkTo(methodOn(ContaController.class)
                .sacar(id,null)).withRel("saque"));


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContaResponseDTO>>> listar() {
        List<ContaResponseDTO> listar  = service.listar();

        ApiResponse<List<ContaResponseDTO>> response = new ApiResponse<>(200,"Lista retornada com sucesso", listar);

        for (ContaResponseDTO conta : listar){
            conta.add(linkTo(methodOn(ContaController.class).buscarPorId(conta.getId())).withSelfRel());

            conta.add(linkTo(methodOn(ContaController.class)
                    .depositar(conta.getId(), null)).withRel("deposito"));

            conta.add(linkTo(methodOn(ContaController.class)
                    .sacar(conta.getId(), null)).withRel("saque"));
        }


        return  ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContaResponseDTO>> criar(@RequestBody @Valid ContaRequestDTO dto){
        ContaResponseDTO criar = service.criarConta(dto);

        ApiResponse<ContaResponseDTO> response = new ApiResponse<>(201,"Conta criada com sucesso", criar);


        criar.add(linkTo(methodOn(ContaController.class)
                .buscarPorId(criar.getId()))
                .withSelfRel());

        criar.add(linkTo(methodOn(ContaController.class)
                .listar())
                .withRel("listar"));

        criar.add(linkTo(methodOn(ContaController.class)
                .depositar(criar.getId(), null))
                .withRel("depositar"));

        criar.add(
                linkTo(methodOn(ContaController.class)
                        .transferir(null, null, null))
                        .withRel("transferir")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("{id}/deposito")
    public  ResponseEntity<ApiResponse<Conta>> depositar(@PathVariable Long id, @RequestParam Double valor){
        Conta conta = service.depositar(id, valor);
        ApiResponse<Conta> response = new ApiResponse<>(200, "Deposito efetuado com sucesso", conta );

        return  ResponseEntity.ok(response);
    }

    @PostMapping("{id}/saque")
    public ResponseEntity<ApiResponse<Conta>> sacar(@PathVariable Long id, @RequestParam Double valor){
        Conta sacar = service.sacar(id, valor);
        ApiResponse<Conta> response = new ApiResponse<>(200, "Valor sacado com sucesso", sacar);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/transferencia")
    public ResponseEntity<ApiResponse<Void>> transferir(
            @RequestParam Long origemId,
            @RequestParam Long destinoId,
            @RequestParam Double valor) {
        service.transferir(origemId,destinoId,valor);
        ApiResponse response = new ApiResponse(200, "Transferencia realizada com sucesso", null);
        return  ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
