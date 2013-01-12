package org.primefaces.extensions.component.tour;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

/**
 * DOCUMENT ME!
 * 
 * @author Dumitru Ciubenco
 * @since 1.0.0
 * @created Jan 11, 2013 12:18:45 PM
 */
public class TourStepRenderer extends CoreRenderer {
	private UIComponent	facetTitle	= null;
	private UIComponent	facetBody	= null;
	private UIComponent	facetFooter	= null;

	@Override
	public void encodeBegin(FacesContext fc, UIComponent component) throws IOException {
		ResponseWriter writer = fc.getResponseWriter();
		TourStep tourStep = (TourStep) component;
		facetTitle = tourStep.getFacet("title");
		facetBody = tourStep.getFacet("body");
		facetFooter = tourStep.getFacet("footer");
		String stateId = tourStep.getStateId() != null ? tourStep.getStateId() : tourStep.getClientId().replaceAll(":", "_").replaceAll("#", "");
		writer.write(stateId);
		writer.write(": {");
		writer.write("title: '");
		facetTitle.encodeAll(fc);
		writer.write("', ");
		writer.write("html: '");
		facetBody.encodeAll(fc);
		writer.write("', ");
		if(tourStep.getFocus() != null) {
			writer.write("focus: " + tourStep.getFocus() + ", ");
		}
		if(tourStep.getForId() != null || tourStep.getForClass() != null) {
			writer.write("position: { ");
			writer.write("container: '");
			writer.write(tourStep.getForId() != null ? "#" + tourStep.findComponent(tourStep.getForId()).getClientId() : "." + tourStep.getForClass());
			writer.write("' ");
			if(tourStep.getArrowX() != null) {
				writer.write(", x:" + tourStep.getArrowX());
			}
			if(tourStep.getArrowY() != null) {
				writer.write(", y:" + tourStep.getArrowY());
			}
			if(tourStep.getWidth() != null) {
				writer.write(", width:" + tourStep.getWidth());
			}
			if(tourStep.getArrowPosition() != null) {
				writer.write(", arrow: '" + tourStep.getArrowPosition() + "'");
			}
			writer.write("}, ");
		}
	}

	@Override
	public void encodeEnd(FacesContext fc, UIComponent component) throws IOException {
		ResponseWriter writer = fc.getResponseWriter();
		TourStep tourStep = (TourStep) component;
		Tour tour = (Tour) tourStep.getParent();
		if(facetFooter.getChildCount() > 0) {
			List<UIComponent> buttons = facetFooter.getChildren();
			writer.write("buttons : { ");
			for(UIComponent uiComponent : buttons) {
				if(uiComponent instanceof TourButton) {
					uiComponent.encodeAll(fc);
				}
			}
			writer.write("}, ");
			writer.write("submit:" + tour.getWidgetVar() + "_tcf");
		}

		writer.write("}, ");
	}
}
