package br.com.soliva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.soliva.model.Fatura;
import br.com.soliva.repository.FaturaRepository;

@Service
@Transactional
public class FaturaService {
	
	@Autowired
	private FaturaRepository faturaRepository;
	
	public Fatura save(Fatura fatura) {
		fatura.setLeituraAtual(fatura.getLeituraAtual() - fatura.getLeituraAnterior());
		
		if( fatura.getId() != null ) {
			Fatura faturaNova = getId(fatura.getId()).get();
			faturaNova.setData(fatura.getData());
			faturaNova.setLeituraAtual(fatura.getLeituraAtual());
			return faturaRepository.save(faturaNova);
		}
		
		return faturaRepository.save(fatura);
	}

	public List<Fatura> getAll() {
	    return faturaRepository.findAll();
	}

	public void remove(Fatura fatura) {
		faturaRepository.delete(fatura);
	}

	public Optional<Fatura> getId(Integer id) {
		return faturaRepository.findById(id);
	}	
}