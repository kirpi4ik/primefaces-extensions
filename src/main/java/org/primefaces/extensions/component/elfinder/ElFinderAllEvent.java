package org.primefaces.extensions.component.elfinder;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.primefaces.extensions.component.elfinder.event.Command;

public class ElFinderAllEvent extends ActionEvent {
	private Command	command;

	public ElFinderAllEvent(UIComponent component, Command command) {
		super(component);
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	private static final long	serialVersionUID	= 1L;

}
