package org.primefaces.extensions.component.tour;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

/**
 * DOCUMENT ME!
 * 
 * @author Dumitru Ciubenco
 * @since 0.6.5
 * @created Jan 11, 2013 11:49:30 AM
 */
@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"), @ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "tour/jquery-impromptu.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "tour/jquery-impromptu.css") })
public class Tour extends UIComponentBase implements Widget, ClientBehaviorHolder {
	public static final String	COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String	DEFAULT_RENDERER	= "org.primefaces.extensions.component.TourRenderer";
	private static final String	OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	enum PropertyKeys{
		widgetVar, style, styleClass, cookie;

		private String	toString;

		PropertyKeys(final String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		@Override
		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public Tour() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String resolveWidgetVar() {
		final FacesContext context = FacesContext.getCurrentInstance();
		final String userWidgetVar = (String) getAttributes().get(PropertyKeys.widgetVar.toString());

		if(userWidgetVar != null) {
			return userWidgetVar;
		}

		return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, getClientId().replaceAll(":", "_"));
	}

	public void setWidgetVar(String widgetVar) {
		setAttribute(PropertyKeys.widgetVar, widgetVar);
	}

	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}

	public void setStyleClass(String styleClass) {
		setAttribute(PropertyKeys.styleClass, styleClass);
	}

	public Boolean getCookie() {
		return (Boolean) getStateHelper().eval(PropertyKeys.cookie, null);
	}

	public void setCookie(Boolean cookie) {
		setAttribute(PropertyKeys.cookie, cookie);
	}

	public void setAttribute(PropertyKeys property, Object value) {
		getStateHelper().put(property, value);

		@SuppressWarnings("unchecked")
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if(setAttributes == null) {
			final String cname = this.getClass().getName();
			if(cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}

		if(setAttributes != null && value == null) {
			final String attributeName = property.toString();
			final ValueExpression ve = getValueExpression(attributeName);
			if(ve == null) {
				setAttributes.remove(attributeName);
			}else if(!setAttributes.contains(attributeName)) {
				setAttributes.add(attributeName);
			}
		}
	}
}
