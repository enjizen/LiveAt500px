package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.constant.ArgumentName;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;
import th.co.yuphasuk.wanchalerm.liveat500px.util.ConvertImageUrlUtil;
import th.co.yuphasuk.wanchalerm.liveat500px.util.LogUtil;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoInfoFragment extends Fragment {

    private PhotoItemDao dao;
    private ImageView imageProfile;
    private TextView name;

    public PhotoInfoFragment() {
        super();
    }

    public static PhotoInfoFragment newInstance(PhotoItemDao dao) {
        PhotoInfoFragment fragment = new PhotoInfoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_photo_info, container, false);
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

        imageProfile = rootView.findViewById(R.id.image_profile);
        name = rootView.findViewById(R.id.name);



        // set data here

        ConvertImageUrlUtil.getInstance().convertToImageView(dao.getProfilePicture(),imageProfile);
        name.setText(dao.getUsername());

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
