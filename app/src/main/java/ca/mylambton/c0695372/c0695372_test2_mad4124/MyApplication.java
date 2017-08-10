package ca.mylambton.c0695372.c0695372_test2_mad4124;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by rodrigocoutinho on 2017-08-10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
