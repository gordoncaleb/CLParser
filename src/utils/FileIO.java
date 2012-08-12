package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIO {
	
	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 */
	public static String readFile(String fileName) {

		URL fileURL = FileIO.class.getResource("doc/" + fileName);
		File aFile = null;

		if (fileURL == null) {
			try {
				aFile = new File(fileName);
				fileURL = aFile.toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}

		if (fileURL == null) {
			return null;
		} else {
			if (aFile != null) {
				if (!aFile.canRead()) {
					return null;
				}
			}
		}

		// ...checks on aFile are elided
		StringBuilder contents = new StringBuilder();

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			InputStreamReader is = new InputStreamReader(fileURL.openStream());
			BufferedReader input = new BufferedReader(is);
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

}
