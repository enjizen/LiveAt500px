package th.co.yuphasuk.wanchalerm.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.co.yuphasuk.wanchalerm.liveat500px.R;


public class FragmentTemplateFull extends Fragment {

    public FragmentTemplateFull() {
        super();
    }

    public static FragmentTemplateFull newInstance() {
        FragmentTemplateFull fragment = new FragmentTemplateFull();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        bindWidgets(rootView);
        setUpEvent();
        setDataView(savedInstanceState);
        return rootView;
    }



    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void bindWidgets(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
    }

    private void setDataView(Bundle savedInstanceState) {
        // Init Data to View  after bindWidgets
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState


    }

    private void setUpEvent() {
        // Set Event Listener

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
