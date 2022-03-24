package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.upjv.calculator.entity.Chrono;
import fr.upjv.calculator.entity.Endurance;

public class ScoreEnduranceDao extends BaseDao<Endurance>{
        static String Name = "Name";
        static String Score = "Score";
        static String Temps = "Temps";
        private SQLiteDatabase bd;

        public ScoreEnduranceDao(DataBaseHelper helper) {
            super(helper);
        }

        @Override
        protected String getTableName() {
            return "classementEndurance";
        }

        @Override
        protected void putValues(ContentValues values, Endurance entity) {
            values.put(Name,entity.getName());
            values.put(Score,entity.getScore());
            values.put(Temps, entity.getTemps());
        }

        @Override
        protected Endurance getEntity(Cursor cursor) {
            cursor.moveToFirst();
            Endurance endurance = new Endurance();
            Integer name = cursor.getColumnIndex(Name);
            endurance.setName(cursor.getString(name));
            Integer score = cursor.getColumnIndex(Score);
            endurance.setScore(cursor.getInt(score));
            Integer temps = cursor.getColumnIndex(Temps);
            endurance.setTemps(cursor.getLong(temps));
            return endurance;
        }

    protected Endurance[] RecupSql () {
        Endurance[] endurance = new Endurance[10];
        String requete = "SELECT * FROM classementEndurance ORDER BY Score ASC LIMIT 10 ";
        Cursor curseur = bd.rawQuery(requete , null);
        curseur.moveToLast();
        for (int index=0; index<=9 ; index++) {
            if (!curseur.isAfterLast()) {
                endurance[index] = new Endurance();
                Integer name = curseur.getColumnIndex(Name);
                endurance[index].setName(curseur.getString(name));
                Integer score = curseur.getColumnIndex(Score);
                endurance[index].setScore(curseur.getInt(score));
                Integer temps = curseur.getColumnIndex(Temps);
                endurance[index].setTemps(curseur.getLong(temps));
            }
        }

        return endurance;
    }

}
