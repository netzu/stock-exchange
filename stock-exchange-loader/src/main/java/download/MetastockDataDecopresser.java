package download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

import DAO.UpdateDBWithFreshnestData;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;

public class MetastockDataDecopresser {
	
	static StockExchangeProperties properties= ApplicationContext.getPropertiesInstance();
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDataDecopresser.class);
	
	private static final void copyInputStream(InputStream in, OutputStream out)
			  throws IOException
			  {
			    byte[] buffer = new byte[1024];
			    int len;

			    while((len = in.read(buffer)) >= 0)
			      out.write(buffer, 0, len);

			    in.close();
			    out.close();
			  }
	
	public void UnZipMetastockData(){
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

	        //System.out.println("Extracting file: " + entry.getName());
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
	
	/*public void UnZipMetastockData(){

		try {
			ZipFile zipFile = new ZipFile(properties.getDirOfZippedMetastock() + properties.getMetastockFileName());
			
			Enumeration entries = zipFile.entries();
			
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) entries.nextElement();
				String name = zipEntry.getName();
				
				System.out.printf("name: %-20s | size: %6d\n", name, zipEntry.getSize());

				File file = new File(properties.getMetastockUnzipDir() + name);
				if (name.endsWith("/")) {
					file.mkdir();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();

			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

