package org.marketcetera.marketdata.ctp;

import static org.marketcetera.marketdata.ctp.Messages.PROVIDER_DESCRIPTION;

import org.marketcetera.core.CoreException;
import org.marketcetera.module.Module;
import org.marketcetera.module.ModuleCreationException;
import org.marketcetera.module.ModuleFactory;
import org.marketcetera.module.ModuleURN;

public class CtpFeedModuleFactory extends ModuleFactory {
	
	public CtpFeedModuleFactory() {
		super(PROVIDER_URN, PROVIDER_DESCRIPTION, false, false);	
	}

    @Override
    public Module create(Object... objects) throws ModuleCreationException {
    	Module module = null;
        try {
            module = new CtpFeedModule(INSTANCE_URN, new CtpFeedFactory().getMarketDataFeed());
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return module;
    }
    
//    public static final String IDENTIFIER = "ctp";
    public static final ModuleURN PROVIDER_URN = new ModuleURN("metc:mdata:ctp");  //$NON-NLS-1$
    public static final ModuleURN INSTANCE_URN = new ModuleURN(PROVIDER_URN,  "single");
}
