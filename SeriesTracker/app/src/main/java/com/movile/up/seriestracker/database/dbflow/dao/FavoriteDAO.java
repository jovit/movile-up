package com.movile.up.seriestracker.database.dbflow.dao;

import android.content.Context;
import android.database.Cursor;

import com.movile.up.seriestracker.database.dbflow.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.dbflow.entity.FavoriteEntity$Table;
import com.movile.up.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

public class FavoriteDAO {

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save();
    }

    public Cursor all() {
        return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }

    public Favorite query(String slug) {
        FavoriteEntity entity = new Select().from(FavoriteEntity.class).where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug)).querySingle();
        if(entity != null) {
            return new Favorite(entity.slug(), entity.title());
        }else{
            return null;
        }
    }

    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();
    }

}