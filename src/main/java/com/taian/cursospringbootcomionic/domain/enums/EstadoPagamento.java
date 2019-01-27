package com.taian.cursospringbootcomionic.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pedido Pendente"),
	QUITADO(2, "Pedido Quitado"),
	CANCELADO(3, "Pedido Cancelado");
	
	private Integer cod;
	private String descricao;	
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	private EstadoPagamento() {
	}
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(x.getCod().equals(cod)) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
