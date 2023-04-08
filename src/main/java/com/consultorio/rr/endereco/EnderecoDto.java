package com.consultorio.rr.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDto(
		
		String logradouro,
		String numero,
		String complemento,
		
		String bairro,
		
		String cidade,
		
		String uf,
		
		String cep) {

}
