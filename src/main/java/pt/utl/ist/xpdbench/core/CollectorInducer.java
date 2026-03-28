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
package pt.utl.ist.xpdbench.core;

import org.apache.log4j.Logger;
import pt.utl.ist.xpdbench.conf.ConfigBuilder;
import pt.utl.ist.xpdbench.util.XPDUtils;

import java.lang.ref.WeakReference;

/**
 * This class has the intention of inducing the garbage collection.
 */
public class CollectorInducer {
    private static Logger log = Logger.getLogger(CollectorInducer.class);

    /**
     * This method induces the garbage collection, based on the user selection.
     */
    public static void induceCollection() {
        if (ConfigBuilder.isGCInduced()) {
            Object tempObject = new Object();
            WeakReference weakReference = new WeakReference<Object>(tempObject);
            tempObject = null;
            while (weakReference.get() != null) {
                log.debug("Before inducing the GC");
                XPDUtils.printCurrentStatus();
                System.gc();
                log.debug("After inducing the GC");
                XPDUtils.printCurrentStatus();
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("GC is scheduled by the GC scheduling algorithm. " +
                          "External induction disabled.");
            }
        }
    }

    /**
     * Generates and Flushes big objects.
     */
    public static void generateAndFlushBigObjects() {
        BigObject bigObject = new BigObject();
        bigObject.flush();

        BigObject bigObjectDup = new BigObject();
        bigObjectDup.flush();

        induceCollection();

        bigObject = new BigObject();
        BigObject bigObjectDupDup = new BigObject();
        bigObject.flush();
        bigObjectDupDup.flush();

        induceCollection();
    }

    /**
     * This method iterates through the generateAndFlushBigObjects(), with the intention to consume
     * memory.
     *
     * @param iteration, number of times to iterate.
     */
    public static void consumeMemory(int iteration) {
        double startRoundTime = System.nanoTime();
        for (int i = 1; i <= iteration; i++) {
            try {
                generateAndFlushBigObjects();
                if (log.isDebugEnabled()) {
                    log.debug("Generate and flush big objects: Round: " + i);
                }
            } catch (Exception e) {
                log.warn("Exception occurred while consuming memory: " + e);
                log.info("Skipping round: " + i);
            }
        }
        double startRoundEndTime = System.nanoTime();
        if (log.isDebugEnabled()) {
            log.debug("Time Taken to complete the consume memory operation: " +
                      XPDUtils.getTimeConsumed(startRoundTime, startRoundEndTime) + " ms.");
        }
    }
}
