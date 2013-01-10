package org.primefaces.extensions.component.chosen;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;

import org.primefaces.component.api.Widget;

/**
 * 
 * @author Dumitru Ciubenco
 * @since 0.0.1
 * @created Nov 7, 2011 12:15:20 PM
 */
@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"), @ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "chosen/jquery.json-2.3.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "chosen/chosen.jquery.min.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "chosen/chosen.widget.js"),
		@ResourceDependency(library = "primefaces-extensions", name = "chosen/chosen.css") })
public class Chosen extends HtmlSelectOneMenu implements ClientBehaviorHolder, Widget {
	public static final String	COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String	DEFAULT_RENDERER	= "org.primefaces.extensions.component.chosen.ChosenRenderer";
	private static final String	OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";
	public static final String	STYLE_CLASS			= "chzn-select";

	protected enum PropertyKeys{
		style, readOnly, label, noResultsText, allowSingleDeselect, widgetVar, multiple;

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

	public Chosen() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(final String style) {
		setAttribute(PropertyKeys.style, style);
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(final String widgetVar) {
		setAttribute(PropertyKeys.widgetVar, widgetVar);
	}

	public String getNoResultsText() {
		return (String) getStateHelper().eval(PropertyKeys.noResultsText, null);
	}

	public void setNoResultsText(final String noResultsText) {
		setAttribute(PropertyKeys.noResultsText, noResultsText);
	}

	public Boolean getAllowSingleDeselect() {
		return (Boolean) getStateHelper().eval(PropertyKeys.allowSingleDeselect, false);
	}

	public void setAllowSingleDeselect(final Boolean allowSingleDeselect) {
		setAttribute(PropertyKeys.allowSingleDeselect, allowSingleDeselect);
	}

	public Boolean getMultiple() {
		return (Boolean) getStateHelper().eval(PropertyKeys.multiple, false);
	}

	public void setMultiple(final Boolean multiple) {
		setAttribute(PropertyKeys.multiple, multiple);
	}

	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label, "");
	}

	public void setLabel(final String widgetVar) {
		setAttribute(PropertyKeys.label, widgetVar);
	}

	public boolean isReadOnly() {
		return (Boolean) getStateHelper().eval(PropertyKeys.readOnly, false);
	}

	public void setReadOnly(final boolean readOnly) {
		setAttribute(PropertyKeys.readOnly, readOnly);
	}

	public String resolveWidgetVar() {
		final FacesContext context = FacesContext.getCurrentInstance();
		final String userWidgetVar = (String) getAttributes().get(PropertyKeys.widgetVar.toString());

		if (userWidgetVar != null) {
			return userWidgetVar;
		}

		return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	@SuppressWarnings("unchecked")
	public void setAttribute(final PropertyKeys property, final Object value) {
		getStateHelper().put(property, value);

		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if (setAttributes == null) {
			final String cname = this.getClass().getName();
			if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if (setAttributes != null && value == null) {
			final String attributeName = property.toString();
			final ValueExpression ve = getValueExpression(attributeName);
			if (ve == null) {
				setAttributes.remove(attributeName);
			} else if (!setAttributes.contains(attributeName)) {
				setAttributes.add(attributeName);
			}
		}
	}

	@Override
	public boolean isValid() {
		return super.isValid();
	}

	@Override
	public void queueEvent(final FacesEvent event) {
		super.queueEvent(event);
	}

}
