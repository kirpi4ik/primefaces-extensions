package org.primefaces.extensions.component.chosen;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import org.primefaces.renderkit.SelectOneRenderer;

/**
 * 
 * @author Dumitru Ciubenco
 * @since 0.0.1
 * @created Nov 7, 2011 12:15:20 PM
 */
public class ChosenRenderer extends SelectOneRenderer {

	@Override
	public void decode(final FacesContext context, final UIComponent component) {
		if (!shouldDecode(component)) {
			return;
		}
		final Chosen chosen = (Chosen) component;
		// set value
		final String paramId = getSubmitParam(context, chosen);
		final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		if (params.containsKey(paramId)) {
			// decode behaviors
			decodeBehaviors(context, component);
			chosen.setSubmittedValue(params.get(paramId));
		}

	}

	@Override
	public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
		final Chosen chosen = (Chosen) component;

		encodeMarkup(context, chosen);
		encodeScript(context, chosen);
	}

	protected void encodeMarkup(final FacesContext context, final Chosen chosen) throws IOException {
		final ResponseWriter writer = context.getResponseWriter();
		List<SelectItem> selectItems = getSelectItems(context, chosen);
		final String clientId = chosen.getClientId(context);
		Converter converter = null;
		Object values = getValues(chosen);
		Object submittedValues = getSubmittedValues(chosen);

		String style = chosen.getStyle();
		String styleclass = chosen.getStyleClass();
		styleclass = styleclass == null ? Chosen.STYLE_CLASS : Chosen.STYLE_CLASS + " " + styleclass;
		styleclass = chosen.isDisabled() ? styleclass + " ui-state-disabled" : styleclass;

		writer.startElement("div", chosen);

		if (!chosen.isValid()) {
			writer.writeAttribute("style", "border:1px solid #CD0A0A;", null);
		}

		writer.startElement("select", null);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("data-placeholder", chosen.getLabel(), null);

		writer.writeAttribute("class", styleclass, null);
		if (style != null) {
			writer.writeAttribute("style", style, "style");
		}
		if (chosen.getMultiple()) {
			writer.writeAttribute("multiple", "multiple", null);
		}

		encodeSelectItems(context, chosen, selectItems, values, submittedValues, converter);

		writer.endElement("select");
		writer.endElement("div");
	}

	private void encodeSelectItems(FacesContext context, Chosen chosen, List<SelectItem> selectItems, Object values, Object submittedValues, Converter converter) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("option", null);
		writer.writeAttribute("value", "", null);
		writer.endElement("option");

		for(SelectItem selectItem : selectItems) {
			encodeOption(context, chosen, selectItem, values, submittedValues, converter);
		}

	}

	protected void encodeOption(FacesContext context, Chosen menu, SelectItem option, Object values, Object submittedValues, Converter converter) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String itemValueAsString = getOptionAsString(context, menu, converter, option.getValue());
		boolean disabled = option.isDisabled() || menu.isDisabled();

		Object valuesArray;
		Object itemValue;
		if (submittedValues != null) {
			valuesArray = submittedValues;
			itemValue = itemValueAsString;
		} else {
			valuesArray = values;
			itemValue = option.getValue();
		}

		boolean selected = isSelected(context, menu, itemValue, valuesArray, converter);
		if (option.isNoSelectionOption() && values != null && !selected) {
			return;
		}

		writer.startElement("option", null);
		writer.writeAttribute("value", itemValueAsString, null);
		if (disabled)
			writer.writeAttribute("disabled", "disabled", null);
		if (selected)
			writer.writeAttribute("selected", "selected", null);

		if (option.isEscape())
			writer.writeText(option.getLabel(), "value");
		else writer.write(option.getLabel());

		writer.endElement("option");

	}

	protected void encodeScript(final FacesContext context, final Chosen chosen) throws IOException {
		final ResponseWriter writer = context.getResponseWriter();
		final String clientId = chosen.getClientId(context);

		StringBuffer sb = new StringBuffer();
		if (chosen.getNoResultsText() != null) {
			sb.append("no_results_text: \"" + chosen.getNoResultsText() + "\",");
		} else {
			sb.append("no_results_text: \"No results matched\",");
		}
		sb.append("allow_single_deselect: " + chosen.getAllowSingleDeselect() + ", ");
		sb.append("disabled: " + chosen.isDisabled());

		startScript(writer, clientId);
		writer.write("PrimeFaces.cw('Chosen','" + chosen.resolveWidgetVar() + "',{");
		writer.write("id:'" + clientId + "',");
		writer.write(sb.toString());
		encodeClientBehaviors(context, chosen);
		writer.write("});");

		endScript(writer);
	}

	@Override
	public Object getConvertedValue(final FacesContext context, final UIComponent component, final Object submittedValue) {
		final Chosen chosen = (Chosen) component;
		final String value = (String) submittedValue;
		final Converter converter = chosen.getConverter();

		if (converter != null) {
			return converter.getAsObject(context, chosen, value);
		}

		final ValueExpression ve = chosen.getValueExpression("value");

		if (ve != null) {
			final Class<?> valueType = ve.getType(context.getELContext());
			final Converter converterForType = context.getApplication().createConverter(valueType);

			if (converterForType != null) {
				return converterForType.getAsObject(context, chosen, value);
			}
		}
		return value;
	}

	@Override
	public void encodeChildren(FacesContext facesContext, UIComponent component) throws IOException {
		// Rendering happens on encodeEnd
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	protected String getSubmitParam(FacesContext context, UISelectOne selectOne) {
		return selectOne.getClientId(context) + "_input";
	}
}
