package com.example.finalassesment;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GoalDao {



        @Insert
        void insert(GoalsObject goal);

        @Update
        void update(GoalsObject goal);

        @Delete
        void delete(GoalsObject goals);

        @Query("DELETE FROM goals_table")
        void deleteAllGoals();

        @Query("SELECT * FROM goals_table ORDER BY id ASC")
        LiveData<List<GoalsObject>> getAllGoals();

        @Query("SELECT * FROM goals_table WHERE type = 1 ORDER BY id ASC")
        LiveData<List<GoalsObject>> getAllMoneyGoals();

        @Query("SELECT * FROM goals_table ORDER BY id ASC")
        LiveData<List<GoalsObject>> getAllScholGoals();

        @Query("SELECT * FROM goals_table ORDER BY id ASC")
        LiveData<List<GoalsObject>> getAllHealthGoals();

        @Query("SELECT * FROM goals_table ORDER BY id ASC")
        LiveData<List<GoalsObject>> getAllSocialGoals();



}

