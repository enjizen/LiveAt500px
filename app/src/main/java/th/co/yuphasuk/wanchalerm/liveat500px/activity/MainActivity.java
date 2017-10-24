package th.co.yuphasuk.wanchalerm.liveat500px.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.analytics.FirebaseAnalytics;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.constant.IntentName;
import th.co.yuphasuk.wanchalerm.liveat500px.dao.PhotoItemDao;
import th.co.yuphasuk.wanchalerm.liveat500px.fragment.MainFragment;
import th.co.yuphasuk.wanchalerm.liveat500px.fragment.MoreInfoFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }


        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

       // mFirebaseAnalytics.setUserProperty("favorite_food", "Noodle");


     /*   Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Tree");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/
    }

    private void initInstances() {
        toolbar = findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);


        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPhotoItemClicked(PhotoItemDao dao) {

        FrameLayout moreInfoLayout = findViewById(R.id.moreInfoContainer);

        if (moreInfoLayout == null) {
            // Mobile
            Intent intent = new Intent(MainActivity.this, MoreInfoActivity.class);
            intent.putExtra(IntentName.PHOTO_ITEM_DAO.toString(), dao);
            startActivity(intent);

        } else {
            // Tablet
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer, MoreInfoFragment.newInstance(dao))
                    .commit();
        }

    }
}
