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


import pt.utl.ist.xpdbench.constants.XPDConstants;

/**
 * XPDBench Configuration.
 */
public class BenchConfiguration {

    private int objectSize = XPDConstants.DEFAULT_OBJECT_SIZE;
    private boolean isGCForced = XPDConstants.IS_GC_FORCED;
    private int iteration = XPDConstants.DEFAULT_ITERATION;
    private int numOfThreads = XPDConstants.DEFAULT_THREAD_COUNT;
    private long threadSleepTimeInMillis =
        XPDConstants.DEFAULT_SLEEP_TIME_IN_MILLISECONDS;

    public int getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(int objectSize) {
        this.objectSize = objectSize;
    }

    public boolean isGCForced() {
        return isGCForced;
    }

    public void setGCForced(boolean GCForced) {
        isGCForced = GCForced;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public long getThreadSleepTimeInMillis() {
        return threadSleepTimeInMillis;
    }

    public void setThreadSleepTimeInMillis(long threadSleepTimeInMillis) {
        this.threadSleepTimeInMillis = threadSleepTimeInMillis;
    }
}
