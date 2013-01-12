package org.primefaces.extensions.component.tour;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;

/**
 * DOCUMENT ME!
 * 
 * @author Dumitru Ciubenco
 * @since 1.0.0
 * @created Jan 11, 2013 12:57:23 PM
 */
public class TourButton extends UIComponentBase {
	public static final String	COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String	DEFAULT_RENDERER	= "org.primefaces.extensions.component.TourButtonRenderer";
	private static final String	OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	protected enum PropertyKeys{
		value, onclick;

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

	public TourButton() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getValue() {
		return (String) getStateHelper().eval(PropertyKeys.value, null);
	}

	public void setValue(String value) {
		setAttribute(PropertyKeys.value, value);
	}

	public void setOnclick(String onclick) {
		setAttribute(PropertyKeys.onclick, onclick);
	}

	public String getOnclick() {
		return (String) getStateHelper().eval(PropertyKeys.onclick, null);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
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
