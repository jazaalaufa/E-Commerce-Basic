package com.example.finalprojects2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StaffFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.admin_fragment_staff, container, false);

        Button btnadd = (Button) root.findViewById(R.id.btnAddStaff);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStaff();
            }
        });

        return root;
    }

    public void AddStaff() {
        Intent intent = new Intent(getActivity(), StaffAdd.class);
        startActivity(intent);
    }


}
