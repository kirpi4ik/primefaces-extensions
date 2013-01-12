package org.primefaces.extensions.component.tour;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.event.ListenerFor;
import javax.faces.event.PreRenderComponentEvent;

/**
 * DOCUMENT ME!
 * 
 * @author Dumitru Ciubenco
 * @since 1.0.0
 * @created Jan 11, 2013 11:58:46 AM
 */
@ListenerFor(systemEventClass = PreRenderComponentEvent.class)
public class TourStep extends UIComponentBase {
	public static final String	COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String	DEFAULT_RENDERER	= "org.primefaces.extensions.component.TourStepRenderer";
	private static final String	OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	protected enum PropertyKeys{
		width, focus, forId, forClass, arrowX, arrowY, arrowPosition, stateId;

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

	public TourStep() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getArrowPosition() {
		return (String) getStateHelper().eval(PropertyKeys.arrowPosition, null);
	}

	public void setArrowPosition(String arrowPosition) {
		setAttribute(PropertyKeys.arrowPosition, arrowPosition);
	}

	public String getArrowX() {
		return (String) getStateHelper().eval(PropertyKeys.arrowX, null);
	}

	public void setArrowX(String arrowX) {
		setAttribute(PropertyKeys.arrowX, arrowX);
	}

	public String getArrowY() {
		return (String) getStateHelper().eval(PropertyKeys.arrowY, null);
	}

	public void setArrowY(String arrowY) {
		setAttribute(PropertyKeys.arrowY, arrowY);
	}

	public String getFocus() {
		return (String) getStateHelper().eval(PropertyKeys.focus, null);
	}

	public void setFocus(String focus) {
		setAttribute(PropertyKeys.focus, focus);
	}

	public String getForClass() {
		return (String) getStateHelper().eval(PropertyKeys.forClass, null);
	}

	public void setForClass(String forClass) {
		setAttribute(PropertyKeys.forClass, forClass);
	}

	public String getForId() {
		return (String) getStateHelper().eval(PropertyKeys.forId, null);
	}

	public void setForId(String forId) {
		setAttribute(PropertyKeys.forId, forId);
	}

	public String getStateId() {
		return (String) getStateHelper().eval(PropertyKeys.stateId, null);
	}

	public void setStateId(String stateId) {
		setAttribute(PropertyKeys.stateId, stateId);
	}

	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(String width) {
		setAttribute(PropertyKeys.width, width);
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
