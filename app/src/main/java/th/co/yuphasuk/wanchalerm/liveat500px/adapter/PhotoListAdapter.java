package th.co.yuphasuk.wanchalerm.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.PhotoListManager;
import th.co.yuphasuk.wanchalerm.liveat500px.view.PhotoListItem;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public class PhotoListAdapter extends BaseAdapter {

    private PhotoItemCollectionDao dao;

    private int lastPosition = -1;

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {

        if(dao == null)
            return 1;
        if(dao .getData()== null)
            return 1;

        return dao.getData().size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {


        return position == getCount() - 1 ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(position == getCount() - 1){
            ProgressBar item;

            if(convertView != null)
                item = (ProgressBar) convertView;
            else
                item = new ProgressBar(parent.getContext());


            return item;
        }

        PhotoListItem item;

        if (convertView != null) {
            item = (PhotoListItem) convertView;
        } else
            item = new PhotoListItem(parent.getContext());


        PhotoItemDao dao = (PhotoItemDao) getItem(position);

        item.setNameText(dao.getCaption());
        item.setDecriptionText(dao.getUsername() + "\n" + dao.getCamera() );
        item.setImageUrl(dao.getImageUrl());



        if(position > lastPosition) {


            Animation anim = AnimationUtils.loadAnimation(
                    parent.getContext()
                    , R.anim.up_from_bottom
            );
            item.startAnimation(anim);

            lastPosition = position;
        }

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

    public void increaseLastPosition(int amount){
        lastPosition += amount;
    }
}
