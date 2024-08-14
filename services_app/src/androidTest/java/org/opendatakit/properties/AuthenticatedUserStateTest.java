package org.opendatakit.properties;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opendatakit.services.sync.actions.fragments.SetCredentialsFragment;
import org.opendatakit.services.utilities.UserState;
import org.opendatakit.utilities.StaticStateManipulator;

import java.util.Collections;

public class AuthenticatedUserStateTest {

    public final String APP_NAME = "AuthenticatedUserStatePropTest";

    private final String TEST_USERNAME = "testUsername";
    private final String TEST_PASSWORD = "testPassword";

    @Before
    public void setUp() {
        StaticStateManipulator.get().reset();

        PropertiesSingleton props = getProps(getContext());
        props.setProperties(SetCredentialsFragment.getCredentialsProperty(TEST_USERNAME, TEST_PASSWORD));
    }

    @Test
    public void verifyCurrentUserStateProperty() {
        PropertiesSingleton props = getProps(getContext());

        String currentUserStateStr = props.getProperty(CommonToolProperties.KEY_CURRENT_USER_STATE);
        assertThat(currentUserStateStr).isNotNull();

        UserState userState = UserState.valueOf(currentUserStateStr);
        assertThat(userState).isEqualTo(UserState.AUTHENTICATED_USER);
    }

    @Test
    public void verifyUsernameProperty() {
        PropertiesSingleton props = getProps(getContext());

        String username = props.getProperty(CommonToolProperties.KEY_USERNAME);
        assertThat(username).isNotNull();
        assertThat(username).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void verifyIsUserAuthenticatedProperty() {
        PropertiesSingleton props = getProps(getContext());

        String isUserAuthenticatedStr = props.getProperty(CommonToolProperties.KEY_IS_USER_AUTHENTICATED);
        assertThat(isUserAuthenticatedStr).isNotNull();

        boolean isUserAuthenticated;
        isUserAuthenticated = Boolean.parseBoolean(isUserAuthenticatedStr);
        assertThat(isUserAuthenticated).isFalse();
    }

    @Test
    public void verifyPasswordProperty() {
        PropertiesSingleton props = getProps(getContext());

        String password = props.getProperty(CommonToolProperties.KEY_PASSWORD);
        assertThat(password).isNotNull();
        assertThat(password).isEqualTo(TEST_PASSWORD);
    }

    @Test
    public void verifyDefaultGroupProperty() {
        PropertiesSingleton props = getProps(getContext());

        String defaultGroup = props.getProperty(CommonToolProperties.KEY_DEFAULT_GROUP);
        assertThat(defaultGroup).isNotNull();
        assertThat(defaultGroup).isEqualTo("");
    }

    @Test
    public void verifyRolesListProperty() {
        PropertiesSingleton props = getProps(getContext());

        String rolesList = props.getProperty(CommonToolProperties.KEY_ROLES_LIST);
        assertThat(rolesList).isNotNull();
        assertThat(rolesList).isEqualTo("");
    }

    @Test
    public void verifyUsersListProperty() {
        PropertiesSingleton props = getProps(getContext());

        String usersList = props.getProperty(CommonToolProperties.KEY_USERS_LIST);
        assertThat(usersList).isNotNull();
        assertThat(usersList).isEqualTo("");
    }

    @Test
    public void verifyLastSyncInfoProperty() {
        PropertiesSingleton props = getProps(getContext());

        String lastSyncInfo = props.getProperty(CommonToolProperties.KEY_LAST_SYNC_INFO);
        assertThat(lastSyncInfo).isNull();
    }

    @Test
    public void verifyAuthenticationTypeProperty() {
        PropertiesSingleton props = getProps(getContext());

        String authType = props.getProperty(CommonToolProperties.KEY_AUTHENTICATION_TYPE);
        assertThat(authType).isNotNull();
        assertThat(authType).isEqualTo("username_password");
    }
    @Test(expected = IllegalArgumentException.class)
    public void verifyInvalidUserState() {
        PropertiesSingleton props = getProps(getContext());
        props.setProperties(Collections.singletonMap(CommonToolProperties.KEY_CURRENT_USER_STATE, "INVALID_STATE"));

        UserState.valueOf(props.getProperty(CommonToolProperties.KEY_CURRENT_USER_STATE));
    }

    @Test
    public void verifyResetProperties() {
        PropertiesSingleton props = getProps(getContext());
        StaticStateManipulator.get().reset();

        props.clearSettings();

        assertThat(props.getProperty(CommonToolProperties.KEY_USERNAME)).isNull();
        assertThat(props.getProperty(CommonToolProperties.KEY_PASSWORD)).isNull();
        assertThat(props.getProperty(CommonToolProperties.KEY_CURRENT_USER_STATE)).isNull();
    }

    @Test
    public void verifyStandardGroupsProperty() {
        PropertiesSingleton props = getProps(getContext());

        String expectedGroup = "DATA_COLLECTORS";
        String actualGroup = props.getProperty(CommonToolProperties.KEY_DEFAULT_GROUP);

        assertThat(actualGroup).isNotNull();
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }

    @Test
    public void verifyStandardRolesProperty() {
        PropertiesSingleton props = getProps(getContext());

        String expectedRole = "ROLE_USER";
        String actualRolesList = props.getProperty(CommonToolProperties.KEY_ROLES_LIST);

        assertThat(actualRolesList).isNotNull();
        assertThat(actualRolesList).contains(expectedRole);
    }

    @Test
    public void verifyMultipleRolesProperty() {
        PropertiesSingleton props = getProps(getContext());

        String[] expectedRoles = {"ROLE_USER", "ROLE_ADMIN"};
        String actualRolesList = props.getProperty(CommonToolProperties.KEY_ROLES_LIST);

        assertThat(actualRolesList).isNotNull();

        for (String role: expectedRoles) {
            assertThat(actualRolesList).contains(role);
        }
    }

    @Test
    public void verifyAllProperties() {
        PropertiesSingleton props = getProps(getContext());

        assertThat(props.getProperty(CommonToolProperties.KEY_USERNAME)).isEqualTo(TEST_USERNAME);
        assertThat(props.getProperty(CommonToolProperties.KEY_PASSWORD)).isEqualTo(TEST_PASSWORD);
        assertThat(props.getProperty(CommonToolProperties.KEY_CURRENT_USER_STATE)).isEqualTo(UserState.AUTHENTICATED_USER.name());
        assertThat(Boolean.parseBoolean(props.getProperty(CommonToolProperties.KEY_IS_USER_AUTHENTICATED))).isFalse();
        assertThat(props.getProperty(CommonToolProperties.KEY_DEFAULT_GROUP)).isEqualTo("");
        assertThat(props.getProperty(CommonToolProperties.KEY_ROLES_LIST)).isEqualTo("");
        assertThat(props.getProperty(CommonToolProperties.KEY_USERS_LIST)).isEqualTo("");
        assertThat(props.getProperty(CommonToolProperties.KEY_LAST_SYNC_INFO)).isNull();
        assertThat(props.getProperty(CommonToolProperties.KEY_AUTHENTICATION_TYPE)).isEqualTo("username_password");
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
