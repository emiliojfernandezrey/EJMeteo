package com.cice.ej.ejmeteo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.cice.ej.ejmeteo.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout2;
    private FragmentoMeteo fragmentoMeteo2;
    private static FragmentoMeteo fragmentoMeteo1;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setActivity(this);

        frameLayout2 = (FrameLayout) findViewById(R.id.frame_layout_meteo2);

        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();

        if(fragmentoMeteo1==null) {
            fragmentoMeteo1 = new FragmentoMeteo();
        }

        //fragmentoMeteo1.setActivity(this);
        Bundle bundle;
        if(savedInstanceState==null) {
            bundle = new Bundle();
            fragmentoMeteo1.setArguments(bundle);
        }

        transaction.replace(R.id.frame_layout_meteo1, fragmentoMeteo1);

        if(frameLayout2 !=null){//landscape
            fragmentoMeteo2 = new FragmentoMeteo();
            //fragmentoMeteo2.setActivity(this);
            transaction.add(R.id.frame_layout_meteo2, fragmentoMeteo2);
        }

        transaction.commit();
    }

}
