package org.opendatakit.services.sync.actions.fragments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Test;
import org.opendatakit.BaseUITest;
import org.opendatakit.TestConsts;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.services.MainActivity;
import org.opendatakit.services.R;
import org.opendatakit.services.sync.actions.activities.VerifyServerSettingsActivity;

public class UpdateServerSettingsFragmentTest extends BaseUITest<MainActivity> {


    @Override
    protected void setUpPostLaunch() {
        Espresso.onIdle();
        onView(ViewMatchers.isRoot()).perform(waitForView(withId(R.id.btnDrawerOpenMainActivity), TestConsts.TIMEOUT_WAIT));
        onView(withId(R.id.btnDrawerOpenMainActivity)).perform(ViewActions.click());
        onView(withId(R.id.drawer_server_login)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_server_login)).perform(ViewActions.click());
    }

    @Override
    protected Intent getLaunchIntent() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);

        return intent;
    }

    @Test
    public void whenUpdateServerUrlButtonClicked_doUpdateServerUrl_checkIfUrlIsEmpty() {
        onView(withId(R.id.inputTextServerUrl)).perform(replaceText(""));

        onView(withId(R.id.btnUpdateServerUrl)).perform(ViewActions.click());
        onView(withId(R.id.inputServerUrl)).check(matches(hasDescendant(
                withText("Server URL cannot be empty!")))
        );

    }

    @Test
    public void whenUpdateServerUrlButtonClicked_doUpdateServerUrl_checkIfUrlIsInvalid() {
        onView(withId(R.id.inputTextServerUrl)).perform(replaceText(" "));

        onView(withId(R.id.btnUpdateServerUrl)).perform(ViewActions.click());
        onView(withId(R.id.inputServerUrl)).check(matches(hasDescendant(
                withText("Please enter a Valid URL")))
        );

    }

    @Test
    public void whenChooseDefaultServerButtonClicked_doCheckServerUrl() {
        onView(withId(R.id.btnChooseDefaultServer)).perform(ViewActions.click());
        onView(withId(R.id.inputServerUrl)).check(matches(hasDescendant(
                withText(getContext().getString(R.string.default_sync_server_url))))
        );
    }

    @Test
    public void whenVerifyServerDetailsButtonClicked_doVerifyActivityLaunched() {
        onView(withId(R.id.btnVerifyServerUpdateServerDetails)).perform(ViewActions.click());
        intended(hasComponent(VerifyServerSettingsActivity.class.getName()));

    }

}