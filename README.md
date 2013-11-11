# SCR Annotation Tests

## Introduction


This project has some very basic demonstrations of OSGi Declarative Services use with the Apache Felix SCR annotations.
The project serves as a basis to examine the behaviour of DS and serve as a basis for further experimentation.


## Installing

To run this demo, first build this project

    mvn clean install


Now download the latest [Karaf](http://karaf.apache.org/) distribution, unpack & run. In the console do

    karaf@root> features:install webconsole-scr
    karaf@root> install -s mvn:com.ninedemons/com.ninedemons.scrtest/1.1-SNAPSHOT

## Service dynamism
Look at the karaf log and you should see an entry like this

    Got a new simple service Simple Service One
    Simple service one doing something

This shows the consumer getting an instance of `SimpleService` and invoking it. Now use the [SCR web console tab](http://127.0.0.1:8181/system/console/components)
and stop the `com.ninedemons.scrtest.simple.provider.SimpleServiceOne` component (click on the stop button in the action column).

You will see an entry like this

    Unbinding our current simple service Simple Service One

This shows the callback in the consumer has been invoked when the service is unbound. If you refresh the web page you will also see
the consumer service (`com.ninedemons.scrtest.simple.consumer.Consumer`) is no longer active as it's dependency can no longer be satisfied.

If you now start both services (`com.ninedemons.scrtest.simple.provider.SimpleServiceOne` and `com.ninedemons.scrtest.simple.provider.SimpleServiceTwo`) you will
see the consumer has also been started with `SimpleServiceOne` being supplied as a dependency, even though two are available.

### Service ranking
Stop the Consumer service and make sure both SimpleService implementations are running - the `Consumer` status should be `disabled`
and both `SimpleService` status should be `registered`.

Now start the consumer and see which SimpleService is supplied as the dependency. Repeat this but disable `SimpleServiceOne`,
and then see that `SimpleServiceTwo` is supplied as the dependency.


## Configuration Factory, Configuration management & cardinality

In your browser now go to http://127.0.0.1:8181/system/console/configMgr and look for the  `Goodbye World Config`
factory and click on it to add a new config. When you save you should see something like this in the karaf log

     Got new goodbye service with prefix Goodbye
     Goodbye Bob!

As you add or remove more configurations you will see them being invoked by the consuming service. The code for this part
lives in the `com.ninedemons.scrtest.multiple` package.
