package org.opendatakit;

import static org.junit.Assert.assertTrue;

import android.content.Intent;

import androidx.test.espresso.Espresso;

import org.junit.Test;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.services.MainActivity;

public class WarmUpTest extends BaseUITest<MainActivity> {

    @Test
    public void warmUpTest() {
        Espresso.onIdle();
        assertTrue(true);
    }

    @Override
    protected void setUpPostLaunch() {
        // do nothing
    }

    @Override
    protected Intent getLaunchIntent() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);
        return intent;
    }
}
