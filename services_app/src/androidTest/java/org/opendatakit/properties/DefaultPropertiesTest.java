package org.opendatakit.properties;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.opendatakit.services.utilities.UserState;
import org.opendatakit.utilities.StaticStateManipulator;

import static com.google.common.truth.Truth.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

public class DefaultPropertiesTest {

    public final String APP_NAME = "DefaultPropTest";

    @Before
    public void setUp() {
        StaticStateManipulator.get().reset();
    }

    @Test
    public void verifyServerUrlProperty() {
        PropertiesSingleton props = getProps(getContext());

        String serverUrl = props.getProperty(CommonToolProperties.KEY_SYNC_SERVER_URL);

        assertThat(serverUrl).isNotNull();
        assertThat(serverUrl).isEqualTo(getContext().getString(org.opendatakit.androidlibrary.R.string.default_sync_server_url));
    }

    @Test
    public void verifyIsServerVerifiedProperty() {
        PropertiesSingleton props = getProps(getContext());

        String isServerVerifiedStr = props.getProperty(CommonToolProperties.KEY_IS_SERVER_VERIFIED);
        assertThat(isServerVerifiedStr).isNotNull();

        boolean isServerVerified = Boolean.parseBoolean(isServerVerifiedStr);
        assertThat(isServerVerified).isFalse();
    }

    @Test
    public void verifyIsAnonymousUsedProperty() {
        PropertiesSingleton props = getProps(getContext());

        String isAnonymousUsedStr = props.getProperty(CommonToolProperties.KEY_IS_ANONYMOUS_SIGN_IN_USED);
        assertThat(isAnonymousUsedStr).isNotNull();

        boolean isAnonymousUsed = Boolean.parseBoolean(isAnonymousUsedStr);
        assertThat(isAnonymousUsed).isFalse();
    }

    @Test
    public void verifyIsAnonymousAllowedProperty() {
        PropertiesSingleton props = getProps(getContext());

        String isAnonymousAllowed = props.getProperty(CommonToolProperties.KEY_IS_ANONYMOUS_ALLOWED);
        assertThat(isAnonymousAllowed).isNull();
    }

    @Test
    public void verifyCurrentUserStateProperty() {
        PropertiesSingleton props = getProps(getContext());

        String currentUserStateStr = props.getProperty(CommonToolProperties.KEY_CURRENT_USER_STATE);
        assertThat(currentUserStateStr).isNotNull();

        UserState userState = UserState.valueOf(currentUserStateStr);
        assertThat(userState).isEqualTo(UserState.LOGGED_OUT);
    }

    @Test
    public void verifyUsernameProperty() {
        PropertiesSingleton props = getProps(getContext());

        String username = props.getProperty(CommonToolProperties.KEY_USERNAME);
        assertThat(username).isNotNull();
        assertThat(username).isEmpty();
    }

    @Test
    public void verifyIsUserAuthenticatedProperty() {
        PropertiesSingleton props = getProps(getContext());

        String isUserAuthenticatedStr = props.getProperty(CommonToolProperties.KEY_IS_USER_AUTHENTICATED);
        assertThat(isUserAuthenticatedStr).isNull();
    }

    @Test
    public void verifyLastSyncInfoProperty() {
        PropertiesSingleton props = getProps(getContext());

        String lastSyncInfo = props.getProperty(CommonToolProperties.KEY_LAST_SYNC_INFO);
        assertThat(lastSyncInfo).isNull();
    }

    @Test
    public void verifyDefaultPropertyValue() {
        PropertiesSingleton props = getProps(getContext());
        // Example of checking a default property value
        String defaultValue = props.getProperty("non_existing_key");
        assertThat(defaultValue).isNull();
    }

    @Test
    public void verifyServerUrlPropertyFormat() {
        PropertiesSingleton props = getProps(getContext());
        String serverUrl = props.getProperty(CommonToolProperties.KEY_SYNC_SERVER_URL);
        assertThat(serverUrl).matches("https?://.*");  // Basic URL pattern check
    }

    @Test
    public void verifyUsernamePropertyLength() {
        PropertiesSingleton props = getProps(getContext());
        String username = props.getProperty(CommonToolProperties.KEY_USERNAME);

        assertThat(username).isNotNull();
        assertThat(username.length()).isAtMost(50); // Example length constraint
    }

    @Test
    public void verifyMissingKeyReturnsNull() {
        PropertiesSingleton props = getProps(getContext());
        String missingKey = props.getProperty("missing_key");
        assertThat(missingKey).isNull();
    }

    @Ignore // need to verify how properties is set internally
    @Test
    public void verifyPropertiesReset() {
        PropertiesSingleton props = getProps(getContext());

        // Define properties to set
        Map<String, String> properties = new HashMap<>();
        properties.put(CommonToolProperties.KEY_USERNAME, "testUser");

        // Set properties
        props.setProperties(properties);

        // Ensure the property is set correctly
        assertEquals("testUser", props.getProperty(CommonToolProperties.KEY_USERNAME));

        // Reset properties by clearing them explicitly
        clearAllProperties(props);

        // Retrieve properties after reset
        String username = props.getProperty(CommonToolProperties.KEY_USERNAME);

        // Verify properties have been reset
        assertNull("Username property should be null after reset", username);
    }

    private void clearAllProperties(PropertiesSingleton props) {
        // Clear all properties by setting an empty map
        props.setProperties(new HashMap<>());
        System.out.println("All properties cleared.");
    }


    @After
    public void clearProperties() {
        StaticStateManipulator.get().reset();
    }

    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private PropertiesSingleton getProps(Context context) {
        return CommonToolProperties.get(context, APP_NAME);
    }
}
