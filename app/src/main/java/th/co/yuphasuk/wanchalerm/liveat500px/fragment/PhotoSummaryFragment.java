package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.constant.ArgumentName;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoSummaryFragment extends Fragment {

    private PhotoItemDao dao;
    private TextView tvName;
    private TextView tvDiscription;
    private ImageView ivImg;
    private LinearLayout bottomBar;

    public PhotoSummaryFragment() {
        super();
    }

    public static PhotoSummaryFragment newInstance(PhotoItemDao dao) {
        PhotoSummaryFragment fragment = new PhotoSummaryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ArgumentName.PHOTO_ITEM_DAO.toString(), dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);


        dao = getArguments().getParcelable(ArgumentName.PHOTO_ITEM_DAO.toString());

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_summary, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        ivImg = rootView.findViewById(R.id.iv_img);
        tvName = rootView.findViewById(R.id.tv_name);
        tvDiscription = rootView.findViewById(R.id.tv_description);
        bottomBar = rootView.findViewById(R.id.layout_bottom_bar);



        // Set up data

        tvName.setText(dao.getCaption());
        tvDiscription.setText(String.format("%s\n%s", dao.getUsername(), dao.getCamera()));
        Glide.with(PhotoSummaryFragment.this)
                .load(dao.getImageUrl())
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

}
