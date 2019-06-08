package com.example.finalassesment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteObj {
    final static int MAX_SHORT_TEXT = 46;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String longDesc;

    private String shortDesc;

    private String title;

    public NoteObj(String title, String longDesc){
        this.title = title;
        this.longDesc = longDesc;
        this.shortDesc = getShortDesc();
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }


    public String getTitle(){
        return title;
    }

    public String getLongDesc(){
        return longDesc;
    }

    public String getShortDesc(){
        String tempShortDesc = "";
        if(longDesc.length() > MAX_SHORT_TEXT){

            char[] characters = longDesc.toCharArray();
            for(int i = 0; i < MAX_SHORT_TEXT; i++){
                 tempShortDesc+= characters[i];
            }

            tempShortDesc+= "...";
        }else{
            tempShortDesc = longDesc;
        }
        return tempShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
