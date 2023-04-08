package com.consultorio.rr.dominio.medicos;

import com.consultorio.rr.enums.Especialidade;

public record ListagemMedicosDto(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade) {

	public ListagemMedicosDto(Medicos medicos) {
		this(medicos.getId(), medicos.getNome(), medicos.getEmail(), medicos.getTelefone(), medicos.getCrm(),medicos.getEspecialidade());
	}
}
