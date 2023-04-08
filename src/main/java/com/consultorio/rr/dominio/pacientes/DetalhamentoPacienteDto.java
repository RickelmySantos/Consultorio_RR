package com.consultorio.rr.dominio.pacientes;

import com.consultorio.rr.endereco.Endereco;

public record DetalhamentoPacienteDto(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

	public DetalhamentoPacienteDto(Pacientes paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
	}

	
}
