package com.freitas.clientecep.services;

import com.freitas.clientecep.feign.ViaCepClient;
import com.freitas.clientecep.models.ClienteModel;
import com.freitas.clientecep.models.EnderecoModel;
import com.freitas.clientecep.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository; // Injeção de dependência do repositório.

    @Autowired
    private ViaCepClient viaCepClient; // Cliente para integração com a API ViaCEP.


    public ClienteModel cadastrarCliente(ClienteModel clienteModel) {
        // Consultar o endereço pelo CEP
        EnderecoModel endereco = viaCepClient.consultarCep(clienteModel.getCep());
        System.out.println("Endereco retornado: " + endereco);

        if (endereco == null) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + clienteModel.getCep());
        }

        // Atribuir o endereço ao cliente
        clienteModel.setEndereco(endereco);

        // Salvar o cliente no banco de dados
        return clienteRepository.save(clienteModel);
    }

    public ClienteModel buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
    }

    public List<ClienteModel> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel atualizarCliente(Long id, ClienteModel clienteModelAtualizado) {
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os dados do cliente
        clienteExistente.setNome(clienteModelAtualizado.getNome());

        // Se o CEP foi alterado, consultar o novo endereço e atualizar
        if (!clienteExistente.getCep().equals(clienteModelAtualizado.getCep())) {
            clienteExistente.setCep(clienteModelAtualizado.getCep());

            // Consulta o novo endereço baseado no CEP e atualiza
            EnderecoModel enderecoAtualizado = viaCepClient.consultarCep(clienteModelAtualizado.getCep());
            clienteExistente.setEndereco(enderecoAtualizado);
        }

        // Salvar as mudanças no banco de dados
        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
