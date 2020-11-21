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

import br.com.soliva.model.Fatura;
import br.com.soliva.service.FaturaService;

@RestController
@RequestMapping("api")
public class FaturaController {
	
	@Autowired
	private FaturaService faturaService;
	
	@GetMapping(value="/fatura")
	public ResponseEntity<List<Fatura>> getAll(){
		List<Fatura> fatura = faturaService.getAll();
		return new ResponseEntity<>(fatura,HttpStatus.OK);
	}	
	
	@GetMapping(value = "/fatura/{id}")
	public ResponseEntity<Fatura> getObject(@PathVariable("id") Integer id) {
		Optional<Fatura> fatura = faturaService.getId(id);
		if (!fatura.isPresent()) {			
			return new ResponseEntity<Fatura>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Fatura>(fatura.get(), HttpStatus.OK);
	}
	
	@PostMapping(value="/fatura")
	public ResponseEntity<Fatura> create(@RequestBody Fatura fatura){	
		faturaService.save(fatura);		
		return new ResponseEntity<Fatura>(HttpStatus.CREATED);
	}	
	
	@PutMapping(value="/fatura")
	public ResponseEntity<Fatura> update(@RequestBody Fatura fatura){
		Fatura faturaNova = faturaService.save(fatura);		
		return new ResponseEntity<Fatura>(faturaNova, HttpStatus.ACCEPTED);
	}	

	@DeleteMapping(value="/fatura/{id}")
	public ResponseEntity<List<Fatura>> delete(@PathVariable Integer id){
		Optional<Fatura> fatura = faturaService.getId(id);
		if(!fatura.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		faturaService.remove(fatura.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}