package th.co.yuphasuk.wanchalerm.liveat500px.manager.http;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public interface ApiService {

    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList();

    @POST("list/after/{id}")
    Call<PhotoItemCollectionDao> loadPhotoListAfterId(@Path("id") int id);

    @POST("list/before/{id}")
    Call<PhotoItemCollectionDao> loadPhotoListBeforeId(@Path("id") int id);





}
