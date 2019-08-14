package br.com.gm5.estoque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gm5.estoque.modelo.Estoque;
import br.com.gm5.estoque.modelo.Item;
import br.com.gm5.estoque.modelo.Loja;

public class EstoqueDAO {

	public List<Estoque> buscaEstoquePorLoja(Loja loja) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Estoque> query = em.createQuery("SELECT e from Estoque e join e.loja p where p.id = :pLojaId", Estoque.class);
		query.setParameter("pLojaId", loja.getId());

		List<Estoque> resultado = query.getResultList();

		em.close();

		return resultado;
	}

	public List<Item> buscaItensNaoAssociadosLoja(Loja loja) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Item> query = em.createQuery("select i from Item i where i.id not in (select distinct e.item.id from Estoque e where e.loja.id = :pLojaId)", Item.class);
				
		query.setParameter("pLojaId", loja.getId());

		List<Item> resultado = query.getResultList();

		em.close();

		return resultado;
	}

}
