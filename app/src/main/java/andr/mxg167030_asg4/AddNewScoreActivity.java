package andr.mxg167030_asg4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewScoreActivity extends AppCompatActivity {
    private final Calendar myCalendarInstance = Calendar.getInstance();
    private DatePickerDialog myDatePicker;
    private EditText nameEditText, dateEditText, scoreEditText;
    private Button saveButton;
    private SimpleDateFormat dateFormat;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_score);

        dateFormat = new SimpleDateFormat("MM/dd/yyy" , Locale.US);

        nameEditText = findViewById(R.id.name_edit_view);
        dateEditText = findViewById(R.id.date_edit_view);
        scoreEditText = findViewById(R.id.score_edit_view);
        saveButton = findViewById(R.id.save_button);
        dateEditText.setText(dateFormat.format(new Date(System.currentTimeMillis())));
        saveButton.setEnabled(false);

        dateEditText.setOnClickListener(getDateOnClickListener());

        nameEditText.addTextChangedListener(getOnTextChangedListener());
        dateEditText.addTextChangedListener(getOnTextChangedListener());
        scoreEditText.addTextChangedListener(getOnTextChangedListener());

        saveButton.setOnClickListener(getSaveOnClickListener());
    }

    private View.OnClickListener getSaveOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                int score = Integer.parseInt(scoreEditText.getText().toString());
                HighScore hs = new HighScore(name, date, score);
                System.out.println("******************* " + hs.toString());
                Intent intent = new Intent();
                intent.putExtra("HighScore", hs);
                setResult(RESULT_OK, intent);

                finish();
            }
        };

    }

    private TextWatcher getOnTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkValidHighScoreEntered();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public View.OnClickListener getDateOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatePicker = new DatePickerDialog(AddNewScoreActivity.this, getOnDateSetListener(), myCalendarInstance.get(Calendar.YEAR),
                        myCalendarInstance.get(Calendar.MONTH), myCalendarInstance.get(Calendar.DAY_OF_MONTH));
                myDatePicker.show();
            }
        };
    }

    private DatePickerDialog.OnDateSetListener getOnDateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarInstance.set(Calendar.YEAR, year);
                myCalendarInstance.set(Calendar.MONTH, month);
                myCalendarInstance.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date = myCalendarInstance.getTime();
                dateEditText.setText(dateFormat.format(date));
            }
        };
    }

    private void checkValidHighScoreEntered() {
        String name = nameEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        int score = Integer.parseInt(scoreEditText.getText().toString().trim());

        saveButton.setEnabled(!name.isEmpty() && !date.isEmpty() && score > 0);
    }

}


