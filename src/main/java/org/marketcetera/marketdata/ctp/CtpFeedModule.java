package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataModule;
import org.marketcetera.marketdata.AssetClass;
import org.marketcetera.marketdata.Capability;
import org.marketcetera.marketdata.MarketDataFeed;
import org.marketcetera.module.ModuleURN;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedModule extends AbstractMarketDataModule<CtpFeedToken, CtpFeedCredentials> {
    protected CtpFeedModule(ModuleURN inInstanceURN, MarketDataFeed<CtpFeedToken, CtpFeedCredentials> inFeed) {
        super(inInstanceURN, inFeed);
    }

    @Override
    public void reconnect() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<Capability> getCapabilities() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<AssetClass> getAssetClasses() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected CtpFeedCredentials getCredentials() throws CoreException {
        return new CtpFeedCredentials();
    }
}
