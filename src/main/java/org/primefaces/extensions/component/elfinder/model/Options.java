package org.primefaces.extensions.component.elfinder.model;

import java.io.Serializable;
/**
 * {
  options : {
    "path"          : "files/folder42",                                 // (String) Current folder path
    "url"           : "http://localhost/elfinder/files/folder42/",      // (String) Current folder URL
    "tmbURL"        : "http://localhost/elfinder/files/folder42/.tmb/", // (String) Thumbnails folder URL
    "separator"     : "/",                                              // (String) Разделитель пути для текущего тома
    "disabled"      : [],                                               // (Array) List of commands not allowed (disabled) on this volume
    "copyOverwrite" : 1,                                                // (Number) Whether or not to overwrite files with the same name on the current volume
    "archivers"     : {                                                 // (Object) Archive settings
      "create"  : [
        0 : "application/x-tar",
        1 : "application/x-gzip"
      ],                                                   // (Array)  List of the mime type of archives which can be created
      "extract" : [
        0 : "application/x-tar",
        1 : "application/x-gzip"
      ]                                                    // (Array)  List of the mime types that can be extracted / unpacked
    }
  }
}
 * @author kirpi4ik
 *
 */
public class Options implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private int					copyOverwrite;
	private String				path;
	private String				separator;
	private String				tmbUrl;
	private String				url;

	public int getCopyOverwrite() {
		return copyOverwrite;
	}

	public void setCopyOverwrite(int copyOverwrite) {
		this.copyOverwrite = copyOverwrite;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getTmbUrl() {
		return tmbUrl;
	}

	public void setTmbUrl(String tmbUrl) {
		this.tmbUrl = tmbUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
