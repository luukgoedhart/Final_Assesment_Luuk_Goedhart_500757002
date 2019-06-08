package com.example.finalassesment;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GoalsRepository {

    private GoalDao goalDao;
    private LiveData<List<GoalsObject>> allGoals;


    public GoalsRepository(Application application){
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        goalDao = myDatabase.goalDao();
        allGoals = goalDao.getAllGoals();
    }

    public void insert(GoalsObject goalsObject){
        new InsertGoalAsyncTask(goalDao).execute(goalsObject);

    }

    public void update (GoalsObject goalsObject){
        new GoalsRepository.UpdateGoalAsyncTask(goalDao).execute(goalsObject);
    }

    public void delete(GoalsObject goalsObject){
        new GoalsRepository.DeleteGoalAsyncTask(goalDao).execute(goalsObject);
    }

    public void deleteAllGoals(){
        new GoalsRepository.DeleteAllGoalsAsyncTask(goalDao).execute();
    }

    public LiveData<List<GoalsObject>> getAllGoals(){
        return allGoals;
    }

    private static class InsertGoalAsyncTask extends AsyncTask<GoalsObject, Void, Void> {
        private GoalDao goalDao;

        private InsertGoalAsyncTask(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsObject... goalsObjects){
            goalDao.insert(goalsObjects[0]);
            return null;
        }
    }

    private static class DeleteGoalAsyncTask extends AsyncTask<GoalsObject, Void, Void> {
        private GoalDao goalDao;

        private DeleteGoalAsyncTask(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsObject... goalObjects){
            goalDao.delete(goalObjects[0]);
            return null;
        }

    }

    private static class UpdateGoalAsyncTask extends AsyncTask<GoalsObject, Void, Void> {
        private GoalDao goalDao;

        private UpdateGoalAsyncTask(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsObject... goalObjects){
            goalDao.update(goalObjects[0]);
            return null;
        }
    }

    private static class DeleteAllGoalsAsyncTask extends AsyncTask<Void, Void, Void> {
        private GoalDao goalDao;

        private DeleteAllGoalsAsyncTask(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            goalDao.deleteAllGoals();
            return null;
        }
    }

}






