package org.primefaces.extensions.component.elfinder;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseId;

import org.primefaces.extensions.component.elfinder.event.Command;
import org.primefaces.renderkit.CoreRenderer;

public class ElFinderRenderer extends CoreRenderer {

	@Override
	public void decode(final FacesContext context, final UIComponent component) {
		final ElFinder elFinder = (ElFinder) component;
		decodeBehaviors(context, component);
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String command = params.get("cmd");

		if (command != null) {
			ElFinderAllEvent ee = new ElFinderAllEvent(component, Command.valueOf(command.toUpperCase()));
			if (elFinder.isImmediate()) {
				ee.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
			} else {
				ee.setPhaseId(PhaseId.INVOKE_APPLICATION);
			}
			elFinder.queueEvent(ee);
			System.out.println("DECODE: " + elFinder.getId() + ", " + params.get("cmd"));
		}

	}

	@Override
	public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
		final ResponseWriter writer = context.getResponseWriter();
		final ElFinder elFinder = (ElFinder) component;
		final String clientId = elFinder.getClientId(context);
		final String widgetVar = elFinder.resolveWidgetVar();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		if (params.get("cmd") == null) {
			writer.startElement("div", elFinder);
			writer.writeAttribute("id", clientId, null);
			writer.endElement("div");
			startScript(writer, clientId);
			writer.write("$(function() {");
			writer.write("PrimeFacesExt.cw('" + ElFinder.class.getSimpleName() + "', '" + widgetVar + "', {");
			writer.write("id:'" + clientId + "'");
			encodeClientBehaviors(context, elFinder);
			writer.write("}, true);});");
			endScript(writer);

		} else {
			System.out.println("Encode: ..." + elFinder.getValue().getApi() + ", " + params.get("cmd"));
			writer.writeText("{ 'text' :'" + elFinder.getValue().getApi() + "'}", null);
		}
	}
}
