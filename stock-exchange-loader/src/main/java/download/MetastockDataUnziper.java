package download;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MetastockDataUnziper {
	
	private static final int BUFFER_SIZE = 1024;
	private static StockExchangeProperties properties= ApplicationContext.getPropertiesInstance();
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDataUnziper.class);
	
	private static final void copyInputStream(InputStream in, OutputStream out)
			  throws IOException
			  {
			    byte[] buffer = new byte[BUFFER_SIZE];
			    int len;

			    while((len = in.read(buffer)) >= 0){
			      out.write(buffer, 0, len);
			    }

			    in.close();
			    out.close();
			  }
	
	public void unZipMetastockData(){
		Enumeration entries;
	    ZipFile zipFile;

	    try {
	      zipFile = new ZipFile(properties.getDirOfZippedMetastock() + properties.getMetastockFileName());
	      entries = zipFile.entries();

	      log.info("Unzipping " + zipFile.getName() + " file");
	      while(entries.hasMoreElements()) {
	    	ZipEntry entry = (ZipEntry)entries.nextElement();

	        if(entry.isDirectory()) {
	          // Assume directories are stored parents first then children.
	         log.info("Extracting directory: " + entry.getName());
	          (new File(properties.getMetastockUnzipDir() + entry.getName())).mkdir();
	          continue;
	        }

	        copyInputStream(zipFile.getInputStream(entry),
	           new BufferedOutputStream(new FileOutputStream(properties.getMetastockUnzipDir() + entry.getName())));
	      }
	      log.info("Finished unzipping file");
	      zipFile.close();
	    } catch (IOException ioe) {
	      log.error("Unhandled exception:", ioe);
	      ioe.printStackTrace();
	      return;
	    }
	  }
	}


