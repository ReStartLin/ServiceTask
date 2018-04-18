package restart.com.servicetask.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static restart.com.servicetask.App.INTENT;
import static restart.com.servicetask.App.KEY;

public class MyService extends Service {

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //获得已安装app
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> installedApplications = pm.getInstalledApplications(0);
        Intent intent = new Intent();
        intent.setAction(INTENT);
        intent.putParcelableArrayListExtra(KEY, (ArrayList<? extends Parcelable>) installedApplications);
        //发送广播
        sendBroadcast(intent);
    }
}
