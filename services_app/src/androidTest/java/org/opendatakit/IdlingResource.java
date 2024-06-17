package org.opendatakit;

import androidx.test.espresso.idling.CountingIdlingResource;


public class IdlingResource {

    private static final String RESOURCE = "GLOBAL";
    private static final CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        if (!countingIdlingResource.isIdleNow()) {
            countingIdlingResource.decrement();
        }
    }

    public static CountingIdlingResource getIdlingResource() {
        return countingIdlingResource;
    }
}

