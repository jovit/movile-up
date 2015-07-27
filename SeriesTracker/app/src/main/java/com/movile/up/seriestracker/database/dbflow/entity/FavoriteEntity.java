package com.movile.up.seriestracker.database.dbflow.entity;


import android.provider.BaseColumns;

import com.movile.up.seriestracker.database.dbflow.SeriesTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public FavoriteEntity(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public FavoriteEntity(){

    }

    public String slug(){
        return slug;
    }

    public String title(){
        return title;
    }
}