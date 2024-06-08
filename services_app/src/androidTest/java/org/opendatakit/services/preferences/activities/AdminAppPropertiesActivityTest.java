package org.opendatakit.services.preferences.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.opendatakit.utilities.ViewMatchers.childAtPosition;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.opendatakit.BaseUITest;
import org.opendatakit.IdlingResource;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.properties.PropertiesSingleton;
import org.opendatakit.services.R;

public class AdminAppPropertiesActivityTest extends BaseUITest<AppPropertiesActivity> {

    @Rule
    public ActivityTestRule<AppPropertiesActivity> activityRule = new ActivityTestRule<>(AppPropertiesActivity.class);


    @Override
    protected void setUpPostLaunch() {
        IdlingResource.increment();

        activityRule.getActivity().runOnUiThread(() -> {
            PropertiesSingleton props = activityRule.getActivity().getProps();
            assertThat(props).isNotNull();
        });
        enableAdminMode();
        onView(withId(R.id.app_properties_content)).check(matches(isDisplayed()));
        IdlingResource.decrement();
    }

    @Test
    public void checkIfChangeAdminPasswordScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());
        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(3, scrollTo()))
                .check(matches(atPosition(3, hasDescendant(withText(R.string.change_admin_password)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 3),
                isDisplayed())).check(matches(withText(R.string.admin_password_enabled)));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());
    }
    @Test
    public void checkIfManageAbilityToChangeServerSettingScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(8, scrollTo()))
                .check(matches(atPosition(8, hasDescendant(withText(R.string.restrict_server)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 8),
                isDisplayed())).check(matches(withText(R.string.restrict_server_settings_summary)));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());

    }


    @Test
    public void checkIfManageAbilityToChangeDeviceSettingScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(9, scrollTo()))
                .check(matches(atPosition(9, hasDescendant(withText(R.string.restrict_device)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 9),
                isDisplayed())).check(matches(withText(R.string.restrict_device_settings_summary)));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());

    }
    @Test
    public void checkIfManageAbilityToChangeTableSpecificSettingScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(10, scrollTo()))
                .check(matches(atPosition(10, hasDescendant(withText(R.string.admin_tool_tables_settings)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 10),
                isDisplayed())).check(matches(withText(R.string.admin_tool_tables_settings_summary)));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());

    }

    @Test
    public void checkIfResetConfigurationScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(6, scrollTo()))
                .check(matches(atPosition(6, hasDescendant(withText(R.string.clear_settings)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 6),
                isDisplayed())).check(matches(withText(R.string.clear_configuration_settings)));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());

    }


    @Test
    public void checkIfExitAdminModeScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(11, scrollTo()))
                .check(matches(atPosition(11, hasDescendant(withText(R.string.exit_admin_mode)))));
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());
    }

    @Test
    public void checkIfVerifyUserPermissionScreen_isVisible() {
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource());

        onView(withId(androidx.preference.R.id.recycler_view)).perform(actionOnItemAtPosition(2, scrollTo()))
                .check(matches(atPosition(2, hasDescendant(withText(R.string.verify_server_settings_header)))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 2),
                isDisplayed())).check(matches(withText(R.string.click_to_verify_server_settings)));

        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource());

    }

    @After
    public void after() {
        resetConfiguration();
    }

    @Override
    protected Intent getLaunchIntent() {
        IdlingResource.increment();
        Intent intent = new Intent(getContext(), AppPropertiesActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);
        intent.putExtra(IntentConsts.INTENT_KEY_SETTINGS_IN_ADMIN_MODE, true);
        IdlingResource.decrement();
        return intent;
    }

}