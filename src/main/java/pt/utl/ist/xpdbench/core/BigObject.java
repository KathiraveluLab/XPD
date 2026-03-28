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

import java.util.ArrayList;
import java.util.List;

/**
 * Intended to provide a big object.
 */
public class BigObject {
    private static double bigArray[][];
    private static List<double[][]> bigList;
    private static int arrayCount = ConfigBuilder.getObjectSize();
    private static Logger log = Logger.getLogger(BigObject.class);

    /**
     * Initializes the double array.
     */
    public void initArray() {
        bigArray = new double[arrayCount][arrayCount];
        for (int i = 0; i < arrayCount; i++) {
            for (int j = 0; j < arrayCount; j++) {
                bigArray[i][j] = i;
            }
        }
    }

    /**
     * Initializes an ArrayList of Double array.
     */
    public void initList() {
        bigList = new ArrayList<double[][]>(arrayCount);
        for (int i = 0; i < arrayCount; i++) {
            bigList.add(bigArray);
        }
    }

    /**
     * Default constructor, initializing the BigObject object.
     */
    public BigObject() {
        initArray();
        initList();
    }

    /**
     * Initialize the BigObject object, with the given count.
     *
     * @param count, an integer passed as the param.
     */
    public BigObject(int count) {
        arrayCount = count;
        initArray();
        initList();
    }

    /**
     * Reset everything to null.
     */
    public void flush() {
        if (bigArray != null) {
            bigArray = null;
        }
        if (bigList != null) {
            bigList = null;
        }
        log.debug("Flushed the array and list objects");
    }
}
