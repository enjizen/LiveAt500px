package th.co.yuphasuk.wanchalerm.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import th.co.yuphasuk.wanchalerm.liveat500px.view.PhotoListItem;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public class PhotoListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 100000;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

/*    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {


        return position % 2 == 0 ? 0 : 1;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoListItem item;

        if (convertView != null) {
            item = (PhotoListItem) convertView;
        } else
            item = new PhotoListItem(parent.getContext());

        return item;

       /* if (getItemViewType(position) == 0) {

            PhotoListItem item;

            if (convertView != null) {
                item = (PhotoListItem) convertView;
            } else
                item = new PhotoListItem(parent.getContext());

            return item;
        } else {
            TextView item;
            if (convertView != null) {

                item =  (TextView) convertView;
                item.setText("position : " + position);
            } else {
                item = new TextView(parent.getContext());
            }
            return item;
        }*/
    }
}
