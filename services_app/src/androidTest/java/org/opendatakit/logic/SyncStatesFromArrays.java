package org.opendatakit.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

import org.junit.Test;
import org.opendatakit.BaseUITest;
import org.opendatakit.consts.IntentConsts;
import org.opendatakit.services.MainActivity;
import org.opendatakit.services.R;
import org.opendatakit.services.sync.actions.activities.LoginActivity;
import org.opendatakit.sync.service.SyncAttachmentState;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SyncStatesFromArrays extends BaseUITest<MainActivity> {


    @Test
    public void verifyArrayStateMatching(){
        Configuration config = getContext().getResources().getConfiguration();

        Locale current;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            current = config.getLocales().get(0);
        } else{
            current = config.locale;
        }

        Map<SyncAttachmentState,String> stateToTextMappings = new HashMap<>();
        if(current.equals(Locale.ENGLISH)) {
            stateToTextMappings.put(SyncAttachmentState.SYNC, "Fully Sync Attachments");
            stateToTextMappings.put(SyncAttachmentState.UPLOAD, "Upload Attachments Only");
            stateToTextMappings.put(SyncAttachmentState.DOWNLOAD, "Download Attachments Only");
            stateToTextMappings.put(SyncAttachmentState.NONE, "Do Not Sync Attachments");
        } else {
            return;
        }
        String[] syncType = getContext().getResources().getStringArray(R.array.sync_attachment_option_values);
        String[] syncTypeNames = getContext().getResources().getStringArray(R.array.sync_attachment_option_names);
        assertTrue(syncType.length == syncTypeNames.length);
        for(int i=0; i<syncTypeNames.length; i++) {
            SyncAttachmentState state = SyncAttachmentState.valueOf(syncType[i]);
            assertEquals(stateToTextMappings.get(state),syncTypeNames[i]);
        }
    }

    @Override
    protected void setUpPostLaunch() {
        // unneeded
    }

    @Override
    protected Intent getLaunchIntent() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra(IntentConsts.INTENT_KEY_APP_NAME, APP_NAME);
        return intent;
    }
}
