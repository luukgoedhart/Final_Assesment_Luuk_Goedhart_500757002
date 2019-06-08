package com.example.finalassesment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "goals_table")
public class GoalsObject {
    private String title;
    private int type;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoalsObject(String title, String description, int type){
        this.title = title;
        this.description = title;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }

    public int getType(){
        return type;
    }

}
