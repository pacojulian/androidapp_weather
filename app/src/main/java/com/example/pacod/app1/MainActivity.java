package com.example.pacod.app1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView ;
    private ArrayList<String> ciudades;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.ciudades);

      ciudades = new ArrayList<String>();

        ciudades.add("Mexico City");
        ciudades.add("London");
        ciudades.add("Paris");


       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ciudades);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;
                String variable = null;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);


                Intent intent = new Intent(MainActivity.this,Weather.class);
                intent.putExtra("itemValue", itemValue);
                startActivity(intent);
            }

        });

    }
    public void agregar(View v) {
        //Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
        EditText edit=(EditText)findViewById(R.id.edit);


        ciudades.add(edit.getText().toString());
        adapter.notifyDataSetChanged();
        edit.setText("");
    }



}
