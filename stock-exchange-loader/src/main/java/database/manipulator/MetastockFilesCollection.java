package database.manipulator;

import java.io.File;
import java.io.FileFilter;

import configuration.StockExchangeProperties;

public class MetastockFilesCollection {

	private File storageFolder = null;
	
	public MetastockFilesCollection(StockExchangeProperties properties){
		storageFolder = new File(properties.getCentralStorage());
	}

	public File[] getListOfFiles(){
		File [] fileList;
		FileFilter ff = new FileFilter() {
			
			public boolean accept(File arg0) {
				return arg0.getName().endsWith(".mst");
			}
		};
		
		fileList = storageFolder.listFiles(ff);
		
		return fileList;
	}
}
