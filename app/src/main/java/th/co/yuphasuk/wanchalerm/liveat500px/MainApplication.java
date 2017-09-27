package th.co.yuphasuk.wanchalerm.liveat500px;

import android.app.Application;

import th.co.yuphasuk.wanchalerm.liveat500px.manager.Contextor;


/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
