PrimeFacesExt.widget.ElFinder = PrimeFaces.widget.BaseWidget
		.extend({

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
				var options = {
					prime : this,
					url : '/jsf-demo/connector',
					lang : 'ru',
					onsuccess : function(responseXML) {
						var xmlDoc = $(responseXML.documentElement)
					},
					handlers : {
						init : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('init');
						},
						load : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('load');
						},
						enable : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('enable');
						},
						disable : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('disable');
						},
						open : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('open');
						},
						select : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('select');
						},
						dblclick : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('dblclick');
						},
						add : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('add');
						},
						remove : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('remove');
						},
						change : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('change');
						},
						upload : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('upload');
						},
						reload : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('reload');
						},
						changeclipboard : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('changeclipboard');
						},
						paste : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('paste');
						},
						rename : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('rename');
						},
						duplicate : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('duplicate');
						},
						download : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('download');
						},
						get : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('get');
						},
						resize : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('resize');
						},
						archive : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('archive');
						},
						extract : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('extract');
						},
						contextmenu : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('contextmenu');
						},
						hover : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('hover');
						},
						viewchange : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('viewchange');
						},
						sortchange : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('sortchange');
						},
						searchstart : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('searchstart');
						},
						search : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('search');
						},
						searchend : function(event, elfinderInstance) {
							elfinderInstance.options.prime
									.fireEvent('searchend');
						},
						destroy : function(event, elfinderInstance) {
							elfinderInstance.options.prime.fireEvent('destroy');
						}
					}
				}

				var elfinder = $('#' + this.id).elfinder(options).elfinder(
						'instance');
				elfinder['cfg']=cfg;
				
				PrimeFacesExt.removeWidgetScript(this.id);
			},

			fireEvent : function(eventName) {
				if (this.cfg.behaviors) {
					var callback = this.cfg.behaviors[eventName];
					if (callback) {
						var ext = {
							params : []
						};

						callback.call(this, null, ext);
					}
				}
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
		});