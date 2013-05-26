package org.primefaces.extensions.component.elfinder.model;

import java.io.Serializable;

public class Volume implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				imgLib;
	private String				mimeDetect;
	private String				name;

	public String getImgLib() {
		return imgLib;
	}

	public void setImgLib(String imgLib) {
		this.imgLib = imgLib;
	}

	public String getMimeDetect() {
		return mimeDetect;
	}

	public void setMimeDetect(String mimeDetect) {
		this.mimeDetect = mimeDetect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
