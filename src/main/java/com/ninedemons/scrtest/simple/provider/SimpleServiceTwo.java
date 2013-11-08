package com.ninedemons.scrtest.simple.provider;

import com.ninedemons.scrtest.simple.common.SimpleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;

import java.util.logging.Logger;

@Component(
        label = "Second simple service implementation",
        enabled = false
)

@Service(SimpleService.class)

@Properties({
        @Property(name = Constants.SERVICE_VENDOR, value = "Nine Demons"),
        @Property(name = Constants.SERVICE_RANKING, intValue = Integer.MIN_VALUE),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "The second SimpleService implementation")
})
public class SimpleServiceTwo implements SimpleService{

    private static final Logger LOG = Logger.getLogger(SimpleServiceTwo.class.getName());


    @Override
    public void doSomething() {
        LOG.info("Simple service two doing something different");
    }

    @Override
    public String toString() {
        return "Simple Service Two";
    }
}
