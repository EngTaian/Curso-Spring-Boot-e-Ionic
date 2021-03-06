package com.taian.cursospringbootcomionic.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.taian.cursospringbootcomionic.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Length(min=5, max=120, message ="O tamanho deve do campo deve ter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Campo obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	@NotEmpty(message="Campo obrigatório")
	private String cpfCnpj;
	
	@NotEmpty(message="Campo obrigatório")
	private String senha;
	
	private Integer tipo;
	
	@NotEmpty(message="Campo obrigário")
	private String logradouro;
	
	@NotEmpty(message="Campo obrigário")
	private String numero;
	private String complemento;
	@NotEmpty(message="Campo obrigário")
	private String bairro;
	@NotEmpty(message="Campo obrigário")
	private String cep;
	
	@NotEmpty(message="Campo obrigário")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {
				
	}

	public ClienteNewDTO(String nome, String email, String cpfCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3,
			Integer cidadeId, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.cidadeId = cidadeId;
		this.senha = senha;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	
}
