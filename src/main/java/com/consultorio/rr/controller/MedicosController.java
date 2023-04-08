package com.consultorio.rr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.consultorio.rr.dominio.medicos.AtualizarMedicosDto;
import com.consultorio.rr.dominio.medicos.DetalamentoMedicosDto;
import com.consultorio.rr.dominio.medicos.ListagemMedicosDto;
import com.consultorio.rr.dominio.medicos.Medicos;
import com.consultorio.rr.dominio.medicos.MedicosDto;
import com.consultorio.rr.repositories.MedicosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

	@Autowired
	private MedicosRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarMedicos(@RequestBody @Valid  MedicosDto dados, UriComponentsBuilder uriBuilder) {
		var medicos = new Medicos(dados);
		repository.save(medicos);
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicos.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetalamentoMedicosDto(medicos));
	}
	
	@GetMapping
	public ResponseEntity<Page<ListagemMedicosDto>> listarMedicos(@PageableDefault Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(ListagemMedicosDto :: new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarMedicos(@RequestBody @Valid AtualizarMedicosDto dados) {
	
		var medicos = repository.getReferenceById(dados.id());
		medicos.atualizarInformacoesMedicos(dados);
		return ResponseEntity.ok(new DetalamentoMedicosDto(medicos));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletarMedicos(@PathVariable Long id) {
	var	medicos = repository.getReferenceById(id);
		medicos.excluir();
		return ResponseEntity.noContent().build();
	}
}
