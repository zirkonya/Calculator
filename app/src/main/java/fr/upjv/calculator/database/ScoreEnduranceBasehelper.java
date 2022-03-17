package fr.upjv.calculator.database;

import android.content.Context;

public class ScoreEnduranceBasehelper extends DataBaseHelper {

        public ScoreEnduranceBasehelper(Context context) {

            super(context, "Score", 1);
        }

        @Override
        protected String getCreationSql() {
            String score = "CREATE TABLE IF NOT EXISTS classementChrono (" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ScoreEnduranceDao.Name + " VARCHAR(255) NOT NULL," +
                    ScoreEnduranceDao.Score + " INTEGER NOT NULL," +
                    ScoreEnduranceDao.Temps + "INTEGER NOT NULL" +
                    ")";
            return score;

        }

        @Override
        protected String getDeleteSql() {
            return null;
        }

}
