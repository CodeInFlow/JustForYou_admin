package com.codeinflow.justforyou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MessMenu extends Fragment {

    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Button update_menu;

    HashMap<String,String> items = new HashMap<>();

    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mess_menu, container, false);

        et1 = v.findViewById(R.id.et1);
        et2 = v.findViewById(R.id.et2);
        et3 = v.findViewById(R.id.et3);
        et4 = v.findViewById(R.id.et4);
        et5 = v.findViewById(R.id.et5);
        et6 = v.findViewById(R.id.et6);
        et7 = v.findViewById(R.id.et7);
        et8 = v.findViewById(R.id.et8);

        update_menu = v.findViewById(R.id.update_menu);

        update_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDataToFirestore();

            }
        });

        return v;
    }

    private void adddata() {
        items.put("m1",et1.getText().toString());
        items.put("m2",et2.getText().toString());
        items.put("m3",et3.getText().toString());
        items.put("m4",et4.getText().toString());
        items.put("m5",et5.getText().toString());
        items.put("m6",et6.getText().toString());
        items.put("m7",et7.getText().toString());
        items.put("m8",et8.getText().toString());
    }

    private void addDataToFirestore() {
        adddata();
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Menu");
        collectionReference.add(items)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getActivity(), "Menu Updated", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container1, new Home());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}