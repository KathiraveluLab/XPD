/**
 *  Copyright 2013 team XPD.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pt.utl.ist.xpdbench.conf;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;
import pt.utl.ist.xpdbench.constants.XPDConstants;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Builds the configuration from the file.
 */
public class ConfigBuilder {
    private static final Logger logger = Logger.getLogger(ConfigBuilder.class.getName());
    private static BenchConfiguration benchConfiguration;

    public static BenchConfiguration getBenchConfiguration() {
        return benchConfiguration;
    }

    public static void setBenchConfiguration(BenchConfiguration benchConfiguration) {
        ConfigBuilder.benchConfiguration = benchConfiguration;
    }

    /**
     * Returns the configurations from the XPDBench configuration file.
     *
     * @return XPDBench configurations
     */
    public static BenchConfiguration loadBenchConfiguration() {
        // gets the configuration file name from the XPDConstants.
        return loadBenchConfiguration(XPDConstants.XPD_CONFIG_FILE);
    }

    /**
     * Loads the given Bench Configuration file.
     *
     * @param configFilename Name of the configuration file
     * @return the bench configuration data.
     */
    private static BenchConfiguration loadBenchConfiguration(String configFilename) {
        BenchConfiguration config = new BenchConfiguration();
        File configFile = new File(configFilename);
        if (configFile.exists()) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(configFile);
                XMLStreamReader parser =
                    XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
                StAXOMBuilder builder = new StAXOMBuilder(parser);
                OMElement documentElement = builder.getDocumentElement();
                Iterator it = documentElement.getChildElements();
                while (it.hasNext()) {
                    OMElement element = (OMElement) it.next();

                    if ("objectSize".equals(element.getLocalName())) {
                        int objectSize = Integer.parseInt(element.getText());
                        config.setObjectSize(objectSize);
                    } else if ("isGCInduced".equals(element.getLocalName())) {
                        boolean isGCInduced = Boolean.parseBoolean(element.getText());
                        config.setGCForced(isGCInduced);
                    } else if ("iteration".equals(element.getLocalName())) {
                        int iteration = Integer.parseInt(element.getText());
                        config.setIteration(iteration);
                    } else if ("threadCount".equals(element.getLocalName())) {
                        int threadCount = Integer.parseInt(element.getText());
                        config.setNumOfThreads(threadCount);
                    } else if ("sleepTime".equals(element.getLocalName())) {
                        long sleepTime = Long.parseLong(element.getText());
                        config.setThreadSleepTimeInMillis(sleepTime);
                    }
                }
                return config;
            } catch (Exception e) {
                String msg = "Error in parsing the XPDBench Configurations File: " +
                                 configFilename + ".";
                logger.error(msg, e);
                return config; //returns the default configurations, if the file could not be loaded.
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        String msg = "Could not close the Configuration File " + configFilename;
                        logger.error(msg, e);
                    }
                }
            }
        }
        logger.error("Unable to locate the XPDBench configurations file. " +
                         "Default Settings will be used.");
        return config; // return the default configuration, if the file not found.
    }

    public static int getObjectSize() {
        return benchConfiguration.getObjectSize();
    }

    public static boolean isGCInduced() {
        return benchConfiguration.isGCForced();
    }

    public static int getIteration() {
        return benchConfiguration.getIteration();
    }

    public static int getThreadCount() {
        return benchConfiguration.getNumOfThreads();
    }

    public static long getThreadSleepTime() {
        return benchConfiguration.getThreadSleepTimeInMillis();
    }
}
