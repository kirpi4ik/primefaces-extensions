package org.primefaces.extensions.component.elfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionListener;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

import org.primefaces.component.api.Widget;
import org.primefaces.extensions.component.elfinder.event.Command;
import org.primefaces.extensions.component.elfinder.event.ElFinderEvent;
import org.primefaces.extensions.component.elfinder.model.FileSystem;
import org.primefaces.util.Constants;

@ResourceDependencies({ @ResourceDependency(
	library = "primefaces", name = "jquery/jquery.js"), @ResourceDependency(
	library = "primefaces", name = "primefaces.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "primefaces-extensions.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/css/theme.css"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/css/jquery-ui.custom.min.css"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/css/elfinder.min.css"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/jquery-ui.min.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.version.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/jquery.elfinder.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.resources.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.options.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.history.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/elFinder.command.js"), @ResourceDependency(
	// UI
	library = "primefaces-extensions", name = "elfinder/js/ui/overlay.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/workzone.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/navbar.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/dialog.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/tree.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/cwd.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/toolbar.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/button.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/uploadButton.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/viewbutton.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/searchbutton.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/sortbutton.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/panel.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/contextmenu.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/path.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/stat.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/ui/places.js"), @ResourceDependency(
	// COmmands
	library = "primefaces-extensions", name = "elfinder/js/commands/back.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/forward.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/reload.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/up.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/home.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/copy.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/commands/cut.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/paste.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/open.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/rm.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/info.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/duplicate.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/commands/rename.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/help.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/getfile.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/mkdir.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/mkfile.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/upload.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/commands/download.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/edit.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/quicklook.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/quicklook.plugins.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/extract.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/archive.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/commands/search.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/view.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/resize.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/sort.js"), @ResourceDependency(
	library = "primefaces-extensions", name = "elfinder/js/commands/netmount.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/jquery.dialogelfinder.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/i18n/elfinder.ru.js"), @ResourceDependency(

	library = "primefaces-extensions", name = "elfinder/js/elfinder-ext.js") })
public class ElFinder extends UIComponentBase implements Widget, ClientBehaviorHolder {
	public static final String				COMPONENT_FAMILY	= "org.primefaces.extensions.component";
	private static final String				DEFAULT_RENDERER	= "org.primefaces.extensions.component.ElFinderRenderer";
	private static final String				OPTIMIZED_PACKAGE	= "org.primefaces.extensions.component.";

	public static final String				EVENT_ALL			= "all";
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
																				.asList(EVENT_ALL, EVENT_ENABLE, EVENT_ADD, EVENT_CHANGE, EVENT_DBCLICK, EVENT_DISABLE, EVENT_DRAGSTART, EVENT_DRAGSTOP, EVENT_GETFILE, EVENT_LOAD, EVENT_LOCKFILES, EVENT_OPEN, EVENT_RELOAD, EVENT_REMOVE, EVENT_SELECT, EVENT_UNLOCKFILES));

	protected enum PropertyKeys {
		widgetVar,
		width,
		height,
		value,
		immediate,
		actionListener;
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

	public ElFinder() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public void broadcast(final FacesEvent event) throws AbortProcessingException {
		super.broadcast(event);
		for (FacesListener listener : getFacesListeners(ActionListener.class)) {
			if (event.isAppropriateListener(listener)) {
				event.processListener(listener);
			}
		}
		if (event instanceof ElFinderEvent) {
			final FacesContext context = getFacesContext();

			// invoke actionListener
			final MethodExpression listener = getActionListener();
			if (listener != null) {
				listener.invoke(context.getELContext(), new Object[] { ((ElFinderEvent) event) });
			}
		}
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
			String cmd = context.getExternalContext().getRequestParameterMap().get("cmd");
			if (cmd != null) {
				if (event instanceof AjaxBehaviorEvent) {
					final AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;
					ElFinderEvent elfEvent = new ElFinderEvent(this, behaviorEvent.getBehavior(), Command.valueOf(cmd.toUpperCase()));
					elfEvent.setPhaseId(behaviorEvent.getPhaseId());
					super.queueEvent(elfEvent);
				} else if (event instanceof ElFinderAllEvent) {
					final ElFinderAllEvent elfEvent = (ElFinderAllEvent) event;
					super.queueEvent(elfEvent);
				}
			}
		} else {
			super.queueEvent(event);
		}
	}

	private boolean isRequestSource(final String clientId, final Map<String, String> params) {
		return clientId.equals(params.get(Constants.PARTIAL_SOURCE_PARAM));
	}

	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(String width) {
		setAttribute(PropertyKeys.width, width);
	}

	public String getHeight() {
		return (String) getStateHelper().eval(PropertyKeys.height, null);
	}

	public void setHeight(String width) {
		setAttribute(PropertyKeys.height, width);
	}

	public FileSystem getValue() {
		return (FileSystem) getStateHelper().eval(PropertyKeys.value, null);
	}

	public void setValue(FileSystem width) {
		setAttribute(PropertyKeys.value, width);
	}

	public MethodExpression getActionListener() {
		return (MethodExpression) getStateHelper().get(PropertyKeys.actionListener);
	}

	public void setActionListener(final MethodExpression actionListener) {
		getStateHelper().put(PropertyKeys.actionListener, actionListener);
		handleAttribute("actionListener", actionListener);
	}

	public boolean isImmediate() {
		if (getStateHelper().get(PropertyKeys.immediate) != null) {
			return (Boolean) getStateHelper().get(PropertyKeys.immediate);
		} else {
			return false;
		}
	}

	public void setImmediate(boolean immediate) {
		setAttribute(PropertyKeys.immediate, immediate);
	}

	public void handleAttribute(String name, Object value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if (setAttributes == null) {
			String cname = this.getClass().getName();
			if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if (setAttributes != null) {
			if (value == null) {
				ValueExpression ve = getValueExpression(name);
				if (ve == null) {
					setAttributes.remove(name);
				} else if (!setAttributes.contains(name)) {
					setAttributes.add(name);
				}
			}
		}
	}

}
