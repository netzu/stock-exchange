package metastockDB;

import configuration.StockExchangeProperties;
import configuration.TestApplicationContext;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static paths.ResourcesUtils.getResourcePath;

public class MetastockFilesCollectionTest {

	@Test
	public void getListOfFilesMSTOnly(){

        final String propertiesPath =
            getResourcePath("metastockDB/MetastockFilesColectionTest/getListOfFilesMSTOnly/StockExchange.properties");
		StockExchangeProperties propertiesInstance = TestApplicationContext.getTestPropertiesInstance(propertiesPath);
		
		MetastockFilesCollection filesCollection = new MetastockFilesCollection(propertiesInstance);
				
		List<String> expectedFilesToBeFound = new ArrayList<String>();
		expectedFilesToBeFound.add("ADB0415.mst");
		expectedFilesToBeFound.add("AMBRA.mst");
		expectedFilesToBeFound.add("ASTORIA.mst");
		expectedFilesToBeFound.add("BLUMERANG.mst");
		expectedFilesToBeFound.add("BUMECH.mst");
		expectedFilesToBeFound.add("JUPITER.mst");
		expectedFilesToBeFound.add("PKPCARGO.mst");
				
		File[] curretnFileCollection = filesCollection.getListOfFiles();
		
		assertTrue("Not all elements has been found",allFilesFound(expectedFilesToBeFound, curretnFileCollection));
		assertEquals("Difretn number of elements",curretnFileCollection.length, expectedFilesToBeFound.size());
	}

	private boolean isFoundInFiles(String fileName, File[] fileCollection){
		
		for(int i=0; i<fileCollection.length; i++){
			if(fileCollection[i].getName().equals(fileName)){
				return true;
			}
		}		
		return false;
	}
	
	private boolean allFilesFound(List<String> toBeFound, File[] fileCollection){
		
		for(int i=0; i<toBeFound.size(); i++){
			if(isFoundInFiles(toBeFound.get(i), fileCollection) == false){
				return false;
			}
		}
		
		return true;
	}
}
