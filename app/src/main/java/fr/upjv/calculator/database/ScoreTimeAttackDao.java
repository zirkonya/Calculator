package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;

import fr.upjv.calculator.entity.TimeAttack;

public class ScoreTimeAttackDao extends BaseDao<TimeAttack>{

        static String Name = "Name";
        static String Score = "Score";

        public ScoreTimeAttackDao(DataBaseHelper helper) {
            super(helper);
        }

        @Override
        protected String getTableName() {
            return "classementTimeAttack";
        }

        @Override
        protected void putValues(ContentValues values, TimeAttack entity) {
            values.put(Name,entity.getName());
            values.put(Score,entity.getScore());
        }

        @Override
        protected TimeAttack getEntity(Cursor cursor) {
            cursor.moveToFirst();
            TimeAttack timeAttack = new TimeAttack();
            Integer name = cursor.getColumnIndex(Name);
            timeAttack.setName(cursor.getString(name));
            Integer score = cursor.getColumnIndex(Score);
            timeAttack.setScore(cursor.getInt(score));
            return timeAttack;
        }

}
