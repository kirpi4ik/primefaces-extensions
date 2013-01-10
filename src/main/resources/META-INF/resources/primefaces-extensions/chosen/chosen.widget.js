/**
 * PrimeFaces Extensions Chosen Widget
 * 
 * @constructor
 */
PrimeFaces.widget.Chosen = PrimeFaces.widget.BaseWidget
		.extend({
			init : function(cfg) {
				this._super(cfg);
				var _self = this;
				chosen = this.jq.find('select');
				this.changeEventDefined = this.cfg.behaviors
						&& this.cfg.behaviors['change'];

				this.options = {};
				if (this.cfg.no_results_text) {
					this.options.no_results_text = this.cfg.no_results_text;
				}
				if (this.cfg.allow_single_deselect) {
					this.options.allow_single_deselect = this.cfg.allow_single_deselect;
				}
				$(PrimeFaces.escapeClientId(this.cfg.id)).chosen(this.options).change($.proxy(function() {_self.change(event)}, this));

				if(this.cfg.disabled){
					$(PrimeFaces.escapeClientId(this.cfg.id)).attr('disabled', true).trigger('liszt:updated');
				}
				
				this.jq.bind('change', function(event) {
					_self.change(event);
				});
			},
			change : function(eventName) {
				if (this.cfg.behaviors) {
					var callback = this.cfg.behaviors[eventName.type];
					if (callback) {
						var ext = {
							params : {}
						};
						if (this.jq[0].multiple) {
							ext.params[this.id + '_input'] = $.toJSON(
									$(this.jq[0]).children(':selected').map(
											function() {
												return $(this).val();
											}).get()).replace(/\"/g, '\'');
						} else {
							ext.params[this.id + '_input'] = this.jq[0].value;
						}
						callback.call(this, null, ext);
					}
				}
			}
		});