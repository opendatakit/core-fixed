package org.opendatakit;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.opendatakit.utilities.RuntimePermissionUtils;

public abstract class BaseFileTest {
    @Rule
    public GrantPermissionRule writeRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    @Rule
    public GrantPermissionRule readtimePermissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);


    protected void verifyReady() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        Application app = ApplicationProvider.getApplicationContext();
        boolean permissions = RuntimePermissionUtils.checkPackageAllPermission(context, app.getPackageName(),
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        if (!permissions) {
            throw new RuntimeException("FILE PERMISSIONS MISSING");
        }
    }
}
