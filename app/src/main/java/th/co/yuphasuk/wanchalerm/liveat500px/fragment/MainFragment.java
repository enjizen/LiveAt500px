package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.adapter.PhotoListAdapter;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemCollectionDao;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;
import th.co.yuphasuk.wanchalerm.liveat500px.datatype.MutableInteger;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.Contextor;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.HttpManager;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.PhotoListManager;


public class MainFragment extends Fragment {

    /***********************
     *
     * Variables
     *
     ************************/

    public interface FragmentListener{
        void onPhotoItemClicked(PhotoItemDao dao);
    }


    private ListView listView;
    private PhotoListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private PhotoListManager photoListManager;
    private Button btnNewPhoto;

    private MutableInteger lastPositionInteger;


    // Variable Final
    private final String PHOTO_LIST_MANAGER = "photo list manager";
    private final String LAST_POSITION_INTEGER = "last position integer";

    /**********************
     *
     * Functions
     *
     **********************/

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize Fragment level

        init(savedInstanceState);



        if(savedInstanceState != null){
            onRestoreInstanceState(savedInstanceState);  // Restore Instance

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        bindWidgets(rootView);
        setUpEvent();
        setDataView(savedInstanceState);
        return rootView;
    }


    /*****************************
     *
     * new Object Variable
     *
     * @param savedInstanceState
     *
     *****************************/
    private void init(Bundle savedInstanceState) {
        photoListManager = new PhotoListManager();
        lastPositionInteger = new MutableInteger(-1);
    }


    /***************************
     * Inflate View Layout
     *
     * @param rootView
     ***************************/

    private void bindWidgets(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here


        btnNewPhoto = rootView.findViewById(R.id.btn_new_photo);
        listView = rootView.findViewById(R.id.list_view);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);

    }


    /*********************************
     *
     * Set Data To View
     * @param savedInstanceState
     *
     **********************************/

    private void setDataView(Bundle savedInstanceState) {
        listAdapter = new PhotoListAdapter(lastPositionInteger);
        listAdapter.setDao(photoListManager.getDao());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);

        if(savedInstanceState == null) {
            refreshData();
        }
    }

    /******************************
     *
     * Set Event Listener
     *
     * ****************************/

    private void setUpEvent() {
        btnNewPhoto.setOnClickListener(buttonClickListener);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);
        listView.setOnScrollListener(listViewScrollListener);
    }

    private void refreshData() {
        if (photoListManager.getCount() == 0)
            reloadData();
        else
            reloadDataNewer();
    }


    private void reloadDataNewer() {
        int maxId = photoListManager.getMaximumId();
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoListAfterId(maxId);
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD_NEWER));

    }

    boolean isLoadingMore = false;

    private void loadMoreData() {
        if (isLoadingMore)
            return;

        isLoadingMore = true;

        int minId = photoListManager.getMinimumId();
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoListBeforeId(minId);
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD_MORE));

    }


    private void reloadData() {
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD));

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

        outState.putBundle(PHOTO_LIST_MANAGER, photoListManager.onSaveInstanceState());

        outState.putBundle(LAST_POSITION_INTEGER,lastPositionInteger.onSaveInstanceState());




    }

    private void onRestoreInstanceState(Bundle savedInstanceState){
        // Restore

        photoListManager.onRestoreInstanceState(savedInstanceState.getBundle(PHOTO_LIST_MANAGER));

        lastPositionInteger.onRestoreInstanceState(savedInstanceState.getBundle(LAST_POSITION_INTEGER));

    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void showButtonNewPhoto() {
        Animation anim = AnimationUtils.loadAnimation(Contextor.getInstance().getContext(), R.anim.zoom_fade_in);

        btnNewPhoto.setVisibility(View.VISIBLE);
        btnNewPhoto.setAnimation(anim);

    }

    private void hideButtonNewPhoto() {
        Animation anim = AnimationUtils.loadAnimation(Contextor.getInstance().getContext(), R.anim.zoom_fade_out);
        btnNewPhoto.setVisibility(View.GONE);

        btnNewPhoto.setAnimation(anim);

    }

    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /*****************
     *
     * Listener Zone
     *
     **********************/

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnNewPhoto) {
                hideButtonNewPhoto();
                listView.smoothScrollToPosition(0);
            }
        }
    };


    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };


    AbsListView.OnScrollListener listViewScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView
                , int scrollState) {

        }

        @Override
        public void onScroll(AbsListView absListView,
                             int firstVisibleItem,
                             int visibleItemCount,
                             int totalItemCount) {
            if (absListView == listView) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);

                if (firstVisibleItem + visibleItemCount >= totalItemCount) {

                    if (photoListManager.getCount() > 0) {
                        // Load More
                        loadMoreData();

                    }
                }
            }
        }
    };

    AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            if(position < photoListManager.getCount()) {

                PhotoItemDao dao = photoListManager.getDao().getData().get(position);

                FragmentListener listener = (FragmentListener) getActivity();
                listener.onPhotoItemClicked(dao);
            }
        }
    };


    /**********************************
     *
     * Inner Class
     *
     ********************************/


    class PhotoListLoadCallBack implements Callback<PhotoItemCollectionDao> {

        private static final int MODE_RELOAD = 1;
        private static final int MODE_RELOAD_NEWER = 2;
        private static final int MODE_RELOAD_MORE = 3;


        private int mode;

        private PhotoListLoadCallBack(int mode) {
            this.mode = mode;
        }

        @Override
        public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {

            swipeRefreshLayout.setRefreshing(false);
            if (response.isSuccessful()) {
                PhotoItemCollectionDao dao = response.body();

                int firstVisiblePosition = listView.getFirstVisiblePosition();
                // ตัวแรกที่มองเห็นตอนนั้น
                View c = listView.getChildAt(0);

                int top = c == null ? 0 : c.getTop();

                if (mode == MODE_RELOAD_NEWER) {
                    photoListManager.insertDaoAtTopPosition(dao);
                } else if (mode == MODE_RELOAD_MORE) {
                    photoListManager.appendDaoAtBottomPosition(dao);

                } else {
                    photoListManager.setDao(dao);
                }

                clearLoadingMorFlag(mode);
                listAdapter.setDao(photoListManager.getDao());
                listAdapter.notifyDataSetChanged();

                if (mode == MODE_RELOAD_NEWER) {
                    int additionalSize = (dao != null && dao.getData() != null) ? dao.getData().size() : 0;
                    listAdapter.increaseLastPosition(additionalSize);
                    listView.setSelectionFromTop(firstVisiblePosition + additionalSize, top);

                    if (additionalSize > 0) {
                        showButtonNewPhoto();
                    }
                }

//                showToast(getString(R.string.load_completed));
            } else {
                // Handle

                clearLoadingMorFlag(mode);

                showToast(response.errorBody() + "");


            }
        }

        @Override
        public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
            clearLoadingMorFlag(mode);
            swipeRefreshLayout.setRefreshing(false);
            showToast(t.toString());

        }


        private void clearLoadingMorFlag(int mode) {
            if (mode == MODE_RELOAD_MORE)
                isLoadingMore = false;
        }
    }
}
