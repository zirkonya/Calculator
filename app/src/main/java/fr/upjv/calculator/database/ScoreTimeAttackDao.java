package fr.upjv.calculator.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.upjv.calculator.entity.Chrono;
import fr.upjv.calculator.entity.TimeAttack;

public class ScoreTimeAttackDao extends BaseDao<TimeAttack>{

        static String Name = "Name";
        static String Score = "Score";
        private SQLiteDatabase bd;

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

    protected TimeAttack[] RecupSql () {
        TimeAttack[] timeattack = new TimeAttack[10];
        String requete = "SELECT * FROM classementTimeAttack ORDER BY Score ASC LIMIT 10 ";
        Cursor curseur = bd.rawQuery(requete , null);
        curseur.moveToLast();
        for (int index=0; index<=9 ; index++) {
            if (!curseur.isAfterLast()) {
                timeattack[index] = new TimeAttack();
                Integer name = curseur.getColumnIndex(Name);
                timeattack[index].setName(curseur.getString(name));
                Integer score = curseur.getColumnIndex(Score);
                timeattack[index].setScore(curseur.getInt(score));
            }
        }
        return timeattack;
    }
}
