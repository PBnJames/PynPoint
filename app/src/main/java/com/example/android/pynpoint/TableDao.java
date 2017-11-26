package com.example.android.pynpoint;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTrophy(Table trophy);

    @Query("SELECT * FROM trophy WHERE userId=:userId")
    List<Table> findTrophiesForUser(int userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrophy(Table trophy);

    @Query("delete from trophy where id = :id")
    void delete(long id);

}