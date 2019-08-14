package br.com.gm5.estoque.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="estoque")
public class Estoque implements Serializable{

	private static final long serialVersionUID = 5510297214463008900L;
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "loja_id", nullable=false)
	private Loja loja;
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable=false)
	private Item item;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}