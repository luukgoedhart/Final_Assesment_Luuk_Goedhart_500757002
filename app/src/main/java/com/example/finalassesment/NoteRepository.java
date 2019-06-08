package com.example.finalassesment;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<NoteObj>> allNotes;


    public NoteRepository(Application application){
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        noteDao = myDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(NoteObj note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update (NoteObj note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(NoteObj note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<NoteObj>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<NoteObj, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteObj... noteObjs){
            noteDao.insert(noteObjs[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<NoteObj, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteObj... noteObjs){
            noteDao.delete(noteObjs[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<NoteObj, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteObj... noteObjs){
            noteDao.update(noteObjs[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
