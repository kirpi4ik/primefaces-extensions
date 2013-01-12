## Chosen component based on : http://harvesthq.github.com/chosen/
--------------------------------------------------------------------
<pre>
<code>
&lt;pe:chosen value=&quot;#{bean.selectedCountry}&quot; required=&quot;true&quot; label=&quot;Select Country&quot; style=&quot;width:150px&quot; allowSingleDeselect=&quot;true&quot; noResultsText=&quot;No results&quot;&gt;
	&lt;f:selectItems value=&quot;#{bean.countries}&quot; /&gt;
&lt;/pe:chosen&gt;
</code>
</pre>

## Tour component based on http://trentrichardson.com/Impromptu/
------------------------------------------------------------------
<pre>
<code>
&lt;pe:tour widgetVar=&quot;help&quot; cookie=&quot;true&quot;&gt;
  &lt;pe:tourStep width=&quot;300&quot; stateId=&quot;bau1&quot; forClass=&quot;page-class&quot; arrowX=&quot;250&quot; arrowY=&quot;70&quot; arrowPosition=&quot;lt&quot;&gt;
		&lt;f:facet name=&quot;title&quot;&gt;
			&lt;h:outputText value=&quot;Step 1&quot; /&gt;
		&lt;/f:facet&gt;
		&lt;f:facet name=&quot;body&quot;&gt;
			&lt;h:outputText value=&quot;Welcome to our tour&quot; /&gt;
		&lt;/f:facet&gt;
		&lt;f:facet name=&quot;footer&quot;&gt;
			&lt;pe:tourButton value=&quot;Next&quot; onclick=&quot;bau2&quot; /&gt;
			&lt;pe:tourButton value=&quot;Close&quot; onclick=&quot;close&quot; /&gt;
		&lt;/f:facet&gt;
	&lt;/pe:tourStep&gt;
	&lt;pe:tourStep width=&quot;300&quot; stateId=&quot;bau2&quot; forId=&quot;testimonial&quot; arrowX=&quot;180&quot; arrowY=&quot;-140&quot; arrowPosition=&quot;bc&quot;&gt;
		&lt;f:facet name=&quot;title&quot;&gt;
			&lt;h:outputText value=&quot;Step 1&quot; /&gt;
		&lt;/f:facet&gt;
		&lt;f:facet name=&quot;body&quot;&gt;
			&lt;h:outputText value=&quot;Testimonials&quot; /&gt;
		&lt;/f:facet&gt;
		&lt;f:facet name=&quot;footer&quot;&gt;
			&lt;pe:tourButton value=&quot;Previous&quot; onclick=&quot;back&quot; /&gt;
			&lt;pe:tourButton value=&quot;Close&quot; onclick=&quot;close&quot; /&gt;
		&lt;/f:facet&gt;
	&lt;/pe:tourStep&gt;
&lt;/pe:tour&gt;
</code>
</pre>

## Vertical Megamenu like: http://www.designchemical.com/lab/jquery-vertical-mega-menu-plugin/examples/
<pre>
<code>
&lt;pe:megamenu id=&quot;tree&quot; styleClass=&quot;megamenu&quot; model=&quot;#{bean.prodCategories}&quot; /&gt;
</code>
</pre>
