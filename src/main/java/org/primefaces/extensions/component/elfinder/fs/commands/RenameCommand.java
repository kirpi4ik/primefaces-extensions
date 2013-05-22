package org.primefaces.extensions.component.elfinder.fs.commands;

import java.io.File;

import org.primefaces.extensions.component.elfinder.fs.ConnectorException;
import org.primefaces.extensions.component.elfinder.fs.FileActionEnum;
import org.primefaces.extensions.component.elfinder.fs.FsException;

/**
 * @author Antoine Walter (www.anw.fr)
 * @date 29 aug. 2011
 * @version $Id$
 * @license BSD
 */
public class RenameCommand extends AbstractCommandOverride {

	@Override
	public void execute() throws ConnectorException {
		File dirCurrent = getExistingDir(getParam("current"), FileActionEnum.CREATE_FILE);
		if (dirCurrent != null) {
			File targetFile = getExistingFile(getParam("target"), dirCurrent, FileActionEnum.DELETE);
			File futureFile = getNewFile(getParam("name"), dirCurrent, FileActionEnum.WRITE);
			try {
				getFs().renameFileOrDirectory(targetFile, futureFile);
			} catch (FsException e) {
				throw new ConnectorException("Unable to rename file", e);
			}

			putResponse("select", _hash(targetFile));
			_content(dirCurrent, true);
		}
	}

}
