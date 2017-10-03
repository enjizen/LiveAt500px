package th.co.yuphasuk.wanchalerm.liveat500px.util;

import android.util.Log;



/**
 * Created by wanchalerm yuphasuk on 11/16/2014.
 */
public class LogUtil {

    private static LogUtil instance;
    private boolean isShowLog;

    public static LogUtil getInstance() {
        if (instance == null) {
            instance = new LogUtil();
            instance.isShowLog = true;
        }
        return instance;
    }


    private LogUtil(){

    }


    public void v(String tag, String msg){
        if(isShowLog){
            Log.v(tag, msg);
        }
    }

    public void d(String tag, String msg){
        if(isShowLog){
            Log.d(tag, msg);
        }
    }

    public void i(String tag, String msg){
        if(isShowLog){
            Log.i(tag, msg);
        }
    }

    public void w(String tag, String msg){
        if(isShowLog){
            Log.w(tag, msg);
        }
    }

    public void e(String tag, String msg){
        if(isShowLog){
            Log.e(tag, msg);
        }
    }





}
