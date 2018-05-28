package com.example.a16046512.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task>{
    private ArrayList<Task> task;
    private Context context;
    private TextView tvidname,tvdescription;

    public TaskAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        task = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvidname = (TextView) rowView.findViewById(R.id.tvidname);
        // Get the ImageView object
        tvdescription = (TextView) rowView.findViewById(R.id.tvdescription);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Task currentTask = task.get(position);
        // Set the TextView to show the food

        tvidname.setText(currentTask.getId()+" "+currentTask.getTaskname());
        tvdescription.setText(currentTask.getTaskdescription());

        return rowView;
    }

}
