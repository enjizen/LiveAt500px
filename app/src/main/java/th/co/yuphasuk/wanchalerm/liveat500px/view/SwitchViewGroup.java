package th.co.yuphasuk.wanchalerm.liveat500px.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;

import th.co.yuphasuk.wanchalerm.liveat500px.R;


public class SwitchViewGroup extends BaseCustomViewGroup {



    public SwitchViewGroup(Context context) {
        super(context);
        initInflate();
    }

    public SwitchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
    }

    public SwitchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
    }

    @TargetApi(21)
    public SwitchViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
    }


    private void initInflate() {
        inflate(getContext(), R.layout.item_switch_custom, this);
    }



}
