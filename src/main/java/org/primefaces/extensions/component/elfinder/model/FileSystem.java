package org.primefaces.extensions.component.elfinder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileSystem implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				api;
	private File				cwd;
	private Debug				debug;
	private String[]			netDrivers;
	private String				uplMaxSize;
	private Options				options;
	private List<File>			files				= new ArrayList<File>();

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public File getCwd() {
		return cwd;
	}

	public void setCwd(File cwd) {
		this.cwd = cwd;
	}

	public Debug getDebug() {
		return debug;
	}

	public void setDebug(Debug debug) {
		this.debug = debug;
	}

	public String[] getNetDrivers() {
		return netDrivers;
	}

	public void setNetDrivers(String[] netDrivers) {
		this.netDrivers = netDrivers;
	}

	public String getUplMaxSize() {
		return uplMaxSize;
	}

	public void setUplMaxSize(String uplMaxSize) {
		this.uplMaxSize = uplMaxSize;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

}
