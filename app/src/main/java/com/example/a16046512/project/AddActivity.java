package com.example.a16046512.project;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    int reqCode = 12345;
    private EditText etName,etDescription,etTime;
    private Button btnAdd,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        etName = (EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etTime = (EditText)findViewById(R.id.etSeconds);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(AddActivity.this);
                long row_affected = dbh.insertSong(etName.getText().toString(),etDescription.getText().toString(),Integer.parseInt(etTime.getText().toString()));
                dbh.close();

                if (row_affected != -1) {
                    Toast.makeText(AddActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();


                    //launch notification here

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, Integer.parseInt(etTime.getText().toString()));

                    //Create a new PendingIntent and add it to the AlarmManager
                    Intent intent = new Intent(AddActivity.this, BCReceiver.class);
//                    Log.i("tttname",etName.getText().toString());
                    intent.putExtra("name",etName.getText().toString());


                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this,reqCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                    // Get AlarmManager instance
                    AlarmManager am = (AlarmManager)
                            getSystemService(Activity.ALARM_SERVICE);

                    // Set the alarm
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                            pendingIntent);




                    Intent i = new Intent();
                    setResult(RESULT_OK, i);
                    finish();


                }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
