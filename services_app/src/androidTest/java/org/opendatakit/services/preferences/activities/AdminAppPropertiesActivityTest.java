package org.opendatakit.services.preferences.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.opendatakit.utilities.ViewMatchers.childAtPosition;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opendatakit.BaseUITest;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.properties.PropertiesSingleton;
import org.opendatakit.services.R;

@RunWith(AndroidJUnit4.class)
public class AdminAppPropertiesActivityTest extends BaseUITest<AppPropertiesActivity> {

    @Override
    protected void setUpPostLaunch() {
        activityScenario.onActivity(activity -> {
            PropertiesSingleton props = activity.getProps();
            assertThat(props).isNotNull();
        });
        onView(withId(R.id.app_properties_content)).check(matches(isDisplayed()));
        enableAdminMode();
        Espresso.pressBack();
    }

    @Test
    public void checkIfChangeAdminPasswordScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(3))
                .perform(actionOnItemAtPosition(3, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.change_admin_password))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 3),
                isDisplayed())).check(matches(withText(R.string.admin_password_enabled)));
    }

    @Test
    public void checkIfManageAbilityToChangeServerSettingScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(8))
                .perform(actionOnItemAtPosition(8, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.restrict_server))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 8),
                isDisplayed())).check(matches(withText(R.string.restrict_server_settings_summary)));
    }

    @Test
    public void checkIfManageAbilityToChangeDeviceSettingScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(9))
                .perform(actionOnItemAtPosition(9, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.restrict_device))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 9),
                isDisplayed())).check(matches(withText(R.string.restrict_device_settings_summary)));
    }

    @Test
    public void checkIfManageAbilityToChangeTableSpecificSettingScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(10))
                .perform(actionOnItemAtPosition(10, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.admin_tool_tables_settings))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 10),
                isDisplayed())).check(matches(withText(R.string.admin_tool_tables_settings_summary)));
    }

    @Test
    public void checkIfResetConfigurationScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(6))
                .perform(actionOnItemAtPosition(6, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.clear_settings))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 6),
                isDisplayed())).check(matches(withText(R.string.clear_configuration_settings)));
    }

    @Test
    public void checkIfExitAdminModeScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(11))
                .perform(actionOnItemAtPosition(11, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.exit_admin_mode))));
    }

    @Test
    public void checkIfVerifyUserPermissionScreen_isVisible() {
        onView(withId(androidx.preference.R.id.recycler_view))
                .perform(scrollToPosition(2))
                .perform(actionOnItemAtPosition(2, scrollTo()))
                .check(matches(hasDescendant(withText(R.string.verify_server_settings_header))));
        onView(allOf(withId(android.R.id.summary),
                childAtPosition(withId(androidx.preference.R.id.recycler_view), 2),
                isDisplayed())).check(matches(withText(R.string.click_to_verify_server_settings)));
    }

    @After
    public void tearDown() {
        resetConfiguration();
    }

    @Override
    protected Intent getLaunchIntent() {
        Intent intent = new Intent(getContext(), AppPropertiesActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);
        intent.putExtra(IntentConsts.INTENT_KEY_SETTINGS_IN_ADMIN_MODE, true);
        return intent;
    }
}
