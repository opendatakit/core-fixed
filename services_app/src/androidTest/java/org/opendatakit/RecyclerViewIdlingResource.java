package org.opendatakit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

public class RecyclerViewIdlingResource implements IdlingResource {
    private ResourceCallback resourceCallback;
    private final RecyclerView recyclerView;

    public RecyclerViewIdlingResource(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public String getName() {
        return RecyclerViewIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean isIdle = recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0;
        if (isIdle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(@NonNull ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}

