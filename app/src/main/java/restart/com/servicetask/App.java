package restart.com.servicetask;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;

import restart.com.servicetask.service.MyService;

/**
 * Created by Administrator on 2018/4/18.
 */

public class App extends Application {
    public static final String INTENT = "com.restart";
    public static final String KEY = "app";
    @Override
    public void onCreate() {
        super.onCreate();
        //启动服务
        startService(new Intent(this, MyService.class));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
