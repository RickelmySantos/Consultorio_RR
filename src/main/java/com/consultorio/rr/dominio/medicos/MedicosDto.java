package com.consultorio.rr.dominio.medicos;

import com.consultorio.rr.endereco.EnderecoDto;
import com.consultorio.rr.enums.Especialidade;

public record MedicosDto(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, EnderecoDto endereco) {

}
