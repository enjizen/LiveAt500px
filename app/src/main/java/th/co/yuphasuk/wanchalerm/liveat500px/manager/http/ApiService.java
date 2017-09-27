package th.co.yuphasuk.wanchalerm.liveat500px.manager.http;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.POST;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public interface ApiService {

    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList();



}
