package com.consultorio.rr.dominio.medicos;

import com.consultorio.rr.endereco.Endereco;
import com.consultorio.rr.enums.Especialidade;

public record DetalamentoMedicosDto(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {

	public DetalamentoMedicosDto(Medicos medicos) {
		this(medicos.getNome(), medicos.getEmail(), medicos.getTelefone(), medicos.getCrm(), medicos.getEspecialidade(), medicos.getEndereco());
	}

	
}
