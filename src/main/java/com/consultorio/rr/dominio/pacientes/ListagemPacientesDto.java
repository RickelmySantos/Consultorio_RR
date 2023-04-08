package com.consultorio.rr.dominio.pacientes;

public record ListagemPacientesDto(Long id, String nome, String email, String cpf) {

	public ListagemPacientesDto(Pacientes paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
