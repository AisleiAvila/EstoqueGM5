package br.com.gm5.estoque.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;

import br.com.gm5.estoque.dao.DAO;
import br.com.gm5.estoque.dao.EstoqueDAO;
import br.com.gm5.estoque.dao.LojaDAO;
import br.com.gm5.estoque.modelo.Estoque;
import br.com.gm5.estoque.modelo.Item;
import br.com.gm5.estoque.modelo.Loja;
import br.com.gm5.estoque.util.RedirectView;

@ManagedBean
//@ManegedBean(name = "lojaBean")
@ViewScoped
public class LojaBean implements Serializable {

	private static final long serialVersionUID = -604734305503366742L;

	private Loja loja = new Loja();

	//private Integer lojaId;

	public Loja getLoja() {
		return loja;
	}

	public List<Item> getItem() {
		return new DAO<Item>(Item.class).listaTodos();
	}

	public void gravar() throws ValidatorException {
		System.out.println("Gravando loja " + this.loja.getNome() + " Id: " + this.loja.getId());

		// Verificar se já existe loja com o mesmo nome
		boolean existeLojaIgual = new LojaDAO().ExisteLojaComEsseNome(this.loja);

		if (existeLojaIgual) {
			FacesContext.getCurrentInstance().addMessage("formDadosLoja:nome", new FacesMessage("Já existe Loja com o mesmo nome!"));
		} else {
			if (this.loja.getId() == null) {
				//Inclusão
				new DAO<Loja>(Loja.class).adiciona(this.loja);
				FacesContext.getCurrentInstance().addMessage("formDadosLoja:nome", new FacesMessage("Inclusão realizada com sucesso!"));
			} else {
				//Alteraçãpo
				new DAO<Loja>(Loja.class).atualiza(this.loja);
				FacesContext.getCurrentInstance().addMessage("formDadosLoja:nome", new FacesMessage("Alteração realizada com sucesso!"));
			}
			this.loja = new Loja();
		}

	}

	public List<Estoque> getEstoque() {
		return new EstoqueDAO().buscaEstoquePorLoja(loja);
	}

	public RedirectView redirecionaEstoque(Loja loja) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lojaSelecionada", loja);
		return new RedirectView("estoque");
	}

	public void gravarItem(Item item) {
		Item itemEncontrado = new DAO<Item>(Item.class).buscaPorId(item.getId());
		this.loja.adicionaItem(itemEncontrado);
	}

	public List<Item> getItensDaLoja() {
		System.out.println("Buscando itens da loja " + loja.getNome());
		new DAO<Loja>(Loja.class).remove(loja);
		return null;
	}

	public void remover(Loja loja) {
		System.out.println("Removendo loja " + loja.getNome());
		//Verificar se há estoque associado a Loja
		boolean existeEstoque = new LojaDAO().ExisteEstoque(loja);
		if(existeEstoque) {
			FacesContext.getCurrentInstance().addMessage("formDadosLoja:nome", new FacesMessage("Loja " + loja.getNome() + " possui itens no estoque. Não pode ser excluída!"));
		} else{
			new DAO<Loja>(Loja.class).remove(loja);
			FacesContext.getCurrentInstance().addMessage("formDadosLoja:nome", new FacesMessage("Exclusão realizada com sucesso!"));
		}
		
	}

	public void removerItemDaLoja(Item item) {
		this.loja.removeItem(item);
	}

	public void carregar(Loja loja) {
		System.out.println("Carregando loja " + loja.getNome() + " Id: " + loja.getId());
		this.loja = loja;
	}

	/*
	 * public Integer getAutorId() { return autorId; }
	 * 
	 * public void setAutorId(Integer autorId) { this.autorId = autorId; }
	 */

	public List<Loja> getLojas() {
		return new DAO<Loja>(Loja.class).listaTodos();
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
