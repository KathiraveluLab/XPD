# XPDBench
XPD, a Mark-Compact Collector algorithm implementation for MMTk. XPDBench is a micro-benchmark for the garbage collection algorithm implementations in java.


## Building the Application
The product can be built using Apache Maven2 from the root directory of xpdbench.
```
mvn clean install -Dmaven.test.skip=true
```

The built jar with the dependencies can be found inside the target directory, xpdbench-1.0.0-jar-with-dependencies.jar

Once built, copy the xpdbench.xml from the root directory, and place it with the built jar.

## Configuring and running the Application
The application can be configured using the xpdbench.xml.

Run using java,
```
java -jar xpdbench-1.0.0-jar-with-dependencies.jar
```

For jikes rvm,

from the directory of jikes rvm executable, 
```
./rvm -jar xpdbench-1.0.0-jar-with-dependencies.jar
```
