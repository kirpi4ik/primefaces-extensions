package org.primefaces.extensions.component.tour;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

/**
 * DOCUMENT ME!
 * 
 * @author Dumitru Ciubenco
 * @since 1.0.0
 * @created Jan 11, 2013 1:54:08 PM
 */
public class TourButtonRenderer extends CoreRenderer {
	@Override
	public void encodeBegin(FacesContext fc, UIComponent component) throws IOException {
		ResponseWriter writer = fc.getResponseWriter();
		TourButton tourButton = (TourButton) component;
		writer.write(tourButton.getValue());
		writer.write(":");
		writer.write("'" + tourButton.getOnclick() + "'");
		writer.write(", ");
	}
}
