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
import pt.utl.ist.xpdbench.XPDMain;
import pt.utl.ist.xpdbench.conf.ConfigBuilder;
import pt.utl.ist.xpdbench.util.XPDUtils;

import java.util.TimerTask;

/**
 * The TimerTask that consumes memory, for the benchmarking.
 */
public class MemoryConsumerTask extends TimerTask {
    private static Logger log = Logger.getLogger(CollectorInducer.class);

    @Override
    public void run() {
        int counter = ConfigBuilder.getThreadCount();
        for (int i = 1; i <= counter; i++) {
            CollectorInducer.consumeMemory(counter);
            try {
                Thread.sleep(ConfigBuilder.getThreadSleepTime());
                if (log.isDebugEnabled()) {
                    log.debug("Counter " + i + " finishing. Sleeping now.");
                }
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
        int currentThreadNumber = XPDMain.count.incrementAndGet();
        if (currentThreadNumber == counter) {
            double totalDuration = XPDUtils.getTimeConsumed(XPDMain.getStartMainTime(),
                                                            System.nanoTime());
            log.info("******************** Total time taken: " + totalDuration / 1000 + " s");
        }
    }
}
