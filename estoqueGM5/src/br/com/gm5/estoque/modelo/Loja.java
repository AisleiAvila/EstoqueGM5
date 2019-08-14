package br.com.gm5.estoque.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loja")
public class Loja implements Serializable {

	private static final long serialVersionUID = -7244850070327104987L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void adicionaItem(Item itemEncontrado) {
		// TODO Auto-generated method stub
		
	}

	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}

}
