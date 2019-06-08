package com.example.finalassesment;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(NoteObj note);

    @Update
    void update(NoteObj note);

    @Delete
    void delete(NoteObj note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    LiveData<List<NoteObj>> getAllNotes();



}
