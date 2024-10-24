package com.freitas.clientecep.controllers;

import com.freitas.clientecep.models.ClienteModel;
import com.freitas.clientecep.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes") // Mapeia a base da URL para este controlador.
public class ClienteController {

    @Autowired
    private ClienteService clienteService; // Injeção do serviço de cliente.

    @GetMapping
    public List<ClienteModel> buscarTodosClientes() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping("/{id}") // Endpoint para buscar cliente por ID.
    public ResponseEntity<ClienteModel> buscarClientePorId(@PathVariable Long id) {
        ClienteModel cliente = clienteService.buscarClientePorId(id); // Chama o serviço para buscar o cliente.
        return ResponseEntity.ok(cliente); // Retorna o cliente encontrado.
    }

    @PostMapping // Endpoint para adicionar um novo cliente.
    public ClienteModel cadastrarCliente(@RequestBody ClienteModel clienteModel) {
        return clienteService.cadastrarCliente(clienteModel); // Retorna o cliente criado com status 201.
    }

    @PutMapping("/{id}")
    public ClienteModel atualizarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteModelAtualizado) {
        return clienteService.atualizarCliente(id, clienteModelAtualizado);
    }

    @DeleteMapping("/{id}") // Endpoint para deletar cliente por ID.
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.ok("Cliente com ID " + id + " foi deletado com sucesso."); // Mensagem de sucesso
    }
}