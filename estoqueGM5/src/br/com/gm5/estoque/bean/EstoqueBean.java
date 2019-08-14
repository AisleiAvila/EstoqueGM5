package br.com.gm5.estoque.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.gm5.estoque.dao.DAO;
import br.com.gm5.estoque.dao.EstoqueDAO;
import br.com.gm5.estoque.modelo.Estoque;
import br.com.gm5.estoque.modelo.Item;
import br.com.gm5.estoque.modelo.Loja;
import br.com.gm5.estoque.util.RedirectView;

@ManagedBean
@ViewScoped
public class EstoqueBean {

	private Estoque estoque = new Estoque();

	private Loja lojaSelecionada;

	private Integer itemId;

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public RedirectView gravar() {

		//Loja loja = new DAO<Loja>(Loja.class).buscaPorId(this.lojaSelecionada.getId());
		this.estoque.setLoja(this.lojaSelecionada);
		Item item = new DAO<Item>(Item.class).buscaPorId(itemId);
		this.estoque.setItem(item);

		System.out.println("Gravando estoque --> Id Estoque: " + this.estoque.getId() + " Quantidade: "
				+ this.estoque.getQuantidade() + " Id Loja: " + this.estoque.getLoja().getId() + " Id Item: "
				+ this.estoque.getItem().getId());

		if (estoque.getId() == null) {
			new DAO<Estoque>(Estoque.class).adiciona(this.estoque);
		} else {
			new DAO<Estoque>(Estoque.class).atualiza(this.estoque);
		}

		this.estoque = new Estoque();
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lojaSelecionada", this.lojaSelecionada);

		return new RedirectView("estoque");
	}

	public void remover(Estoque estoque) {
		System.out.println("Removendo estoque: " + estoque.getId());
		new DAO<Estoque>(Estoque.class).remove(estoque);
	}

	@PostConstruct
	public void init() {
		lojaSelecionada = (Loja) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("lojaSelecionada");
		itemId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("itemId");
	}

	public List<Estoque> getEstoques() {
		System.out.println("lojaSelecionada nome: " + this.lojaSelecionada.getNome());
		List<Estoque> estoques = null;
		if (this.lojaSelecionada != null) {

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lojaSelecionada", this.lojaSelecionada);

			estoques = new EstoqueDAO().buscaEstoquePorLoja(this.lojaSelecionada);
		}
		return estoques;
	}

	public List<Item> getItens() {
		return new EstoqueDAO().buscaItensNaoAssociadosLoja(this.lojaSelecionada);
	}

	public void carregar(Estoque estoque) {
		System.out.println("Carregando estoque: " + estoque.getId());
		this.estoque = estoque;
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("itemId", estoque.getItem().getId());
	}

	public void carregarEstoquePelaId() {
		Integer id = this.estoque.getId();
		this.estoque = new DAO<Estoque>(Estoque.class).buscaPorId(id);
		if (this.estoque == null) {
			this.estoque = new Estoque();
		}
	}

	public Double multiplicarValores(Estoque estoque) {
		Double resultado = new Double(0);

		if (estoque.getQuantidade() != null) {
			resultado = estoque.getQuantidade() * estoque.getItem().getValor();
		}

		return resultado;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}
