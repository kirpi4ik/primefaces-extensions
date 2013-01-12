package org.primefaces.extensions.component.megamenu;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

public class MegaMenuRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		super.decode(context, component);
	}

	@Override
	public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
		final MegaMenu chosen = (MegaMenu) component;
		encodeMarkup(context, chosen);
		encodeScript(context, chosen);
	}

	protected void encodeMarkup(FacesContext context, MegaMenu menu) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		MegaMenuModel model = menu.getModel();
		String clientId = menu.getClientId(context);
		writer.startElement("div", menu);
		writer.writeAttribute("id", clientId, "id");
		writer.writeAttribute("class", "dcjq-vertical-mega-menu", "styleClass");
		writer.startElement("ul", menu);
		writer.writeAttribute("class", "megamenu", "styleClass");
		for (MegaMenuModel megaMenuModel : model) {
			encodeSubMenu(writer, megaMenuModel, menu);
		}
		writer.endElement("ul");
		writer.endElement("div");
	}

	private void encodeSubMenu(ResponseWriter writer, MegaMenuModel parentModel, MegaMenu menu) throws IOException {
		if (parentModel.size() > 0) {
			writer.startElement("li", menu);
			writer.startElement("a", menu);
			writer.writeAttribute("href", parentModel.getUrl(), null);
			writer.writeAttribute("title", parentModel.getTitle(), null);
			writer.writeText(parentModel.getLabel(), "value");
			writer.endElement("a");
			writer.startElement("ul", menu);
			for (MegaMenuModel subMenuModel : parentModel) {
				encodeSubMenu(writer, subMenuModel, menu);
			}
			writer.endElement("ul");
			writer.endElement("li");
		} else {
			writer.startElement("li", menu);
			writer.startElement("a", menu);
			writer.writeAttribute("href", parentModel.getUrl(), null);
			writer.writeAttribute("title", parentModel.getTitle(), null);
			writer.writeText(parentModel.getLabel(), "value");
			writer.endElement("a");
			writer.endElement("li");
		}
	}

	protected void encodeScript(FacesContext context, MegaMenu menu) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = menu.getClientId(context);
		startScript(writer, clientId);
		writer.write("$('.megamenu').dcVerticalMegaMenu({rowItems : '3',speed : 'fast',effect : 'slide',direction : 'right'});");
		endScript(writer);
	}

}
