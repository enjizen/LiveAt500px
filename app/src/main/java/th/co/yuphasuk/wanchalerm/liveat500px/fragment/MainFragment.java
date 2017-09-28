package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.adapter.PhotoListAdapter;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.Contextor;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.HttpManager;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.PhotoListManager;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    private ListView listView;
    private PhotoListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private PhotoListManager photoListManager;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        photoListManager = new PhotoListManager();
        listView = rootView.findViewById(R.id.list_view);

        listAdapter = new PhotoListAdapter();
        listView.setAdapter(listAdapter);

        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView
                    , int scrollState) {

            }

            @Override
            public void onScroll(AbsListView absListView,
                                        int firstVisibleItem,
                                        int visibleItemCount,
                                        int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            }
        });

        refreshData();
    }

    private void refreshData(){
        if(photoListManager.getCount() == 0)
            reloadData();
        else
            reloadDataNewer();
    }


    private void reloadDataNewer() {
        int maxId = photoListManager.getMaximumId();
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoListAfterId(maxId);
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD_NEWER));

    }


    private void reloadData() {
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD));

    }




    class PhotoListLoadCallBack implements Callback<PhotoItemCollectionDao>{

        public  static final int MODE_RELOAD = 1;
        public  static final int MODE_RELOAD_NEWER = 2;

        private int mode;

        public PhotoListLoadCallBack(int mode){
            this.mode = mode;
        }

        @Override
        public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {

            swipeRefreshLayout.setRefreshing(false);
            if(response.isSuccessful()){
                PhotoItemCollectionDao dao = response.body();

                int firstVisiblePosition = listView.getFirstVisiblePosition();
               // ตัวแรกที่มองเห็นตอนนั้น
                View c = listView.getChildAt(0);

                int top = c == null ? 0 : c.getTop();

                if(mode == MODE_RELOAD_NEWER)
                    photoListManager.insertDaoAtTopPosition(dao);
                else
                    photoListManager.setDao(dao);

                photoListManager.insertDaoAtTopPosition(dao);
                listAdapter.setDao(photoListManager.getDao());
                listAdapter.notifyDataSetChanged();

                if(mode == MODE_RELOAD_NEWER){
                    int additionalSize = (dao != null && dao.getData() != null) ? dao.getData().size() : 0;
                    listAdapter.increaseLastPosition(additionalSize);
                    listView.setSelectionFromTop(firstVisiblePosition + additionalSize,top);
                }

                Toast.makeText(Contextor.getInstance().getContext(), "Load completed", Toast.LENGTH_SHORT).show();
            }
            else{
                // Handle

                Toast.makeText(Contextor.getInstance().getContext(), response.errorBody()+"", Toast.LENGTH_SHORT).show();


            }
        }

        @Override
        public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
