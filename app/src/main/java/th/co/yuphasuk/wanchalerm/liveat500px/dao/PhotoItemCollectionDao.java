package th.co.yuphasuk.wanchalerm.liveat500px.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import th.co.yuphasuk.wanchalerm.liveat500px.view.PhotoListItem;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public class PhotoItemCollectionDao {
    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private List<PhotoItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDao> getData() {
        return data;
    }

    public void setData(List<PhotoItemDao> data) {
        this.data = data;
    }
}
