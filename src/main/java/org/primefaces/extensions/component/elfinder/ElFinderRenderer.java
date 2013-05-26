package org.primefaces.extensions.component.elfinder;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

public class ElFinderRenderer extends CoreRenderer {

	@Override
	public void decode(final FacesContext context, final UIComponent component) {
		final ElFinder elFinder = (ElFinder) component;
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		System.out.println("TERST " + elFinder.getId() + ", " + params.get("cmd"));
		decodeBehaviors(context, component);
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
		}else{
			System.out.println("Encode: ..." + elFinder.getValue().getApi() + ", " + params.get("cmd"));
			writer.writeText("{ text:'"+elFinder.getValue().getApi()+"'}", null);
		}
	}
}
