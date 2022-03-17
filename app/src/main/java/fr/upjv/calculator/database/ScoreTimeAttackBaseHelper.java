package fr.upjv.calculator.database;

import android.content.Context;

public class ScoreTimeAttackBaseHelper extends DataBaseHelper {

        public ScoreTimeAttackBaseHelper(Context context) {

            super(context, "Score", 1);
        }

        @Override
        protected String getCreationSql() {
            String score = "CREATE TABLE IF NOT EXISTS classementChrono (" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ScoreTimeAttackDao.Name + " VARCHAR(255) NOT NULL," +
                    ScoreTimeAttackDao.Score + " INTEGER NOT NULL," +
                    ")";
            return score;

        }

        @Override
        protected String getDeleteSql() {
            return null;
        }

}
