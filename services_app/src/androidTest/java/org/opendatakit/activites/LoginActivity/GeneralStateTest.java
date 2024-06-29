package org.opendatakit.activites.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.opendatakit.BaseUITest;
import org.opendatakit.TestConsts;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.properties.CommonToolProperties;
import org.opendatakit.properties.PropertiesSingleton;
import org.opendatakit.services.R;
import org.opendatakit.services.preferences.activities.AppPropertiesActivity;
import org.opendatakit.services.sync.actions.activities.LoginActivity;
import org.opendatakit.services.sync.actions.fragments.UpdateServerSettingsFragment;

import java.util.Collections;
import java.util.Map;

public class GeneralStateTest extends BaseUITest<LoginActivity> {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);


    @Override
    protected void setUpPostLaunch() {
        activityRule.getActivity().runOnUiThread(() -> {
            PropertiesSingleton props = activityRule.getActivity().getProps();
            assertThat(props).isNotNull();

            Map<String, String> serverProperties = UpdateServerSettingsFragment.getUpdateUrlProperties(TEST_SERVER_URL);
            assertThat(serverProperties).isNotNull();
            props.setProperties(serverProperties);

            props.setProperties(Collections.singletonMap(CommonToolProperties.KEY_FIRST_LAUNCH, "false"));

            activityRule.getActivity().updateViewModelWithProps();
        });
    }
    @Test
    public void verifyValuesTest() {
        onView(isRoot()).perform(waitFor(TestConsts.WAIT_TIME));

        onView(withId(R.id.tvTitleLogin)).check(matches(withText(getContext().getString(R.string.drawer_sign_in_button_text))));
        onView(withId(R.id.btnAnonymousSignInLogin)).check(matches(withText(R.string.anonymous_user)));
        onView(withId(R.id.btnUserSignInLogin)).check(matches(withText(R.string.authenticated_user)));
        onView(withId(R.id.btnAnonymousSignInLogin)).check(matches(isEnabled()));
        onView(withId(R.id.btnUserSignInLogin)).check(matches(isEnabled()));
    }

    @Test
    public void verifyVisibilityTest() {
        onView(isRoot()).perform(waitFor(TestConsts.WAIT_TIME));
        onView(allOf(withId(R.id.btnDrawerOpen), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.btnDrawerOpen), isDisplayed())).perform(click());
        onView(withId(R.id.drawer_update_credentials)).check(doesNotExist());
        onView(withId(R.id.drawer_switch_sign_in_type)).check(doesNotExist());
    }


    @Test
    public void checkDrawerServerLoginTest() {
        onView(withId(R.id.btnDrawerOpen)).perform(click());
        onView(withId(R.id.drawer_server_login)).perform(click());

        onView(withId(R.id.inputServerUrl)).check(matches(isDisplayed()));
        onView(withId(R.id.inputTextServerUrl)).check(matches(withText(TEST_SERVER_URL)));
    }
    @Ignore
    @Test
    public void checkToolbarSettingsButtonClick() {
        onView(withId(R.id.action_settings)).perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasComponent(AppPropertiesActivity.class.getName()));
    }

    @Ignore
    @Test
    public void checkDrawerSettingsClick() {
        onView(withId(R.id.btnDrawerOpen)).perform(click());
        onView(withId(R.id.drawer_settings)).perform(click());
        Intents.intended(IntentMatchers.hasComponent(AppPropertiesActivity.class.getName()));
    }

    @Override
    protected Intent getLaunchIntent() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);
        return intent;
    }
}