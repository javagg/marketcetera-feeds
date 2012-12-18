package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataModule;
import org.marketcetera.marketdata.MarketDataFeed;
import org.marketcetera.module.ModuleURN;

public class CtpFeedModule extends AbstractMarketDataModule<CtpFeedToken, CtpFeedCredentials> implements CtpFeedMXBean {
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
