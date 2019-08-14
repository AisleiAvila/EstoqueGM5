package br.com.gm5.estoque.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.gm5.estoque.dao.DAO;
import br.com.gm5.estoque.dao.ItemDAO;
import br.com.gm5.estoque.modelo.Item;

@ManagedBean
@ViewScoped
public class ItemBean {

	private Item item = new Item();

	// private Integer autorId;

	public Item getItem() {
		return item;
	}

	public void gravar() {
		System.out.println("Gravando item " + this.item.getNome() + " Id: " + this.item.getId());
		
		// Verificar se já existe item com o mesmo nome
		boolean existeItemIgual = new ItemDAO().ExisteItemComEsseNome(this.item);
		
		if (existeItemIgual) {
			FacesContext.getCurrentInstance().addMessage("formDadosItem:nome", new FacesMessage("Já existe Item com esse mesmo nome!"));
		} else {
			if (this.item.getId() == null) {
				//Inclusão
				new DAO<Item>(Item.class).adiciona(this.item);
				FacesContext.getCurrentInstance().addMessage("formDadosItem:nome", new FacesMessage("Inclusão realizada com sucesso!"));
			} else {
				//Alteraçãpo
				new DAO<Item>(Item.class).atualiza(this.item);
				FacesContext.getCurrentInstance().addMessage("formDadosItem:nome", new FacesMessage("Alteração realizada com sucesso!"));
			}
			this.item = new Item();
		}
		
	}

	public void remover(Item item) {
		System.out.println("Removendo item: " + item.getNome());
		
		//Verificar se há estoque associado ao Item
		boolean existeEstoque = new ItemDAO().ExisteEstoque(item);
		if(existeEstoque) {
			FacesContext.getCurrentInstance().addMessage("formDadosItem:nome", new FacesMessage("Item " + item.getNome() + " existente em estoque. Não pode ser excluído!"));
		} else{
			new DAO<Item>(Item.class).remove(item);
			FacesContext.getCurrentInstance().addMessage("formDadosItem:nome", new FacesMessage("Exclusão realizada com sucesso!"));
		}
		
	}

	public List<Item> getItens() {
		return new DAO<Item>(Item.class).listaTodos();
	}

	public void carregar(Item item) {
		System.out.println("Carregando item: " + item.getNome());
		this.item = item;
	}

	public void carregarItemPelaId() {
		Integer id = this.item.getId();
		this.item = new DAO<Item>(Item.class).buscaPorId(id);
		if(this.item == null) {
			this.item = new Item();
		}
	}


}
