PrimeFacesExt.widget.ElFinder = PrimeFaces.widget.BaseWidget.extend({
	/**
	 * Initializes the widget.
	 * 
	 * @param {object}
	 *            cfg The widget configuration.
	 */
	init : function(cfg) {
		this.id = cfg.id;
		this.cfg = cfg;
		this.initialized = false;
		$('#' + this.id).elfinder({
			url : '/jsf-demo/connector',
			lang : 'ru'
		}).elfinder('instance');
		PrimeFacesExt.removeWidgetScript(this.id);
	},
	/**
	 * Initializes the settings.
	 * 
	 * @private
	 */
	initializeLazy : function() {
		if (!this.initialized) {
			this.initialized = true;
		}
	},
	/**
	 * Reloads the widget.
	 */
	reload : function() {
		this.initialized = false;
		this.initializeLazy();
	},

	/**
	 * Fires the rotate event.
	 * 
	 * @private
	 */
	fireHelpEvent : function() {
		if (this.cfg.behaviors) {
			var callback = this.cfg.behaviors['rotate'];
			if (callback) {
				var ext = {
					params : [ {
						name : this.id + '_degree',
						value : this.degree
					} ]
				};

				callback.call(this, null, ext);
			}
		}
	},
});