package com.example.finalassesment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class GoalViewModel extends AndroidViewModel {
    private GoalsRepository repository;
    private LiveData<List<GoalsObject>> allGoals;

    public GoalViewModel(@NonNull Application application) {
        super(application);
        repository = new GoalsRepository(application);
        allGoals = repository.getAllGoals();

    }

    public void insert(GoalsObject goal){
        repository.insert(goal);
    }
    public void delete(GoalsObject goal){
        repository.delete(goal);
    }
    public void update(GoalsObject goal){
        repository.update(goal);
    }
    public void deleteAllGoals(){
        repository.deleteAllGoals();
    }
    public LiveData<List<GoalsObject>> getAllGoals(){
        return allGoals;
    }
}

