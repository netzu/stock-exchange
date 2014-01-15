package download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;

public class MetastockDataDownloader {
	
		static StockExchangeProperties properties= ApplicationContext.getPropertiesInstance();
		private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDataDecompressor.class);
        
	    private HttpURLConnection establishedConnection(){
	    	URL url = null; 
	    	
	    	try {
	    		url = new URL(properties.getLinkToMetastock());
	    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    		connection.setRequestMethod("GET");
	    		
	    		return connection;
			} catch (IOException e) {
				log.error("Couldn't connect to metastock: ", e);
				throw new IllegalStateException("Nie udalo sie nawiazac polaczenia z metastock", e);
			}
	    }
	    
	    private void downloadFile(HttpURLConnection connection){
	    	try {
	    		log.info("Start downloading metastock file from network");
	    		InputStream in = connection.getInputStream();	    		
	    			    		
	    		FileOutputStream out = new FileOutputStream(properties.getDirOfZippedMetastock() + properties.getMetastockFileName());
	    		startStreamingFile(in, out, 1024);
	    		
	    		log.info("The " + properties.getMetastockFileName() + " file has been saved in this location: " + properties.getDirOfZippedMetastock());
	    		out.close();
			} catch (IOException e) {
				log.error("Streaming of metastock date to local disck failed: ", e);
				throw new IllegalStateException("Nie dziala streamowanie danych Metastock", e);
			}
	    }
	    
	    private static void startStreamingFile(InputStream input, OutputStream output, int bufferSize) throws IOException {
	        byte[] buffer = new byte[bufferSize];
	        int n = input.read(buffer);
	        while (n >= 0) {
	          output.write(buffer, 0, n);
	          n = input.read(buffer);
	        }
	        output.flush();
	      }
	    

        public void downloadData(){
        	downloadFile(establishedConnection());
        }
}
