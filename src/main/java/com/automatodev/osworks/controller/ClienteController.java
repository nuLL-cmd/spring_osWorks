package com.automatodev.osworks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automatodev.osworks.domain.model.Cliente;
import com.automatodev.osworks.domain.repository.ClienteRepository;
import com.automatodev.osworks.domain.service.RegisterClientService;

//Controlador rest, clase que contem os endpoints referente ao CRUD de uma determinada entidade

//A anotação GetMapping recebe os parametros "endpoint" e no caso de pesquisas "endpoint/{valor}" no qual é apontada pela variavel do metodo, a anotação PathVariabels, indica que a propriedade do metodo
//esta ligada ao valor do endpoint passado

@RestController // INFORMA AO SPRING QUE ESTA CLASSE É UM CONTROLADOR REST 
@RequestMapping("clientes") // SETA O ENDPOINT INICIAL DIRETAMENTE NA CLASSE
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;

	@Autowired
	RegisterClientService registerCliente;

	//METODO HTTP DO TIPO GET QUE RECUPERA TODOS OS DADOS DO BANCO
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> all = new ArrayList<>();
		all = repository.findAll();
		if (all.size() != 0)
			return ResponseEntity.ok(all);
		else
			return ResponseEntity.notFound().build();
						
	}
	 // METODO HTTP DO TIPO GET QUE RECUPERA UM SINGLE DATA POR ID
	@GetMapping("{id}")
	public ResponseEntity<Cliente> getSingleCliente(@PathVariable long id) {
	Cliente byId = repository.findById(id);

		if(byId != null)
			return ResponseEntity.ok(byId);
		else
			return ResponseEntity.notFound().build();
		
	}
	 // METODO HTTP DO TIPO GET QUE RECUPERA DADOS APARTIR DE UM OU MAIS CARACTERES DIGITADOS (QUEIVALENTE AO LIKE % SQL)
	@GetMapping("like/{charset}")
	public ResponseEntity<List<Cliente>> getFilter(@PathVariable String charset){
		List<Cliente> filterClientes = new ArrayList<>();
		filterClientes = repository.findByNameContaining(charset);
		if (filterClientes.size() != 0)
			return ResponseEntity.ok(filterClientes);
		else
			return ResponseEntity.notFound().build();
	}
	// METODO DE ISERÇÃO DE UM DADO NO BANCO DE DADOS
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente addCliente(@Valid @RequestBody Cliente cliente) {
      return registerCliente.saveCliente(cliente);

	}
	// METODO DE UPDATE NO BANCO DE DADOS
	@PutMapping("{id}") 
	public ResponseEntity<Cliente> updateCliente(@PathVariable long id, @RequestBody Cliente cliente) {
		if(!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		cliente.setId(id);
		cliente = registerCliente.saveCliente(cliente);
		return ResponseEntity.ok(cliente);
	}
	 // METODO DE DELETE DO BANCO DE DADOS
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable long id){
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		registerCliente.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}
	
}
