package github.di.component.module;

import android.app.Activity;
import android.content.Context;

import dagger.Provides;
import github.di.component.ActivityContext;

public class ActivityModule {
    private Activity mActivity;
    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
