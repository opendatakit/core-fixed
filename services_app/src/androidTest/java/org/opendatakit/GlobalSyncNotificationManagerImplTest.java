package org.opendatakit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import android.Manifest;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opendatakit.services.sync.service.GlobalSyncNotificationManagerImpl;
import org.opendatakit.services.sync.service.exceptions.NoAppNameSpecifiedException;

public class GlobalSyncNotificationManagerImplTest {

    @Rule
    public GrantPermissionRule writeRuntimePermissionRule = GrantPermissionRule .grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Rule
    public GrantPermissionRule readtimePermissionRule = GrantPermissionRule .grant(Manifest.permission.READ_EXTERNAL_STORAGE);

    @Mock
    private Service mockService;

    @Mock
    private NotificationManager mockNotificationManager;

    private GlobalSyncNotificationManagerImpl notificationManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockService.getSystemService(Context.NOTIFICATION_SERVICE)).thenReturn(mockNotificationManager);
        notificationManager = new GlobalSyncNotificationManagerImpl(mockService);
    }

    @Test
    public void testConstructorWithService() {
        assertNotNull(notificationManager);
        assertFalse(notificationManager.isDisplayingNotification());
    }

//    @Test
//    public void testStartingSync() throws NoAppNameSpecifiedException {
//        notificationManager.startingSync("testApp");
//        assertTrue(notificationManager.getAppStatus("testApp").isSyncing());
//    }

    @Test(expected = NoAppNameSpecifiedException.class)
    public void testStartingSyncWithNullAppName() throws NoAppNameSpecifiedException {
        notificationManager.startingSync(null);
    }

    @Test
    public void testCreateNotification() {
        notificationManager.createNotification("testApp");
        verify(mockService).startForeground(anyInt(), any());
    }

    @Test
    public void testRemoveNotification() {
        notificationManager.removeNotification();
        verify(mockService).stopForeground(true);
    }

    @Test
    public void testUpdateNotification() {
        notificationManager.updateNotification("testApp", "Syncing...", 100, 50, false);
        verify(mockNotificationManager).notify(eq("testApp"), anyInt(), any());
    }

    @Test
    public void testFinalErrorNotification() {
        notificationManager.finalErrorNotification("testApp", "Error occurred");
        verify(mockNotificationManager).notify(eq("testApp"), anyInt(), any());
    }

    @Test
    public void testClearNotification() {
        notificationManager.clearNotification("testApp", "Sync complete", "All data is synced");
        verify(mockNotificationManager).notify(eq("testApp"), anyInt(), any());
    }

    @Test
    public void testCreateSyncNotificationChannel() {
        notificationManager.createSyncNotificationChannel("testApp");
        verify(mockNotificationManager).createNotificationChannel(any());
    }
}
