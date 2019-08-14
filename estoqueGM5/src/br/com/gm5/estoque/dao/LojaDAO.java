package br.com.gm5.estoque.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gm5.estoque.modelo.Estoque;
import br.com.gm5.estoque.modelo.Loja;

public class LojaDAO {

	public boolean ExisteLojaComEsseNome(Loja loja) {
		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Loja> query;

		if (loja.getId() == null) {
			//Inclusão de registro
			query = em.createQuery("SELECT l from Loja l where upper(l.nome) = :pNome", Loja.class);
			query.setParameter("pNome", loja.getNome().toUpperCase());
			
		} else {
			//Alteração de registro
			query = em.createQuery("SELECT l from Loja l where upper(l.nome) = :pNome and l.id != :pId", Loja.class);
			query.setParameter("pNome", loja.getNome().toUpperCase());
			query.setParameter("pId", loja.getId());
		}
		
		boolean existe = !query.getResultList().isEmpty();

		em.close();

		return existe;
	}

	public boolean ExisteEstoque(Loja loja) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Estoque> query = em.createQuery("SELECT e from Estoque e join e.loja p where p.id = :pLojaId", Estoque.class);
		query.setParameter("pLojaId", loja.getId());
		boolean existe = !query.getResultList().isEmpty();

		em.close();

		return existe;
	}
	
}
