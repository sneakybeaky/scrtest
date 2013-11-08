To run this demo, first build this project

    mvn clean install


Now download the latest [Karaf](http://karaf.apache.org/) distribution, unpack & run. In the console do

    karaf@root> features:install webconsole-scr
    karaf@root> install -s mvn:com.ninedemons/com.ninedemons.scrtest/1.1-SNAPSHOT

In your browser now go to http://127.0.0.1:8181/system/console/configMgr and look for the  `Goodbye World Config`
factory and click on it to add a new config. When you save you should see something like this in the karaf log

     Got new goodbye service with prefix Goodbye
     Goodbye Bob!

