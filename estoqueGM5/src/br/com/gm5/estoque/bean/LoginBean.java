package br.com.gm5.estoque.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.gm5.estoque.dao.UsuarioDAO;
import br.com.gm5.estoque.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());
		
		boolean existe = new UsuarioDAO().existe(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "loja?faces-redirect=true";
		} else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			return "login?faces-redirect=true";
		}
	}

public String deslogar() {
	FacesContext context = FacesContext.getCurrentInstance();
	context.getExternalContext().getSessionMap().remove("usuarioLogado");
	return "login?faces-redirect=true";
}

}
