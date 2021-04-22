package com.example.tabslider3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class GenderFragment extends Fragment {
    public GenderFragment() {

    }
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    SharedPreferences myPrefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        radioGroup = view.findViewById(R.id.radioGroup);
        myPrefs  = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton1){
                myPrefs.edit().putInt("selected",1).apply();
            }
            else
                myPrefs.edit().putInt("selected",2).apply();
        });
        int s=myPrefs.getInt("selected",0);
        if(s==1){
            radioButton1.setChecked(true);
        } else if(s==2){
            radioButton2.setChecked(true);
        }
        return view;
    }
}
