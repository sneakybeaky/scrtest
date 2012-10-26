package com.ninedemons.scrtest;

import org.apache.felix.scr.annotations.*;
import org.osgi.framework.Constants;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@Component(immediate = true)
@Service(AggregatorService.class)
@Properties({
        @Property(name = Constants.SERVICE_VENDOR, value = "CQ Blueprints"),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Collects all the goodbye services in one place")
})

public class AggregatorService {

    private static final Logger LOG = Logger.getLogger(AggregatorService.class.getName());

    @Reference (name="goodbyes",
            cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
            referenceInterface = GoodbyeWorldService.class,
            policy = ReferencePolicy.DYNAMIC,
            bind = "bindGoodbye",
            unbind = "unbindGoodbye"
    )
    public Set<GoodbyeWorldService> goodbyes = new HashSet<GoodbyeWorldService>();


    public void bindGoodbye(GoodbyeWorldService service) {
        LOG.info("Got new goodbye service with prefix " + service.getPrefix());
        goodbyes.add(service);
        sayGoodbye("Bob");
    }

    public void unbindGoodbye(GoodbyeWorldService service) {
        LOG.info("Removed goodbye service with prefix " + service.getPrefix());
        goodbyes.remove(service);
        sayGoodbye("Bob");
    }

    public void updatedGoodbye(GoodbyeWorldService service) {
        LOG.info("Updated goodbye service with prefix " + service.getPrefix());
        sayGoodbye("Bob");
    }

    public void sayGoodbye(String who) {
        for (GoodbyeWorldService goodbye : goodbyes) {
            LOG.info(goodbye.getMessage(who));
        }
    }
}
