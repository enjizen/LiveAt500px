package th.co.yuphasuk.wanchalerm.liveat500px.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import th.co.yuphasuk.wanchalerm.liveat500px.manager.Contextor;

public class BuildVersionResourceUtil {

    private static BuildVersionResourceUtil instance;

    public static BuildVersionResourceUtil getInstance() {
        if (instance == null)
            instance = new BuildVersionResourceUtil();
        return instance;
    }

    private Context mContext;

    private BuildVersionResourceUtil() {
        mContext = Contextor.getInstance().getContext();
    }

    public Drawable getDrawable(int drawableId){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return mContext.getResources().getDrawable(drawableId);
        } else {
            return mContext.getResources().getDrawable(drawableId, mContext.getTheme());
        }
    }

}
