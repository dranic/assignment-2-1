package org.example.ap;

import eu.ibagroup.easyrpa.engine.boot.ApModuleRunner;
import eu.ibagroup.easyrpa.engine.boot.configuration.DevelopmentConfigurationModule;

public class LocalRunner {
    public static void main(String[] arg) {
        //use this code to test your Automation Process
        ApModuleRunner.localLaunch(Module.class, new DevelopmentConfigurationModule());
    }
}
