package github.component;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Component;
import github.DemoApplication;
import github.di.component.ApplicationContext;
import github.di.component.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

}
