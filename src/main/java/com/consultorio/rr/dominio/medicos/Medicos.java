package com.consultorio.rr.dominio.medicos;

import java.util.Objects;

import com.consultorio.rr.endereco.Endereco;
import com.consultorio.rr.enums.Especialidade;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Medicos")
@Table(name = "medicos")
public class Medicos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean ativo;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded
	private Endereco endereco;

	public Medicos() {

	}

	public Medicos(Long id, Boolean ativo, String nome, String email, String telefone, String crm,
			Especialidade especialidade, Endereco endereco) {

		this.id = id;
		this.ativo = true;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
	}

	public Medicos(@Valid MedicosDto dados) {
		this.id = dados.id();
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicos other = (Medicos) obj;
		return Objects.equals(id, other.id);
	}

	public void atualizarInformacoesMedicos(@Valid AtualizarMedicosDto dados) {
		if (this.nome != null) {
			this.nome = dados.nome();
		}
		if (this.telefone != null) {
			this.telefone = dados.telefone();
		}
		if (this.endereco != null) {
			this.endereco.atualizarEndereco(dados.endereco());
		}

	}

	public void excluir() {
		this.ativo = false;
	}

}
