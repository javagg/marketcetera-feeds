package org.freequant.marketcetera.marketdata.ctp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataModule;
import org.marketcetera.marketdata.MarketDataFeed;
import org.marketcetera.module.ModuleURN;

public class CtpFeedModule extends AbstractMarketDataModule<CtpFeedToken, CtpFeedCredentials> implements CtpFeedMXBean {
//    static private void loadLib(final String name) throws IOException {
//    	String suffix = "";
//    	String osName = System.getProperty("os.name");
//    	if (osName.substring(0, 7).equals("Windows")) {
//    		suffix = ".dll";
//    	} else if (osName.equals("Linux")) {
//    		suffix = ".so";
//		}
//		InputStream in = CtpFeedModule.class.getClass().getResource("/" + name + suffix).openStream();
//		File lib = File.createTempFile(name, suffix);
//		FileOutputStream out = new FileOutputStream(lib);
//
//		int i;
//		byte[] buf = new byte[1024];
//		while ((i = in.read(buf)) != -1) {
//			out.write(buf, 0, i);
//		}
//
//		in.close();
//		out.close();
//		lib.deleteOnExit();
//		System.load(lib.toString());
//		System.loadLibrary(lib.toString());
//    }
//    
//	static {
////    	try {
////    		loadLib("thostmduserapi");
////    	} catch (Exception e) {
////    		e.printStackTrace();
////    	}
//    }
	
	protected CtpFeedModule(ModuleURN inInstanceURN, MarketDataFeed<CtpFeedToken, CtpFeedCredentials> inFeed) {
        super(inInstanceURN, inFeed);
    }

    @Override
    protected CtpFeedCredentials getCredentials() throws CoreException {
        return new CtpFeedCredentials();
    }
    public String getPassword() {
		return password;
	}
    
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
