package org.primefaces.extensions.component.megamenu;

import java.io.Serializable;
import java.util.ArrayList;

public class MegaMenuModel extends ArrayList<MegaMenuModel> implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				label;
	private String				title;
	private String				url;

	public MegaMenuModel() {

	}

	public MegaMenuModel(String label, String title, String url) {
		this.label = label;
		this.title = title;
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
