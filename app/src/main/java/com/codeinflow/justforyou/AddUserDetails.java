package com.codeinflow.justforyou;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddUserDetails extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_user_details, container, false);

        String[] type = {"25 Plates", "50 Plates"};
        String[] mode = {"Cash", "Online", "Other"};

        return v;
    }
}