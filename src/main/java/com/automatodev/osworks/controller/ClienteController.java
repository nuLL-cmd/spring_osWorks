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

//Controlador rest, clase que contem os endpoints referente ao CRUD de uma determinada entidade

//A anotação GetMapping recebe os parametros "endpoint" e no caso de pesquisas "endpoint/{valor}" no qual é apontada pela variavel do metodo, a anotação PathVariabels, indica que a propriedade do metodo
//esta ligada ao valor do endpoint passado
@RestController // INFORMA AO SPRING QUE ESTA CLASSE É UM CONTROLADOR REST 
@RequestMapping("clientes") // SETA O ENDPOINT INICIAL DIRETAMENTE NA CLASSE
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;
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
	@GetMapping("{id}") // METODO HTTP DO TIPO GET QUE RECUPERA UM SINGLE DATA POR ID
	public ResponseEntity<Cliente> getSingleCliente(@PathVariable long id) {
		Optional<Cliente> optional = repository.findById(id);

		if(optional.isPresent())
			return ResponseEntity.ok(optional.get());
		else
			return ResponseEntity.notFound().build();
		
	}
	@GetMapping("like/{charset}") // METODO HTTP DO TIPO GET QUE RECUPERA DADOS APARTIR DE UM OU MAIS CARACTERES DIGITADOS (QUEIVALENTE AO LIKE % SQL)
	public ResponseEntity<List<Cliente>> getFilter(@PathVariable String charset){
		List<Cliente> filterClientes = new ArrayList<>();
		filterClientes = repository.findByNameContaining(charset);
		if (filterClientes.size() != 0)
			return ResponseEntity.ok(filterClientes);
		else
			return ResponseEntity.notFound().build();
	}
	@PostMapping // METODO DE ISERÇÃO DE UM DADO NO BANCO DE DADOS
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente addCliente(@Valid @RequestBody Cliente cliente) {
      return repository.save(cliente);

	}
	@PutMapping("{id}") // METODO DE UPDATE NO BANCO DE DADOS
	public ResponseEntity<Cliente> updateCliente(@PathVariable long id, @RequestBody Cliente cliente) {
		if(!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		cliente.setId(id);
		cliente = repository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("{id}") // METODO DE DELETE DO BANCO DE DADOS
	public ResponseEntity<Void> deleteCliente(@PathVariable long id){
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		 repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
