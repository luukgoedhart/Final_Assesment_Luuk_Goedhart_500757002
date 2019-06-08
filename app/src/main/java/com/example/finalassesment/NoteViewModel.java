package com.example.finalassesment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<NoteObj>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();

    }

    public void insert(NoteObj note){
        repository.insert(note);
    }
    public void delete(NoteObj note){
        repository.delete(note);
    }
    public void update(NoteObj note){
        repository.update(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
    public LiveData<List<NoteObj>> getAllNotes(){
        return allNotes;
    }
}
