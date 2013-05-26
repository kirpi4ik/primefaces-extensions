package org.primefaces.extensions.component.elfinder.event;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

public class ElFinderEvent extends FacesEvent {
	private static final long	serialVersionUID	= 1L;
	private Command				command;

	public ElFinderEvent(UIComponent component, Command command) {
		super(component);
		this.command = command;
	}

	@Override
	public boolean isAppropriateListener(FacesListener fl) {
		return false;
	}

	@Override
	public void processListener(FacesListener fl) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Command getCommand() {
		return command;
	}
}
