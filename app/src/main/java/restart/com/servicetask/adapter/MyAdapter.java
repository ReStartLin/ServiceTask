package restart.com.servicetask.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import restart.com.servicetask.R;

/**
 * Created by Administrator on 2018/4/18.
 */

public class MyAdapter extends BaseAdapter {
    private List<ApplicationInfo> data;
    private Context context;


    public MyAdapter(Context context,List<ApplicationInfo> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<ApplicationInfo> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_app, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = convertView.findViewById(R.id.id_iv_icon);
            viewHolder.name = convertView.findViewById(R.id.id_tv_app_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data != null) {
            viewHolder.name.setText(data.get(position).loadLabel(context.getPackageManager()).toString());
            viewHolder.icon.setImageDrawable(data.get(position).loadIcon(context.getPackageManager()));

        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
        ImageView icon;
    }
}
