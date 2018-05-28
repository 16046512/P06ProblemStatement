package com.example.a16046512.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private TaskAdapter ta;
    private ArrayList<Task> tasks;
    private Button btnAddTask;

    private DBHelper dbh = new DBHelper(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TaskManager");
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        lv = (ListView)findViewById(R.id.lv);

        tasks = dbh.getAllTask();




        ta = new TaskAdapter(this,R.layout.row,tasks);
        lv.setAdapter(ta);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task selectTask = tasks.get(i);
                Toast.makeText(MainActivity.this,selectTask.getId()+selectTask.getTaskname()+selectTask.getTaskdescription(),Toast.LENGTH_LONG).show();
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(i,9);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            tasks.clear();
            DBHelper dbh = new DBHelper(this);
            tasks.addAll(dbh.getAllTask());
            ta.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Update successful",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
