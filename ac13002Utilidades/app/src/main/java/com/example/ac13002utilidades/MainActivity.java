package com.example.ac13002utilidades;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    String[] menu={"Audio","Video","Camara","TextToSpeech","Speech","GPS","Acelerometro","Gestos"};
    String[] activities={"AudioActivity","VideoActivity","CamaraActivity","TextToSpeechActivity","SpeechActivity","GPSActivity",
            "AcelerometroActivity","GestosActivity"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = getListView();
        listView.setBackgroundColor(Color.rgb(32, 178, 170));
        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,35);
                // Return the view
                return view;
            }
        };
        setListAdapter(arrayAdapter);
    }
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(0, 0, 0));
        try{
            Class<?> clase=Class.forName("com.example.ac13002utilidades."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}