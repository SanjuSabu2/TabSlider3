package com.example.tabslider3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class NameFragment extends Fragment {
    public NameFragment() {
    }
    EditText name;
    SharedPreferences sharedPreferences;
    String preferences = "pref", nameId = "name", nValue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        sharedPreferences = getContext().getSharedPreferences(preferences, MODE_PRIVATE);
        name = view.findViewById(R.id.Name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                nValue = name.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(nameId, nValue);
                editor.apply();
            }
        });
        SharedPreferences prefs = getContext().getSharedPreferences(preferences, MODE_PRIVATE);
        String loadedName = prefs.getString(nameId, null);
        name.setText(loadedName);
        return view;
    }
}