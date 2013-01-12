package org.primefaces.extensions.component.megamenu;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "primefaces.css"), @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"), @ResourceDependency(library = "primefaces-extensions", name = "megamenu/megamenu.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "megamenu/megamenu.css") })
public class MegaMenu extends UIComponentBase implements org.primefaces.component.api.Widget {

	public static final String	COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String	DEFAULT_RENDERER	= "org.primefaces.extensions.component.MegaMenuRenderer";
	private static final String	OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	protected enum PropertyKeys{

		widgetVar, model, style, styleClass;

		String	toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public MegaMenu() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
		handleAttribute("widgetVar", _widgetVar);
	}

	public MegaMenuModel getModel() {
		return (MegaMenuModel) getStateHelper().eval(PropertyKeys.model, null);
	}

	public void setModel(MegaMenuModel _model) {
		getStateHelper().put(PropertyKeys.model, _model);
		handleAttribute("model", _model);
	}

	public java.lang.String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(java.lang.String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
		handleAttribute("style", _style);
	}

	public java.lang.String getStyleClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}

	public void setStyleClass(java.lang.String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
		handleAttribute("styleClass", _styleClass);
	}

	public static final String	CONTAINER_CLASS	= "ui-menu ui-menubar ui-megamenu ui-widget ui-widget-content ui-corner-all ui-helper-clearfix";

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public String resolveWidgetVar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		else return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public void handleAttribute(String name, Object value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if(setAttributes == null) {
			String cname = this.getClass().getName();
			if(cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if(setAttributes != null) {
			if(value == null) {
				ValueExpression ve = getValueExpression(name);
				if(ve == null) {
					setAttributes.remove(name);
				}else if(!setAttributes.contains(name)) {
					setAttributes.add(name);
				}
			}
		}
	}
}