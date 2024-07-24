package org.opendatakit.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.Manifest;
import android.content.Intent;
import android.os.IBinder;

import androidx.test.rule.GrantPermissionRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.opendatakit.application.IToolAware;
import org.opendatakit.services.sync.service.AppSynchronizer;
import org.opendatakit.services.sync.service.GlobalSyncNotificationManager;
import org.opendatakit.services.sync.service.OdkSyncService;
import org.opendatakit.sync.service.SyncOverallResult;
import org.opendatakit.sync.service.SyncProgressEvent;
import org.opendatakit.sync.service.SyncStatus;

import java.util.concurrent.ScheduledExecutorService;

public class IOdkSyncServiceInterfaceImplTest {

//    @Mock
//    private OdkSyncService mockSyncService;
//
//    @Mock
//    private IOdkSyncServiceInterfaceImpl serviceInterface;
//
//    private static final String APP_NAME = "testApp";
//
//    @Before
//    public void setUp() {
//    }
//
//    @Test
//    public void testGetSyncStatus() throws RemoteException {
//        SyncStatus expectedStatus = SyncStatus.SYNC_COMPLETE;
//        when(mockSyncService.getStatus(APP_NAME)).thenReturn(expectedStatus);
//
//        SyncStatus result = serviceInterface.getSyncStatus(APP_NAME);
//        assertEquals(expectedStatus, result);
//    }
//
//    @Test
//    public void testGetSyncStatusThrowsException() throws RemoteException {
//        when(mockSyncService.getStatus(APP_NAME)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.getSyncStatus(APP_NAME);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    private void fail(String expectedRemoteException) {
//
//    }
//
//    @Test
//    public void testGetSyncProgressEvent() throws RemoteException {
//        SyncProgressEvent expectedEvent = new SyncProgressEvent();
//        when(mockSyncService.getSyncProgress(APP_NAME)).thenReturn(expectedEvent);
//
//        SyncProgressEvent result = serviceInterface.getSyncProgressEvent(APP_NAME);
//        assertEquals(expectedEvent, result);
//    }
//
//    @Test
//    public void testGetSyncProgressEventThrowsException() throws RemoteException {
//        when(mockSyncService.getSyncProgress(APP_NAME)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.getSyncProgressEvent(APP_NAME);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    @Test
//    public void testGetSyncResult() throws RemoteException {
//        SyncOverallResult expectedResult = new SyncOverallResult();
//        when(mockSyncService.getSyncResult(APP_NAME)).thenReturn(expectedResult);
//
//        SyncOverallResult result = serviceInterface.getSyncResult(APP_NAME);
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void testGetSyncResultThrowsException() throws RemoteException {
//        when(mockSyncService.getSyncResult(APP_NAME)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.getSyncResult(APP_NAME);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    @Test
//    public void testResetServer() throws RemoteException {
//        SyncAttachmentState attachmentState = new SyncAttachmentState();
//        when(mockSyncService.resetServer(APP_NAME, attachmentState)).thenReturn(true);
//
//        boolean result = serviceInterface.resetServer(APP_NAME, attachmentState);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testResetServerThrowsException() throws RemoteException {
//        SyncAttachmentState attachmentState = new SyncAttachmentState();
//        when(mockSyncService.resetServer(APP_NAME, attachmentState)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.resetServer(APP_NAME, attachmentState);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    @Test
//    public void testSynchronizeWithServer() throws RemoteException {
//        SyncAttachmentState attachmentState = new SyncAttachmentState();
//        when(mockSyncService.synchronizeWithServer(APP_NAME, attachmentState)).thenReturn(true);
//
//        boolean result = serviceInterface.synchronizeWithServer(APP_NAME, attachmentState);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testSynchronizeWithServerThrowsException() throws RemoteException {
//        SyncAttachmentState attachmentState = new SyncAttachmentState();
//        when(mockSyncService.synchronizeWithServer(APP_NAME, attachmentState)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.synchronizeWithServer(APP_NAME, attachmentState);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    @Test
//    public void testClearAppSynchronizer() throws RemoteException {
//        when(mockSyncService.clearAppSynchronizer(APP_NAME)).thenReturn(true);
//
//        boolean result = serviceInterface.clearAppSynchronizer(APP_NAME);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testClearAppSynchronizerThrowsException() throws RemoteException {
//        when(mockSyncService.clearAppSynchronizer(APP_NAME)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.clearAppSynchronizer(APP_NAME);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }
//
//    @Test
//    public void testVerifyServerSettings() throws RemoteException {
//        when(mockSyncService.verifyServerSettings(APP_NAME)).thenReturn(true);
//
//        boolean result = serviceInterface.verifyServerSettings(APP_NAME);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testVerifyServerSettingsThrowsException() throws RemoteException {
//        when(mockSyncService.verifyServerSettings(APP_NAME)).thenThrow(new RuntimeException("Test Exception"));
//
//        try {
//            serviceInterface.verifyServerSettings(APP_NAME);
//            fail("Expected RemoteException");
//        } catch (RemoteException e) {
//            // Expected exception
//        }
//    }

//    @Mock
//    private IToolAware mockToolAware;
//
//    @Mock
//    private GlobalSyncNotificationManager mockNotificationManager;
//
//    @Mock
//    private AppSynchronizer mockAppSynchronizer;
//
//    @Mock
//    private ScheduledExecutorService mockScheduledExecutor;
//
//    @Mock
//    private OdkSyncService service;
//
//    private static final String APP_NAME = "testApp";
////    private static final SyncAttachmentState ATTACHMENT_STATE = new SyncAttachmentState();
//
//
//    @Rule
//    public GrantPermissionRule writeRuntimePermissionRule = GrantPermissionRule .grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//    @Rule
//    public GrantPermissionRule readtimePermissionRule = GrantPermissionRule .grant(Manifest.permission.READ_EXTERNAL_STORAGE);
//
//    @Before
//    public void setUp() {
////        MockitoAnnotations.openMocks(this);
////        service = mock(OdkSyncService.class);
//        when(mockToolAware.getVersionCodeString()).thenReturn("1.0");
////        when(mockNotificationManager.isNotificationEnabled()).thenReturn(true);
//    }
//
//    @Test
//    public void testOnCreate() {
//        service.onCreate();
//        assertNotNull(service.getServiceInterface());
//        assertNotNull(service.getNotificationManager());
//        assertNotNull(service.getScheduledExecutor());
//    }
//
//    @Test
//    public void testOnStartCommand() {
//        Intent intent = new Intent();
//        int flags = 0;
//        int startId = 1;
//
//        int result = service.onStartCommand(intent, flags, startId);
//
//        assertEquals(OdkSyncService.START_STICKY, result);
//    }
//
//    @Test
//    public void testOnBind() {
//        Intent intent = new Intent();
//        IBinder binder = service.onBind(intent);
//        assertNotNull(binder);
//    }
//
//    @Test
//    public void testOnUnbind() {
//        Intent intent = new Intent();
//        boolean result = service.onUnbind(intent);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testOnDestroy() {
//        service.onDestroy();
//        verify(mockScheduledExecutor).shutdownNow();
//    }
//
//    @Test
//    public void testResetServer() {
//        when(mockAppSynchronizer.synchronize(true, ATTACHMENT_STATE)).thenReturn(true);
//        boolean result = service.resetServer(APP_NAME, ATTACHMENT_STATE);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testSynchronizeWithServer() {
//        when(mockAppSynchronizer.synchronize(false, ATTACHMENT_STATE)).thenReturn(true);
//        boolean result = service.synchronizeWithServer(APP_NAME, ATTACHMENT_STATE);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetStatus() {
//        when(mockAppSynchronizer.getStatus()).thenReturn(SyncStatus.SYNCING);
//        SyncStatus result = service.getStatus(APP_NAME);
//        assertEquals(SyncStatus.SYNCING, result);
//    }
//
//    @Test
//    public void testGetSyncProgress() {
//        SyncProgressEvent progressEvent = new SyncProgressEvent();
//        when(mockAppSynchronizer.getSyncProgressEvent()).thenReturn(progressEvent);
//        SyncProgressEvent result = service.getSyncProgress(APP_NAME);
//        assertEquals(progressEvent, result);
//    }
//
//    @Test
//    public void testGetSyncResult() {
//        SyncOverallResult result = new SyncOverallResult();
//        when(mockAppSynchronizer.getSyncResult()).thenReturn(result);
//        when(mockAppSynchronizer.getStatus()).thenReturn(SyncStatus.SYNCED);
//        SyncOverallResult syncResult = service.getSyncResult(APP_NAME);
//        assertEquals(result, syncResult);
//    }
//
//    @Test
//    public void testClearAppSynchronizer() {
//        when(mockAppSynchronizer.verifyServerSettings()).thenReturn(true);
//        boolean result = service.clearAppSynchronizer(APP_NAME);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testVerifyServerSettings() {
//        when(mockAppSynchronizer.verifyServerSettings()).thenReturn(true);
//        boolean result = service.verifyServerSettings(APP_NAME);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testStartShutdownActor() {
//        // Directly test the behavior of startShutdownActor if necessary
//        // or validate it through indirect means like ensuring it is scheduled.
//    }
//
//    @Test
//    public void testGetSync() {
//        AppSynchronizer sync = service.getSync(APP_NAME);
//        assertNotNull(sync);
//    }
}
