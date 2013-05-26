package org.primefaces.extensions.component.elfinder.model;

import java.io.Serializable;
/**
 * {
    "name"   : "Images",             // (String) name of file/dir. Required
    "hash"   : "l0_SW1hZ2Vz",        // (String) hash of current file/dir path, first symbol must be letter, symbols before _underline_ - volume id, Required. 
    "phash"  : "l0_Lw",              // (String) hash of parent directory. Required except roots dirs.
    "mime"   : "directory",          // (String) mime type. Required.
    "ts"     : 1334163643,           // (Number) file modification time in unix timestamp. Required.
    "date"   : "30 Jan 2010 14:25",  // (String) last modification time (mime). Depricated but yet supported. Use ts instead.
    "size"   : 12345,                // (Number) file size in bytes
    "dirs"   : 1,                    // (Number) Only for directories. Marks if directory has child directories inside it. 0 (or not set) - no, 1 - yes. Do not need to calculate amount.
    "read"   : 1,                    // (Number) is readable
    "write"  : 1,                    // (Number) is writable
    "locked" : 0,                    // (Number) is file locked. If locked that object cannot be deleted and renamed
    "tmb"    : 'bac0d45b625f8d4633435ffbd52ca495.png' // (String) Only for images. Thumbnail file name, if file do not have thumbnail yet, but it can be generated than it must have value "1"
    "alias"  : "files/images",       // (String) For symlinks only. Symlink target path.
    "thash"  : "l1_c2NhbnMy",        // (String) For symlinks only. Symlink target hash.
    "dim"    : "640x480"             // (String) For images - file dimensions. Optionally.
    "volumeid" : "l1_"               // (String) Volume id. For root dir only.
}
 * @author kirpi4ik
 *
 */
public class File implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				hash;
	private String				phash;
	private int					locked;
	private String				mime;
	private String				name;
	private int					read;
	private long				size;
	private long				ts;
	private int					write;
	private int					dirs;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPhash() {
		return phash;
	}

	public void setPhash(String phash) {
		this.phash = phash;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public int getWrite() {
		return write;
	}

	public void setWrite(int write) {
		this.write = write;
	}

	public int getDirs() {
		return dirs;
	}

	public void setDirs(int dirs) {
		this.dirs = dirs;
	}

}
