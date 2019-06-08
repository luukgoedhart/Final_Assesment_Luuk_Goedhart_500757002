package com.example.finalassesment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NotesFragment extends Fragment {
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EditText input_title;
    private EditText input_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext())); //I guess?


        input_title = (EditText) v.findViewById(R.id.input_title);
        input_description = (EditText) v.findViewById(R.id.input_desc);

        fab = v.findViewById(R.id.fab_add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(input_description.getText().toString().matches("") || input_title.getText().toString().matches(""))){
                    noteViewModel.insert(new NoteObj(input_title.getText().toString(), input_description.getText().toString()));
                    input_title.setText("");
                    input_description.setText("");
                }else{
                    if(input_description.getText().toString().matches("")) {

                        input_description.setHint("This field can't be empty");
                    }
                    if(input_title.getText().toString().matches("")){
                        input_title.setHint("This field can't be empty");
                    }
                }




            }
        });
        setHasOptionsMenu(true);

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteObj>>() {
            @Override
            public void onChanged(@Nullable List<NoteObj> noteObjs) {
                adapter.setNotes(noteObjs);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.notes_menu, menu);
        //moet miss ergens anders
        super.onCreateOptionsMenu(menu,inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(getContext(), "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }





}
