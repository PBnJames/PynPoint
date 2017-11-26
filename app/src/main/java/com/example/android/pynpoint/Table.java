package com.example.android.pynpoint;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity
        (tableName = "trophy",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = { @Index(value = "id")}
)
public class Table {

    @PrimaryKey(autoGenerate = true)
    long id;

    public long userId;

    String description;

    public Table(long userId, String description)
    {
        this.userId = userId;
        this.description = description;
    }
}