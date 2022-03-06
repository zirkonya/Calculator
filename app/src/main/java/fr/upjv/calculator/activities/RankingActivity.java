package fr.upjv.calculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import fr.upjv.calculator.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity {
    private TextView icon;
    private TextView score;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        icon = new TextView(this);
        icon.setTextSize(30);
        icon.setGravity(View.TEXT_ALIGNMENT_CENTER);

    }

    private void uneFonction() {
        TextView textView = name;

    }

}