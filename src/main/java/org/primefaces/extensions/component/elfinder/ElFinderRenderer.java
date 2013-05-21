package org.primefaces.extensions.component.elfinder;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

public class ElFinderRenderer extends CoreRenderer {

	@Override
	public void decode(final FacesContext context, final UIComponent component) {
		decodeBehaviors(context, component);
	}

	@Override
	public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
		final ResponseWriter writer = context.getResponseWriter();
		final ElFinder elFinder = (ElFinder) component;
		final String clientId = elFinder.getClientId(context);
		final String widgetVar = elFinder.resolveWidgetVar();

		writer.startElement("div", elFinder);
		writer.writeAttribute("id", clientId, null);
		writer.endElement("div");

		startScript(writer, clientId);

		writer.write("$(function() {");
		writer.write("$('#" + clientId + "').elfinder({");

		writer.write("url:'/connector'");
		writer.write(",lang:'ru'");

		encodeClientBehaviors(context, elFinder);

		writer.write("}); });");

		endScript(writer);
	}
}
