package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.module.Module;
import org.marketcetera.module.ModuleCreationException;
import org.marketcetera.module.ModuleFactory;
import org.marketcetera.module.ModuleURN;
import org.marketcetera.util.log.I18NBoundMessage;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedModuleFactory extends ModuleFactory {
    protected CtpFeedModuleFactory(ModuleURN inURN, I18NBoundMessage inDescription, boolean inMultipleInstances, boolean inAutoInstantiate, Class... inParameterTypes) {
        super(inURN, inDescription, inMultipleInstances, inAutoInstantiate, inParameterTypes);
    }

    @Override
    public Module create(Object... objects) throws ModuleCreationException {
        Module module = null;
        try {
            module = new CtpFeedModule(new ModuleURN("metc:mdata:ctp"), new CtpFeedFactory().getMarketDataFeed());
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return module;
    }
}
