package twe.testprojects.rentateamtest.info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import twe.testprojects.rentateamtest.R;

public class InfoFragment extends Fragment {

    private TextView tv_info;

    public InfoFragment(){

    }

    public static InfoFragment newInstance(){
        return new InfoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        tv_info = view.findViewById(R.id.tv_info);
        tv_info.setText(R.string.info);
        return view;
    }


}
