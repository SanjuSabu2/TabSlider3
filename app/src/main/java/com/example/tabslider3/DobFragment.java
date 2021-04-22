package com.example.tabslider3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class DobFragment extends Fragment {
    public DobFragment() {
    }
    EditText dob;
    Calendar calendar = Calendar.getInstance();
//    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
    SharedPreferences sharedPreferences;
    String preferences = "pref", dateOfBirth = "dob",date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dob, container, false);
        dob =  view.findViewById(R.id.dob);
        sharedPreferences = getActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE);
        DatePickerDialog.OnDateSetListener
                pickDate = (view1, year, month, dayOfMonth) -> {
            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            month = month + 1;
            String s = dayOfMonth + "/" + month  + "/" + year ;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date strDate = sdf.parse(s);
                if (new Date().after(strDate)){
                    dob.setText(s);
                }
                else {
                    Toast.makeText(getContext(), "Enter a Valid Date", Toast.LENGTH_SHORT).show();
                    dob.setText("");
                    calendar = Calendar.getInstance();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        dob.setOnClickListener(v -> {
//                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            hideKeyboardFrom(getContext(), getView());
            new DatePickerDialog(getContext(), pickDate,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        });

        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                date = dob.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(dateOfBirth,date);
                editor.apply();
            }
        });

        SharedPreferences prefs = getContext().getSharedPreferences(preferences, MODE_PRIVATE);
        String loadedDob = prefs.getString(dateOfBirth, null);
        dob.setText(loadedDob);
        return view;
    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}