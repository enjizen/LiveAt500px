package th.co.yuphasuk.wanchalerm.liveat500px.manager;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoListManager {
/*
    private static PhotoListManager instance;

    public static PhotoListManager getInstance() {
        if (instance == null)
            instance = new PhotoListManager();
        return instance;
    }*/

    private Context mContext;
    private PhotoItemCollectionDao dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }


    public void insertDaoAtTopPosition(PhotoItemCollectionDao newDao){
        if(this.dao == null)
            this.dao = new PhotoItemCollectionDao();

        if(this.dao.getData() == null)
            this.dao.setData(new ArrayList<PhotoItemDao>());


        this.dao.getData().addAll(0,newDao.getData());
    }

    public void appendDaoAtBottomPosition(PhotoItemCollectionDao newDao){
        if(this.dao == null)
            this.dao = new PhotoItemCollectionDao();

        if(this.dao.getData() == null)
            this.dao.setData(new ArrayList<PhotoItemDao>());


        this.dao.getData().addAll(dao.getData().size(),newDao.getData());
    }

    public int getMaximumId(){
        if(this.dao == null)
            return 0;
        if(this.dao.getData() == null)
            return 0;

        if(this.dao.getData().size() == 0)
            return 0;

        int maxId = this.dao.getData().get(0).getId();

        for(int i = 0; i < this.dao.getData().size(); i++){
            maxId = Math.max(maxId, this.dao.getData().get(i).getId());
        }

        return maxId;
    }

    public int getMinimumId(){
        if(this.dao == null)
            return 0;
        if(this.dao.getData() == null)
            return 0;

        if(this.dao.getData().size() == 0)
            return 0;

        int minId = this.dao.getData().get(0).getId();

        for(int i = 0; i < this.dao.getData().size(); i++){
            minId = Math.min(minId, this.dao.getData().get(i).getId());
        }

        return minId;
    }

    public int getCount(){
        if (this.dao == null)
            return 0;
        if(this.dao.getData() == null)
            return 0;

        return this.dao.getData().size();
    }

    public Bundle onSaveInstanceState(){

        Bundle bundle = new Bundle();
        bundle.putParcelable("dao",dao);

        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
            dao = savedInstanceState.getParcelable("dao");
    }
}
