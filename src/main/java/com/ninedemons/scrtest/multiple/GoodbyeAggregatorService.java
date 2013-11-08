package com.ninedemons.scrtest.multiple;

import com.ninedemons.scrtest.multiple.common.GoodbyeWorld;
import org.apache.felix.scr.annotations.*;
import org.osgi.framework.Constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@Component(immediate = true)
@Service(GoodbyeAggregatorService.class)
@Properties({
        @Property(name = Constants.SERVICE_VENDOR, value = "Nine Demons"),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Collects all the goodbye services in one place")
})

public class GoodbyeAggregatorService {

    private static final Logger LOG = Logger.getLogger(GoodbyeAggregatorService.class.getName());

    @Reference (
            cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
            referenceInterface = GoodbyeWorld.class,
            policy = ReferencePolicy.DYNAMIC,
            bind = "bindGoodbye",
            unbind = "unbindGoodbye"
    )
    public Set<GoodbyeWorld> goodbyes =  Collections.synchronizedSet(new HashSet<GoodbyeWorld>());


    public void bindGoodbye(GoodbyeWorld service) {
        LOG.info("Got new goodbye service " + service);
        goodbyes.add(service);
        sayGoodbye("Bob");
    }

    public void unbindGoodbye(GoodbyeWorld service) {
        LOG.info("Removed goodbye service " + service);
        goodbyes.remove(service);
        sayGoodbye("Bob");
    }


    public void sayGoodbye(String who) {
        for (GoodbyeWorld goodbye : goodbyes) {
            LOG.info(goodbye.getMessage(who));
        }
    }
}
