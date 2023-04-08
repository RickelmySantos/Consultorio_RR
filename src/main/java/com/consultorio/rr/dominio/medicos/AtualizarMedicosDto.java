package com.consultorio.rr.dominio.medicos;

import com.consultorio.rr.endereco.EnderecoDto;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicosDto(@NotNull Long id, String nome, String telefone, EnderecoDto endereco) {

	}

