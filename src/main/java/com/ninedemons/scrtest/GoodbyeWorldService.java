package com.ninedemons.scrtest;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Deactivate;
import org.osgi.framework.BundleContext;
import java.util.Map;
import java.util.logging.Logger;


import org.osgi.framework.Constants;

/**
 * Example OSGi service using SCR annotations.
 */
@Component(immediate = true, metatype = true, configurationFactory= true, policy = ConfigurationPolicy.REQUIRE)
@Service(GoodbyeWorldService.class)
@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "CQ Blueprints"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Provides a friendly farewell.")
})
public class GoodbyeWorldService {

    private static final Logger LOG = Logger.getLogger(GoodbyeWorldService.class.getName());


	@Property(value="Goodbye ")
	static final String CONSTANT_NAME = "goodbye.prefix";

    public String getMessage(String name) {
        return String.format("Goodbye %s!", name);
    }

    @Activate
    void setup(BundleContext bundleContext, Map<String, Object> configuration) {
        LOG.warning("Activated with " + configuration);
	}

    @Deactivate
    void shutdown() {
        LOG.warning("Shutting down");
    }


}
