package utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class utilsForTest {

	public String getResourcePath(final String resourceName) {
		URL resource = this.getClass().getClassLoader().getResource(resourceName);
		if (resource == null) {
			throw new IllegalArgumentException("Given resource name was not found");
		}
		
		return resource.getPath();
	}
	
	public void removeFiles_CleanUp(String directory, String files){
		Path path = FileSystems.getDefault().getPath(directory, files);
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
