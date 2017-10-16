package th.co.yuphasuk.wanchalerm.liveat500px.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wanchalermyuphasuk on 14/10/2017 AD.
 */

public class BaseAppActivity extends AppCompatActivity {

    private Context context;

    protected void showLoadingDialog(){

    }



    public void setContext(Context context) {
        this.context = context;
    }
}
