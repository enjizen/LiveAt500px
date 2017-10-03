package th.co.yuphasuk.wanchalerm.liveat500px.util;

import java.text.DecimalFormat;


/**
 * Created by wanchalerm yuphasuk on 11/16/2014.
 */
public class NumberFormatUtil {

    private static NumberFormatUtil instance;

    public static NumberFormatUtil getInstance() {
        if (instance == null)
            instance = new NumberFormatUtil();
        return instance;
    }

    private NumberFormatUtil(){

    }


    public String convertDoubleToFormatCommaZeroZero(Double value){

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.00");

        String result;

        if(value.isNaN())
            result = "0.00";
        else{
            result = decimalFormat.format(value);
        }

        return result;

    }

}
