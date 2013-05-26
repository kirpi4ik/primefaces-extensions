package org.primefaces.extensions.component.elfinder.model;

import java.io.Serializable;
import java.util.List;
/**
 * {
  "debug":{
    "connector":"php",                     // (String) Connector type
    "phpver"   : "5.3.6",                  // (String) php version
    "time"     : 0.0749430656433,          // (Number) Execution time
    "memory"   : "3348Kb / 2507Kb / 128M", // (String) Used / Free / Available Memory
    "volumes"  : [                         // (Array)  Debugging by volume
      {
        "id"         : "l1_",              // (String) ID of the Volume
        "driver"     : "localfilesystem",  // (String) Driver type (class name)
        "mimeDetect" : "internal",         // (String) Method for determining mime type
        "imgLib"     : "gd"                // (String) Library for working with images
      }

    ],
    "mountErrors" : [                      // (Array) List of errors for not mounted volumes
      0 : "Root folder has not read and write permissions."
    ]
  }
}
 * @author kirpi4ik
 *
 */
public class Debug implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				memory;
	private String[]			mountErrors;
	private long				time;
	private String				upload;
	private List<Volume>		volumes;

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String[] getMountErrors() {
		return mountErrors;
	}

	public void setMountErrors(String[] mountErrors) {
		this.mountErrors = mountErrors;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

}
