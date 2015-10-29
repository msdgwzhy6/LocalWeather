package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr on 13.10.2015.
 */

@Table(name = "City")
public class City extends Model {
    @Column(name = "name", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("name")
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Column(name = "coord", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    @SerializedName("coord")
    Coord coord;


    public City() {
        super();
    }

    public City(String name, Coord coord) {
        super();
        this.name = name;
        this.coord = coord;

    }

    public Coord getCoord() {
        return coord;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + name + '\'' +
                ", coord=" + coord +
                '}';
    }
}
