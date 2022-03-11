package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;

import fr.upjv.calculator.entity.Chrono;

public class ScoreChronoDao extends BaseDao<Chrono>{
    static String Name = "Name";
    static String Score = "Score";

    public ScoreChronoDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "classementChrono";
    }

    @Override
    protected void putValues(ContentValues values, Chrono entity) {
        values.put(Name,entity.getName());
        values.put(Score,entity.getScore());
    }

    @Override
    protected Chrono getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Chrono chrono = new Chrono();
        Integer name = cursor.getColumnIndex(Name);
        Chrono.setName(cursor.getString(name));
        Integer score = cursor.getColumnIndex(Score);
        Chrono.setScore(cursor.getInt(score));
        return chrono;
    }
}
