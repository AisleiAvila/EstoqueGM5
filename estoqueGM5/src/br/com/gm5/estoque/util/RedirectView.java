package br.com.gm5.estoque.util;

public class RedirectView {
	private String viewName;

	public RedirectView(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	@Override
	public String toString() {
	    return viewName + "?faces-redirect=true";
	}

}
