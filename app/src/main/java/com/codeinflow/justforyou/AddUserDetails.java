package com.codeinflow.justforyou;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.protobuf.Any;

import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class AddUserDetails extends Fragment {

    HashMap<String, String> details = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_user_details, container, false);

        String[] type = {"25 Plates", "50 Plates"};
        String[] mode = {"Cash", "Online", "Other"};

        Spinner type_spin = v.findViewById(R.id.type_spin);
        Spinner mode_spin = v.findViewById(R.id.mode_spin);

        ArrayAdapter a1 = new ArrayAdapter(getActivity(), simple_spinner_item, type);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spin.setAdapter(a1);

        ArrayAdapter a2 = new ArrayAdapter(getActivity(), simple_spinner_item, mode);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mode_spin.setAdapter(a2);


        type_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0: {
                        details.put("type","25");
                    }
                    case 1: {
                        details.put("type","50");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                type_spin.requestFocus();
            }
        });

        mode_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0: {
                        details.put("payment_mode","Cash");
                    }
                    case 1: {
                        details.put("payment_mode","online");
                    }
                    case 2: {
                        details.put("payment_mode","other");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                type_spin.requestFocus();
            }
        });

        return v;
    }


}