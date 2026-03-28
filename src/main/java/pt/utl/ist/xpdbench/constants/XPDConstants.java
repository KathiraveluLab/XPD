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
package pt.utl.ist.xpdbench.constants;

/**
 * This contains the constants across the benchmark.
 */
public class XPDConstants {
    public static int DEFAULT_OBJECT_SIZE = 1000;
    public static boolean IS_GC_FORCED = true;
    public static int DEFAULT_ITERATION = 10;
    public static int DEFAULT_THREAD_COUNT = 10;
    public static long DEFAULT_SLEEP_TIME_IN_MILLISECONDS = 10; // default: 60000 or 1 minute;

    public static final String XPD_CONFIG_FILE = "xpdbench.xml";
}
