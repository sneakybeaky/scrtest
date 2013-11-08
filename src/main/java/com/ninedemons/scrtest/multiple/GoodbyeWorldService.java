package com.ninedemons.scrtest.multiple;

import com.ninedemons.scrtest.multiple.common.GoodbyeWorld;
import org.apache.felix.scr.annotations.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Example OSGi service using SCR annotations.
 */
@Component(
        metatype = true,
        configurationFactory= true,
        policy = ConfigurationPolicy.REQUIRE,
        label = "Goodbye World Config"
)

@Service(GoodbyeWorld.class)

@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "Nine Demons"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Provides a friendly farewell.")
})

public class GoodbyeWorldService implements GoodbyeWorld {

    private static final Logger LOG = Logger.getLogger(GoodbyeWorldService.class.getName());

    String prefix;


	@Property(value="Goodbye ")
	static final String CONSTANT_NAME = "goodbye.prefix";

    @Override
    public String getMessage(String name) {
        return String.format("%s %s!", this.prefix, name);
    }

    @Activate
    void setup(BundleContext bundleContext, Map<String, Object> configuration) {
        LOG.warning("Activated with " + configuration);
        modified(configuration);
	}

    @Deactivate
    void shutdown() {
        LOG.warning("Shutting down");
    }

    @Modified
    void modified( Map<String, Object> configuration) {
        LOG.warning("Modified with " + configuration);

        this.prefix = (String) configuration.get(CONSTANT_NAME);
    }


    @Override
    public String toString() {
        return "GoodbyeWorldService{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
