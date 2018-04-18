package restart.com.servicetask.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import restart.com.servicetask.App;
import restart.com.servicetask.R;
import restart.com.servicetask.adapter.MyAdapter;
import restart.com.servicetask.broadcast.MyReceiver;
import restart.com.servicetask.service.MyService;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private List<ApplicationInfo> apps = new ArrayList<>();
    private List<ApplicationInfo> apps_show = new ArrayList<>();

    private MyAdapter adapter;

    private MyReceiver myReceiver = new MyReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            apps = intent.getParcelableArrayListExtra(App.KEY);
            adapter.setData(apps);
            adapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(App.INTENT);
        registerReceiver(myReceiver, intentFilter);

        initView();

        initEvent();

        adapter = new MyAdapter(this,apps);
        listView.setAdapter(adapter);

    }

    private void initEvent() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                apps_show.clear();
                for (ApplicationInfo app : apps) {
                    String appname = app.loadLabel(getPackageManager()).toString();
                    if (appname.indexOf(query) != -1) {
                        apps_show.add(app);
                    }
                }
                adapter.setData(apps_show);
                adapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.setData(apps);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.id_lv_app);
        searchView = findViewById(R.id.id_sv_search);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MyService.class));
    }



}
