package com.ninedemons.scrtest.simple.consumer;

import com.ninedemons.scrtest.simple.common.SimpleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.framework.Constants;

import java.util.logging.Logger;

@Component(immediate = true)

@Properties({
        @Property(name = Constants.SERVICE_VENDOR, value = "Nine Demons"),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "A simple consumer of another service")
})
public class Consumer {

    private static final Logger LOG = Logger.getLogger(Consumer.class.getName());


    @Reference
    protected SimpleService simpleService;


    protected void bindSimpleService(SimpleService service) {
        LOG.info("Got a new simple service " + service);
        this.simpleService = service;
        this.simpleService.doSomething();
    }

    protected void unbindSimpleService(SimpleService service) {
        LOG.info("Unbinding our current simple service " + service);
        this.simpleService = null;
    }

}
