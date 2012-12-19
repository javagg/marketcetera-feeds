package org.freequant.marketcetera.marketdata.ctp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataModule;
import org.marketcetera.marketdata.MarketDataFeed;
import org.marketcetera.module.ModuleURN;

public class CtpFeedModule extends AbstractMarketDataModule<CtpFeedToken, CtpFeedCredentials> implements CtpFeedMXBean {
    static private void loadDll(final String filename) throws IOException {
  		  InputStream in = CtpFeedModule.class.getClass().getResource(filename).openStream();
  		  File dll = File.createTempFile(filename, ".dll");
  		  FileOutputStream out = new FileOutputStream(dll); 

  		  int i;
  		  byte [] buf = new byte[1024];
  		  while((i=in.read(buf))!=-1) {
  		   out.write(buf,0,i);
  		  }
        
  		  in.close();
  		  out.close();
  		  dll.deleteOnExit();
  		  System.load(dll.toString());
    }
	static {
    	try {
    		loadDll("libfreequant");
//    		  InputStream in = CtpFeedModule.class.getClass().getResource("/NTV.dll").openStream();
//    		  File dll = File.createTempFile("NTV", ".dll");
//    		  FileOutputStream out = new FileOutputStream(dll); 
//
//    		  int i;
//    		  byte [] buf = new byte[1024];
//    		  while((i=in.read(buf))!=-1) {
//    		   out.write(buf,0,i);
//    		  }
//    		  
//    		  in.close();
//    		  out.close();
//    		  dll.deleteOnExit();
//    		 
//    		  System.load(dll.toString());// 
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	
	protected CtpFeedModule(ModuleURN inInstanceURN, MarketDataFeed<CtpFeedToken, CtpFeedCredentials> inFeed) {
        super(inInstanceURN, inFeed);
    }

    @Override
    protected CtpFeedCredentials getCredentials() throws CoreException {
        return new CtpFeedCredentials();
    }
    @Override 
    public String getPassword() {
		return password;
	}
    
    @Override 
    public void setPassword(String inPassword) {
    	password = inPassword;
    }

	private volatile String address;
	private volatile String userId;
	private volatile String brokerId;
	private volatile String password;
	
    private volatile long replayRate = 1000;
    private volatile String marketdataDirectory;
}
