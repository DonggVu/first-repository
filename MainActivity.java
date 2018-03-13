package com.example.dongvu.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity implements MainCallbacks {
    FragmentTransaction ft;
    FragmentRed redFragment;
    FragmentBlue blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();

        ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();
    }

    public void onMsgFromFragToMain(String sender, String strValue) {
        Toast.makeText(getApplication(),  sender + "\n"+ strValue, Toast.LENGTH_LONG).show();
       if(!sender.contentEquals("BLUE-FRAG"))
         redFragment.onMsgFromMainToFragment("\nSender: " + sender+ "\nMsg:" + strValue);
        //if(!sender.contentEquals("RED-FRAG"))
        //blueFragment.onMsgFromMainToFragment(strValue);
    }
}




