package com.ninedemons.scrtest.simple.provider;

import com.ninedemons.scrtest.simple.common.SimpleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;

import java.util.logging.Logger;

@Component(
        label = "First simple service implementation"
)

@Service(SimpleService.class)

@Properties({
        @Property(name = Constants.SERVICE_VENDOR, value = "Nine Demons"),
        @Property(name = Constants.SERVICE_RANKING, intValue = Integer.MAX_VALUE),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "The first SimpleService implementation")
})
public class SimpleServiceOne implements SimpleService{

    private static final Logger LOG = Logger.getLogger(SimpleServiceOne.class.getName());


    @Override
    public void doSomething() {
        LOG.info("Simple service one doing something");
    }

    @Override
    public String toString() {
        return "Simple Service One";
    }

}
