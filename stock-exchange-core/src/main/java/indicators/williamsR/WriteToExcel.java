package indicators.williamsR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToExcel {
	
	public void CreateFileForWilliamsIndicator(String destinationPath, String nameOfTheFile, WilliamsRIndicator wiRIndicator){
		
		try {
			 
			String contentOfFile;
			ArrayList<WilliamsRData> williams = wiRIndicator.getWilliamsRCollectionForTicker().getWilliamsR();
			
			contentOfFile = "highestHigh,lowestLow,currentClose,date,williamsR\n";
					
			for(int i=0; i<williams.size(); i++){
				contentOfFile = contentOfFile + williams.get(i).getHighestHigh() + ",";
				contentOfFile = contentOfFile + williams.get(i).getLowestLow() + ",";
				contentOfFile = contentOfFile + williams.get(i).getCurrentClose() + ",";
				contentOfFile = contentOfFile + williams.get(i).getDate() + ",";
				contentOfFile = contentOfFile + williams.get(i).getWilliamsR()+ "\n";
			}
	
			File file = new File(destinationPath+nameOfTheFile);
	
			// if file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contentOfFile);
			bw.close();
	
			System.out.println("Done");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
