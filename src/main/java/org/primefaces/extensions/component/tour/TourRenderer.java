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
 * @created Jan 11, 2013 3:31:34 PM
 */
public class TourRenderer extends CoreRenderer {

	@Override
	public void encodeBegin(FacesContext fc, UIComponent component) throws IOException {
		ResponseWriter writer = fc.getResponseWriter();
		Tour tour = (Tour) component;
		startScript(writer, tour.getClientId());

		writer.write("var " + tour.getWidgetVar() + "_tcf = function(e,v,m,f){ ");
		writer.write("if(v === 'back'){$.prompt.prevState();return false; }");
		writer.write("else if(v === 'next'){$.prompt.nextState();return false; }");
		writer.write("else if(v === 'close'){" + tour.getWidgetVar() + ".hide(); return false;}");
		writer.write("else {$.prompt.goToState(v);return false; }");
		writer.write("}; ");

		writer.write("var t_" + tour.getWidgetVar() + " = {");
	}

	@Override
	public void encodeEnd(FacesContext fc, UIComponent component) throws IOException {
		ResponseWriter writer = fc.getResponseWriter();
		Tour tour = (Tour) component;
		writer.write("}; ");
		if(tour.getCookie()) {
			writer.write("if($.cookie('t_" + tour.getWidgetVar() + "')==null) {");
			writer.write("$.prompt(t_" + tour.getWidgetVar() + ");");
			writer.write("}");
		}

		writer.write("var " + tour.getWidgetVar() + " = {");
		writer.write("show:function(){$.prompt(t_" + tour.getWidgetVar() + "); $.cookie('t_" + tour.getWidgetVar() + "', null); return false;},");
		writer.write("hide:function(){$.prompt.close(); $.cookie('t_" + tour.getWidgetVar() + "', 'closed'); return false; return false;}");
		writer.write("};");
		endScript(writer);
	}
}
