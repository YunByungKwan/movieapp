package ybk.org.movieapp.util;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ybk.org.movieapp.di.component.DaggerAppComponent;

public class App extends DaggerApplication {

    private static App instance;
    public int commentId = 0;
    public int movieId = 0;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

}
