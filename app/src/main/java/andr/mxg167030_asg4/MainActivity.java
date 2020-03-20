package andr.mxg167030_asg4;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView scoreListView = (ListView) findViewById(R.id.high_score_list);

        Database.initializeDatabase(getApplicationContext());

        Database.addHighScore(getApplicationContext(), new HighScore("e", new Date(System.currentTimeMillis()-100), 1000));
        System.out.println("*******************8" + Database.getScores(getApplicationContext()).toString());

        Database.addHighScore(getApplicationContext(), new HighScore("f", new Date(System.currentTimeMillis()-200), 1000));
//        Database.addHighScore(getApplicationContext(), new HighScore("d", new Date(System.currentTimeMillis()), 30));
//        Database.addHighScore(getApplicationContext(), new HighScore("c", new Date(System.currentTimeMillis()), 1565));
//        Database.addHighScore(getApplicationContext(), new HighScore("a", new Date(System.currentTimeMillis()), 1234));
//        Database.addHighScore(getApplicationContext(), new HighScore("b", new Date(System.currentTimeMillis()), 4568));
//        Database.addHighScore(getApplicationContext(), new HighScore("g", new Date(System.currentTimeMillis()), 2222));
//        Database.addHighScore(getApplicationContext(), new HighScore("h", new Date(System.currentTimeMillis()), 0));
//        Database.addHighScore(getApplicationContext(), new HighScore("i", new Date(System.currentTimeMillis()), 654));
//        Database.addHighScore(getApplicationContext(), new HighScore("j", new Date(System.currentTimeMillis()), 487));
//        Database.addHighScore(getApplicationContext(), new HighScore("k", new Date(System.currentTimeMillis()), 565));
//        Database.addHighScore(getApplicationContext(), new HighScore("l", new Date(System.currentTimeMillis()), 123));
//        Database.addHighScore(getApplicationContext(), new HighScore("m", new Date(System.currentTimeMillis()), 0000));
//        Database.addHighScore(getApplicationContext(), new HighScore("n", new Date(System.currentTimeMillis()), 5656));
//        Database.addHighScore(getApplicationContext(), new HighScore("o", new Date(System.currentTimeMillis()), 1321));
//        Database.addHighScore(getApplicationContext(), new HighScore("p", new Date(System.currentTimeMillis()), 10000));
//        Database.addHighScore(getApplicationContext(), new HighScore("q", new Date(System.currentTimeMillis()), 1515));
//        Database.addHighScore(getApplicationContext(), new HighScore("r", new Date(System.currentTimeMillis()), 3));

        System.out.println("*******************8" + Database.getScores(getApplicationContext()));
        ScoreListAdapter scoreListAdapter = new ScoreListAdapter(this, R.layout.score_adapter_view_layout, Database.getScores(this));
        scoreListView.setAdapter(scoreListAdapter);
    }
}
