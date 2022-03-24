package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.upjv.calculator.entity.Chrono;

public class ScoreChronoDao extends BaseDao<Chrono>{
    static String Name = "Name";
    static String Score = "Score";
    private SQLiteDatabase bd;

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
        chrono.setName(cursor.getString(name));
        Integer score = cursor.getColumnIndex(Score);
        chrono.setScore(cursor.getInt(score));
        return chrono;
    }

    protected Chrono[] RecupSql () {
        Chrono[] chrono = new Chrono[10];
        String requete = "SELECT * FROM classementChrono ORDER BY Score ASC LIMIT 10 ";
        Cursor curseur = bd.rawQuery(requete , null);
        curseur.moveToLast();
        for (int index=0; index<=9 ; index++) {
            if (!curseur.isAfterLast()) {
                chrono[index] = new Chrono();
                Integer name = curseur.getColumnIndex(Name);
                chrono[index].setName(curseur.getString(name));
                Integer score = curseur.getColumnIndex(Score);
                chrono[index].setScore(curseur.getInt(score));
            }
        }

        return chrono;
    }

}
