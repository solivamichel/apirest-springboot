package br.com.soliva.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.soliva.model.Cliente;
import br.com.soliva.service.ClienteService;

@RestController
@RequestMapping("api")
public class ClienteController {
	
	@Autowired	
	private ClienteService clienteService;
	
	@GetMapping(value="/cliente")
	public ResponseEntity<List<Cliente>> getAll(){
		List<Cliente> cliente = clienteService.getAll();
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/cliente/{id}")
	public ResponseEntity<Cliente> getObject(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clienteService.getId(id);
		if (!cliente.isPresent()) {			
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	}
	
	@PostMapping(value="/cliente")
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){	
		clienteService.save(cliente);		
		return new ResponseEntity<Cliente>(HttpStatus.CREATED);
	}	
	
	@PutMapping(value="/cliente")
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
		Cliente clienteNovo = clienteService.save(cliente);		
		return new ResponseEntity<Cliente>(clienteNovo, HttpStatus.ACCEPTED);
	}	

	@DeleteMapping(value="/cliente/{id}")
	public ResponseEntity<List<Cliente>> delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteService.getId(id);
		if(!cliente.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.remove(cliente.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}