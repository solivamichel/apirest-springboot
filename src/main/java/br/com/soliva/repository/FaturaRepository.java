package br.com.soliva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.soliva.model.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {
	
//	List<Fatura> findByCliente( Cliente cliente );
}
