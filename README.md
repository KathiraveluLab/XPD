# XPD
XPD, a Mark-Compact Collector algorithm implementation for MMTk.

XPDBench is a micro-benchmark for the garbage collection algorithm implementations in java.


## Building the Application
The product can be built using Apache Maven.

Build using:
```bash
mvn clean package
```

The built jar with dependencies and the configuration file (`xpdbench.xml`) will be available in the `target` directory.

## Configuring and running the Application
The application can be configured using `xpdbench.xml`.

To run using Java:
```bash
java -jar target/xpdbench-1.0.0-jar-with-dependencies.jar
```

For Jikes RVM:
Replace `/path/to/rvm` with your actual Jikes RVM installation path:
```bash
/path/to/rvm/rvm -jar target/xpdbench-1.0.0-jar-with-dependencies.jar
```

