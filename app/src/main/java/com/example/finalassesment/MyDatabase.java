package com.example.finalassesment;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

@Database(entities = {NoteObj.class, GoalsObject.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract NoteDao noteDao();
    public abstract GoalDao goalDao();

    public static synchronized MyDatabase getInstance (Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "my_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase database){
            super.onCreate(database);
            new PopulateDBAsyncTask(instance).execute();

        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private GoalDao goalDao;
        private PopulateDBAsyncTask(MyDatabase database){
            noteDao = database.noteDao();
            goalDao = database.goalDao();
        }

        @Override
        protected Void doInBackground(Void... Voids){
            return null;
        }

    }
}
