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

import com.consultorio.rr.dominio.pacientes.DetalhamentoPacienteDto;
import com.consultorio.rr.dominio.pacientes.ListagemPacientesDto;
import com.consultorio.rr.dominio.pacientes.Pacientes;
import com.consultorio.rr.dominio.pacientes.PacientesDto;
import com.consultorio.rr.repositories.PacientesRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
	@Autowired
	private PacientesRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrarPacientes(@RequestBody @Valid PacientesDto dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Pacientes(dados);
		repository.save(paciente);
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoPacienteDto(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<ListagemPacientesDto>> listarPacientes(@PageableDefault Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacientesDto::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizarPacientes(@RequestBody @Valid PacientesDto dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletarPacientes(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.excluir();
		return ResponseEntity.noContent().build();

	}
}
