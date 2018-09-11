package com.gemini.starwar.database.dao;

/**
 * Created by geminih on 9/11/2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.gemini.starwar.database.entity.CharacterEntity;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM Character")
    List<CharacterEntity> getAll();

    @Query("SELECT * FROM Character where name LIKE  :firstName")
    CharacterEntity findByName(String name);

    @Query("SELECT COUNT(*) from Character")
    int countUsers();

    @Insert
    void insertAll(CharacterEntity... users);

    @Delete
    void delete(CharacterEntity user);
}
