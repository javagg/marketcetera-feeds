package org.freequant.marketcetera.marketdata.ctp;

import org.marketcetera.module.ModuleCreationException;

public final class JarLoader {
	public static void main(String[] args) {
		try {
			System.out.println(new CtpFeedModuleFactory().create().toString());
		} catch (ModuleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
