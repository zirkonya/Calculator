package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;

import fr.upjv.calculator.entity.Endurance;

public class ScoreEnduranceDao extends BaseDao<Endurance>{
        static String Name = "Name";
        static String Score = "Score";
        static String Temps = "Temps";

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

}
