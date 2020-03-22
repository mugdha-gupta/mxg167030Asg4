package andr.mxg167030_asg4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView scoreListView;
    ScoreListAdapter scoreListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreListView = findViewById(R.id.high_score_list);

        Database.initializeDatabase(getApplicationContext());

//        Database.addHighScore(getApplicationContext(), new HighScore("e", new Date(System.currentTimeMillis()-100), 1000));
//        System.out.println("*******************8" + Database.highScores.toString());
//
//        Database.addHighScore(getApplicationContext(), new HighScore("f", new Date(System.currentTimeMillis()-200), 1000));
////        Database.addHighScore(getApplicationContext(), new HighScore("d", new Date(System.currentTimeMillis()), 30));
////        Database.addHighScore(getApplicationContext(), new HighScore("c", new Date(System.currentTimeMillis()), 1565));
////        Database.addHighScore(getApplicationContext(), new HighScore("a", new Date(System.currentTimeMillis()), 1234));
////        Database.addHighScore(getApplicationContext(), new HighScore("b", new Date(System.currentTimeMillis()), 4568));
////        Database.addHighScore(getApplicationContext(), new HighScore("g", new Date(System.currentTimeMillis()), 2222));
////        Database.addHighScore(getApplicationContext(), new HighScore("h", new Date(System.currentTimeMillis()), 0));
////        Database.addHighScore(getApplicationContext(), new HighScore("i", new Date(System.currentTimeMillis()), 654));
////        Database.addHighScore(getApplicationContext(), new HighScore("j", new Date(System.currentTimeMillis()), 487));
////        Database.addHighScore(getApplicationContext(), new HighScore("k", new Date(System.currentTimeMillis()), 565));
////        Database.addHighScore(getApplicationContext(), new HighScore("l", new Date(System.currentTimeMillis()), 123));
////        Database.addHighScore(getApplicationContext(), new HighScore("m", new Date(System.currentTimeMillis()), 0000));
////        Database.addHighScore(getApplicationContext(), new HighScore("n", new Date(System.currentTimeMillis()), 5656));
////        Database.addHighScore(getApplicationContext(), new HighScore("o", new Date(System.currentTimeMillis()), 1321));
////        Database.addHighScore(getApplicationContext(), new HighScore("p", new Date(System.currentTimeMillis()), 10000));
////        Database.addHighScore(getApplicationContext(), new HighScore("q", new Date(System.currentTimeMillis()), 1515));
////        Database.addHighScore(getApplicationContext(), new HighScore("r", new Date(System.currentTimeMillis()), 3));
//
//        System.out.println("*******************8" + Database.getScores(getApplicationContext()));
        updateListView();
    }

    private void updateListView() {
        scoreListAdapter = new ScoreListAdapter(this, R.layout.score_adapter_view_layout, Database.getScores(this));
        scoreListView.setAdapter(scoreListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Intent intent = new Intent(MainActivity.this, AddNewScoreActivity.class);
        if (item.getItemId() == R.id.action_add_new) {
            startActivityForResult(intent, 0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==0)
        {
            if(resultCode == Activity.RESULT_OK){
                HighScore hs = (HighScore) data.getSerializableExtra("HighScore");
                if(hs == null)
                    System.out.println("*********");
                Database.addHighScore(getApplicationContext(), hs);
                updateListView();
            }
        }
    }
}
