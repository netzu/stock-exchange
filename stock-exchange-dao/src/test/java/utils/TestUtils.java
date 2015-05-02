package utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import configuration.StockExchangeProperties;

public class TestUtils {
	
    public StockExchangeProperties expandPaths(final StockExchangeProperties input) {
        return null;
    }
	
	public void removeFilesCleanUp(String directory, String files){
		Path path = FileSystems.getDefault().getPath(directory, files);
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
