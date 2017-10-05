package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.constant.ArgumentName;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;
import th.co.yuphasuk.wanchalerm.liveat500px.view.SlidingTabLayout;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoFragment extends Fragment {

    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    private PhotoItemDao dao;

    public MoreInfoFragment() {
        super();
    }

    public static MoreInfoFragment newInstance(PhotoItemDao dao) {
        MoreInfoFragment fragment = new MoreInfoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_more_info, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        super.setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        viewPager = rootView.findViewById(R.id.viewPager);


       /* viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                Button btn = new Button(container.getContext());
                btn.setText("position : " + position);
                container.addView(btn);

                return btn;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });*/

       viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
           @Override
           public Fragment getItem(int position) {
               switch (position){
                   case 0 :
                       return PhotoSummaryFragment.newInstance(dao);
                   case 1 :
                       return PhotoInfoFragment.newInstance(dao);
                   case 2 :
                        return PhotoTagsFragment.newInstance(dao);
                   default:
                       return null;
               }

           }

           @Override
           public int getCount() {
               return 3;
           }

           @Override
           public CharSequence getPageTitle(int position) {
               switch (position){
                   case 0 :
                       return "Summary";
                   case 1 :
                       return "Info";
                   case 2 :
                       return "Tags";
                   default:
                       return "";
               }
           }
       });

        slidingTabLayout =  rootView.findViewById(R.id.sliding_tab_layout);
        slidingTabLayout.setViewPager(viewPager);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_more_info,menu);

        MenuItem menuItem =  menu.findItem(R.id.action_share);
      ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        shareActionProvider.setShareIntent(getShareIntent());


    }

    private Intent getShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Extra Text");
        return intent;
    }
}
