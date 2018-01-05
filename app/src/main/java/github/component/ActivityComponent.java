package github.component;


import dagger.Component;
import github.di.component.PerActivity;
import github.di.component.module.ActivityModule;
import github.myapplication.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
