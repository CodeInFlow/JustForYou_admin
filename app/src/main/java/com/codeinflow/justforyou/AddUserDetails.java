package com.codeinflow.justforyou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static android.R.layout.simple_spinner_item;

public class AddUserDetails extends Fragment {

    HashMap<String, String> details = new HashMap<>();
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_user_details, container, false);

        String[] type = {"25 Plates", "50 Plates"};
        String[] mode = {"Cash", "Online", "Other"};

        Spinner type_spin = v.findViewById(R.id.type_spin);
        Spinner mode_spin = v.findViewById(R.id.mode_spin);

        String mobile = getArguments().getString("phonenum");
        details.put("number",mobile);

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

    private void addDataToFirestore() {
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Users");
        collectionReference.add(details)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getActivity(),"User added",Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container1, new Home());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Please try again",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}