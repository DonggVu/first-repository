package com.example.dongvu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class FragmentRed extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView txtRed;
    Button btnRedClock;
    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
    fragment.setArguments(bundle);
    return fragment;

    } //newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(R.layout.layout_red, null);
        txtRed = (TextView) view_layout_red.findViewById(R.id.textView1Red);
        try {
            Bundle arguments = getArguments();
            String redMessage = arguments.getString("arg1","");
            txtRed.setText(redMessage);
        } catch (Exception e) {
            Log.e("Red bundle error - ","" + e.getMessage());
        }
        btnRedClock = (Button) view_layout_red.findViewById(R.id.button1Red);
        btnRedClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redMessage = "\nĐồng hồ:\n" + new Date().toString();
                txtRed.setText(redMessage);
                main.onMsgFromFragToMain("RED-FRAG", redMessage);
            }

        });
        return view_layout_red;
    }
    @Override
    public  void onMsgFromMainToFragment(String MessFromMain) {
        txtRed.setText( MessFromMain );
    }
}
