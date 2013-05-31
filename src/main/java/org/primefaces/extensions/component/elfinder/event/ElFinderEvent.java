package org.primefaces.extensions.component.elfinder.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;

public class ElFinderEvent extends javax.faces.event.AjaxBehaviorEvent {
	private static final long	serialVersionUID	= 1L;
	private Command				command;

	public ElFinderEvent(UIComponent component, Behavior behavior, Command command) {
		super(component, behavior);
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

}
