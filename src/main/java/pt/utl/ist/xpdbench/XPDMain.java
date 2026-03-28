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
package pt.utl.ist.xpdbench;

import org.apache.log4j.Logger;
import pt.utl.ist.xpdbench.conf.BenchConfiguration;
import pt.utl.ist.xpdbench.conf.ConfigBuilder;
import pt.utl.ist.xpdbench.core.CollectorInducer;
import pt.utl.ist.xpdbench.core.MemoryConsumerTask;
import pt.utl.ist.xpdbench.util.XPDUtils;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The main class of the XPD Bench.
 */
public class XPDMain {
    private static Logger log = Logger.getLogger(XPDMain.class);
    private static double startMainTime;
    public static AtomicInteger count;

    private static boolean isInitialized = false;

    public static void main(String[] args) {
        startMainTime = System.nanoTime();
        XPDMain.initialize();

        while (!isInitialized) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error("Exception while waiting for the configuration initialization", e);
            }
        }

        log.info("Benchmark started with " + ConfigBuilder.getThreadCount() + " threads and " +
                 ConfigBuilder.getIteration() + " iterations.");
        count = new AtomicInteger(0);
        int threadCount = ConfigBuilder.getThreadCount();
        Timer[] timer = new Timer[threadCount];
        for (int i = 0; i < threadCount; i++) {
            timer[i] = new Timer();
            timer[i].schedule(new MemoryConsumerTask(), i * 10000);
        }
        double startGCTime = System.nanoTime();
        CollectorInducer.induceCollection();
        double endTime = System.nanoTime();

        double finalRoundDuration = XPDUtils.getTimeConsumed(startGCTime, endTime);
        if (ConfigBuilder.isGCInduced()) {
            log.info("******************** Time taken to run the forced GC Iteration: "
                     + finalRoundDuration + " ms");
        }
    }

    /**
     * Gets the time the main() started the execution.
     * @return the started time in nano-seconds.
     */
    public static double getStartMainTime() {
        return startMainTime;
    }

    /**
     * Initialize the parameters;
     */
    public static void initialize() {
        try {
            if (ConfigBuilder.getBenchConfiguration() == null) {
                BenchConfiguration benchConfiguration =
                    ConfigBuilder.loadBenchConfiguration();
                ConfigBuilder.setBenchConfiguration(benchConfiguration);
            }
        } catch (Exception e) {
            log.error("Error in initializing the Benchmark with the configurations provided." +
                      "Default settings will be used.");
        } finally {
            isInitialized = true;
        }
    }
}