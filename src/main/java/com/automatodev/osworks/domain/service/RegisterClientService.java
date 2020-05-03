package com.automatodev.osworks.domain.service;

import com.automatodev.osworks.domain.model.Cliente;
import com.automatodev.osworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService{

    @Autowired
    private ClienteRepository repository;

    public Cliente saveCliente(Cliente cliente){
        return repository.save(cliente);
    }

    public void deleteCliente(long idCliente){
        repository.deleteById(idCliente);
    }

}