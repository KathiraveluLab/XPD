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
package pt.utl.ist.xpdbench.util;

import org.apache.log4j.Logger;

/**
 * Utility classes for Memory management
 */
public class XPDUtils {
    private static Logger log = Logger.getLogger(XPDUtils.class);
    private static Runtime runtimeEnv = Runtime.getRuntime();

    public XPDUtils() {
        printNumberOfProcessors();
    }

    /**
     * Prints the current status
     */
    public static void printCurrentStatus() {
        if (log.isDebugEnabled()) {
            log.debug("Total memory in the virtual machine: " + runtimeEnv.totalMemory());
            log.debug("Free memory: " + runtimeEnv.freeMemory());
        }
    }

    /**
     * Just prints the number of processors
     */
    public static void printNumberOfProcessors() {
        log.info("Available Processors:" + runtimeEnv.availableProcessors());
    }

    /**
     * Gets the spent time.
     *
     * @param startMainTime, the starting time.
     * @param endTime,       the ending time.
     * @return the time taken, in milli seconds.
     */
    public static double getTimeConsumed(double startMainTime, double endTime) {
        return (endTime - startMainTime) / 1000000;
    }
}
