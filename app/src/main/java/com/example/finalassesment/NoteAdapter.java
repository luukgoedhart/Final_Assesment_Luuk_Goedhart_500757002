package com.example.finalassesment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private NoteViewModel noteViewModel;
    private List<NoteObj> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        NoteObj currentNote = notes.get(position);
        noteHolder.noteTitle.setText(currentNote.getTitle());
        noteHolder.noteDesc.setText(currentNote.getShortDesc());//currentNote.getShortDesc());
        noteHolder.noteDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(noteHolder.noteDesc.getText().equals(currentNote.getShortDesc()));
                if(noteHolder.noteDesc.getText().equals(currentNote.getShortDesc())){
                    noteHolder.noteDesc.setText(currentNote.getLongDesc());

                }else{
                    noteHolder.noteDesc.setText(currentNote.getShortDesc());

                }
            }


        });





       // noteHolder.noteDesc.setText(currentNote.getShortDesc());s


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<NoteObj> notes){
        this.notes = notes;
        notifyDataSetChanged(); //Will be changed later
    }

    public NoteObj getNoteAt(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView noteTitle;
        private TextView noteDesc;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.text_title);
            noteDesc = itemView.findViewById(R.id.text_description);
        }
    }

}
