package br.com.gm5.estoque.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gm5.estoque.modelo.Estoque;
import br.com.gm5.estoque.modelo.Item;

public class ItemDAO {

	public boolean ExisteItemComEsseNome(Item item) {
		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Item> query;

		if (item.getId() == null) {
			//Inclusão de registro
			query = em.createQuery("SELECT i from Item i where upper(i.nome) = :pNome", Item.class);
			query.setParameter("pNome", item.getNome().toUpperCase());
			
		} else {
			//Alteração de registro
			query = em.createQuery("SELECT i from Item i where upper(i.nome) = :pNome and i.id != :pId", Item.class);
			query.setParameter("pNome", item.getNome().toUpperCase());
			query.setParameter("pId", item.getId());
		}
		
		boolean existe = !query.getResultList().isEmpty();

		em.close();

		return existe;
	}

	public boolean ExisteEstoque(Item item) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Estoque> query = em.createQuery("SELECT e from Estoque e join e.item p where p.id = :pItemId", Estoque.class);
		query.setParameter("pItemId", item.getId());
		boolean existe = !query.getResultList().isEmpty();

		em.close();

		return existe;
	}
	
}
