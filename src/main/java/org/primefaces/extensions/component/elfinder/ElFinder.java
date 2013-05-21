package org.primefaces.extensions.component.elfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;

import org.primefaces.component.api.Widget;
import org.primefaces.extensions.event.ResizeEvent;
import org.primefaces.extensions.event.RotateEvent;
import org.primefaces.util.Constants;

@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"), @ResourceDependency(library = "primefaces", name = "primefaces.js"), @ResourceDependency(
																																															library = "primefaces-extensions",
																																															name = "elfinder/css/elfinder.min.css"), @ResourceDependency(
																																																															library = "primefaces-extensions",
																																																															name = "elfinder/css/theme.css"),

@ResourceDependency(library = "primefaces-extensions", name = "elfinder/js/elfinder.min.js"), @ResourceDependency(
																													library = "primefaces-extensions",
																													name = "elfinder/js/i18n/elfinder.ru.js"), })
public class ElFinder extends UIComponentBase implements Widget, ClientBehaviorHolder {
	public static final String				COMPONENT_TYPE		= "org.primefaces.extensions.component.ElFinder";
	public static final String				COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String				DEFAULT_RENDERER	= "org.primefaces.extensions.component.ElFinderRenderer";
	private static final String				OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	public static final String				EVENT_ENABLE		= "enable";
	public static final String				EVENT_DISABLE		= "disable";
	public static final String				EVENT_LOAD			= "load";
	public static final String				EVENT_OPEN			= "open";
	public static final String				EVENT_RELOAD		= "reload";
	public static final String				EVENT_SELECT		= "select";
	public static final String				EVENT_ADD			= "add";
	public static final String				EVENT_REMOVE		= "remove";
	public static final String				EVENT_CHANGE		= "change";
	public static final String				EVENT_DBCLICK		= "dbclick";
	public static final String				EVENT_GETFILE		= "getfile";
	public static final String				EVENT_LOCKFILES		= "lockfiles";
	public static final String				EVENT_UNLOCKFILES	= "unlockfiles";
	public static final String				EVENT_DRAGSTART		= "dragstart";
	public static final String				EVENT_DRAGSTOP		= "dragstop";

	private static final Collection<String>	EVENT_NAMES			= Collections
																		.unmodifiableCollection(Arrays
																				.asList(EVENT_ENABLE, EVENT_ADD, EVENT_CHANGE, EVENT_DBCLICK, EVENT_DISABLE, EVENT_DRAGSTART, EVENT_DRAGSTOP, EVENT_GETFILE, EVENT_LOAD, EVENT_LOCKFILES, EVENT_OPEN, EVENT_RELOAD, EVENT_REMOVE, EVENT_SELECT, EVENT_UNLOCKFILES));

	protected enum PropertyKeys {
		widgetVar,
		width,
		height,

	}

	public ElFinder() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
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
	public void queueEvent(final FacesEvent event) {
		final FacesContext context = FacesContext.getCurrentInstance();
		final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		final String clientId = getClientId(context);

		if (isRequestSource(clientId, params)) {
			final String eventName = params.get(Constants.PARTIAL_BEHAVIOR_EVENT_PARAM);

			final BehaviorEvent behaviorEvent = (BehaviorEvent) event;

			if (eventName.equals(EVENT_ADD)) {
//				final double width = Double.parseDouble(params.get(clientId + "_width"));
//				final double height = Double.parseDouble(params.get(clientId + "_height"));
//
//				final ResizeEvent resizeEvent = new ResizeEvent(this, behaviorEvent.getBehavior(), width, height);
//
//				super.queueEvent(resizeEvent);
			} 
		} else {
			super.queueEvent(event);
		}
	}

	private boolean isRequestSource(final String clientId, final Map<String, String> params) {
		return clientId.equals(params.get(Constants.PARTIAL_SOURCE_PARAM));
	}

}
