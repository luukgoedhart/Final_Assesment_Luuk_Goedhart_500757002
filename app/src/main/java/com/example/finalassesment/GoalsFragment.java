package com.example.finalassesment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GoalsFragment extends Fragment {
    static int FINANCES_CODE = 0;
    static int HEALTH_CODE = 1;
    static int SOCIAL_CODE = 2;
    static int EDUCATION_CODE = 3;
    private Button confirmBtn;
    private EditText goalTitle;
    private EditText goalDesc;
    private GoalViewModel goalViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goals, container, false);
        confirmBtn = (Button) v.findViewById(R.id.confirmBtn);
        goalTitle = (EditText) v.findViewById(R.id.title_edittext);
        goalDesc = (EditText) v.findViewById(R.id.desc_edittext);
        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel.class);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner1);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Object wordt aangemaakt en toegevoegd aan lijst");
                goalViewModel.insert(new GoalsObject(goalTitle.getText().toString(), goalDesc.getText().toString(),spinner.getSelectedItemPosition()));

                goalTitle.setText("");
                goalDesc.setText("");

            }
        });
        String [] values =
                {"Select Category", "Money and Finances", "Health", "Social", "Education and Learning"};



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;


        //return inflater.inflate(R.layout.fragment_goals, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }


//    @Override
////    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
////        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner1);
////        String[] items = new String[]{"Select an ", "2", "three"};
//////create an adapter to describe how the items are displayed, adapters are used in several places in android.
//////There are multiple variations of this, but this is the basic variant.
////        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, android.R.layout.simple_spinner_item );
//////set the spinners adapter to the previously created one.
////        dropdown.setAdapter(adapter);
////
////    }
//    @Override
//    public boolean isEnabled(int position) {
//        if (position == 0) {
//            // Disable the first item from Spinner
//            // First item will be use for hint
//            return false;
//        } else {
//            return true;
//        }
//    }
    }
