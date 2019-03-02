import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

	// Initialise constructor
	public FileHandler() {
	}

	// Return file from given path
	public File getFile(String filePath) {
		File newFile = new File(filePath);

		return newFile;
	}

	// Return array of bytes from file at given path
	public byte[] readFileBytes(String filePath) {
		File file = new File(filePath);
		FileInputStream fileStream;
		byte[] fileBytes = null;

		try {
			fileStream = new FileInputStream(file);
			fileBytes = new byte[(int) file.length()];
			fileStream.read(fileBytes);
			fileStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileBytes;
	}
}
