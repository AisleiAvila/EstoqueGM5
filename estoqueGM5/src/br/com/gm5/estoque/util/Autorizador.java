package br.com.gm5.estoque.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.gm5.estoque.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 9039601385787424895L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println("Página: " + nomePagina);
		
		if("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		 Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		 if(usuarioLogado != null) {
			 return;
		 }
		 
		 // Redirecionamento para login.xhtml
		 NavigationHandler handler = context.getApplication().getNavigationHandler();
		 handler.handleNavigation(context, null, "/login?faces-redirect=true");
		 context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent evento) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
