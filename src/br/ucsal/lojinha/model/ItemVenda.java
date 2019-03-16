package br.ucsal.lojinha.model;

import java.math.BigDecimal;

public class ItemVenda {

	
	private Produto produto;
	private Integer quntidade;
	private BigDecimal preco;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuntidade() {
		return quntidade;
	}
	public void setQuntidade(Integer quntidade) {
		this.quntidade = quntidade;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	

	
	
}
