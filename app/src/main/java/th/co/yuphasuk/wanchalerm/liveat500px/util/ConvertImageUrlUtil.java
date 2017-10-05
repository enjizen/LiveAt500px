package th.co.yuphasuk.wanchalerm.liveat500px.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import th.co.yuphasuk.wanchalerm.liveat500px.R;
import th.co.yuphasuk.wanchalerm.liveat500px.manager.Contextor;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ConvertImageUrlUtil {

    private static ConvertImageUrlUtil instance;

    public static ConvertImageUrlUtil getInstance() {
        if (instance == null)
            instance = new ConvertImageUrlUtil();
        return instance;
    }

    private Context mContext;

    private ConvertImageUrlUtil() {
        mContext = Contextor.getInstance().getContext();
    }

    public void convertToImageView(String url,ImageView imageView){

        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

}
