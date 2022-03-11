package fr.upjv.calculator.database;

import android.content.Context;

public class ScoreChronoBaseHelper extends DataBaseHelper{

    public ScoreChronoBaseHelper(Context context) {
        super(context, "Score", 1);
    }

    @Override
    protected String getCreationSql() {
        String score = "CREATE TABLE IF NOT EXISTS classementChrono (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                ScoreChronoDao.Name + " VARCHAR(255) NOT NULL," +
                ScoreChronoDao.Score + " INTEGER NOT NULL," +
                ")";
        return score;

    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
