package com.consultorio.rr.dominio.pacientes;

import com.consultorio.rr.endereco.EnderecoDto;

public record PacientesDto(Long id, String nome, String email, String cpf, String telefone, EnderecoDto endereco ) {

}
