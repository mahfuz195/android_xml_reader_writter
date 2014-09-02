package com.mahfuz.xmlreaderwriter;

import android.support.v4.os.EnvironmentCompat;
import android.support.v4.util.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Environment;
import java.util.ArrayList;
import android.util.Log;
import android.widget.TextView;
public class MainActivity extends ActionBarActivity {

    static TextView tx ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView)findViewById(R.id.textView2);
        if(isExternalStorageWritable()){
            XMLHandler handler = new XMLHandler(this);
            try{
                String fileName = "data1.xml";

                ArrayList<Box> list = new ArrayList<Box>();
                Box box1 = new Box();
                box1.setBox_length(10); box1.setBox_width(5); box1.setBox_name("Box 1"); box1.setBox_color("Red");
                Box box2 = new Box();
                box2.setBox_length(12); box2.setBox_width(9); box2.setBox_name("Box 2"); box2.setBox_color("Green");
                Box box3 = new Box();
                box3.setBox_length(14); box3.setBox_width(10); box3.setBox_name("Box 3"); box3.setBox_color("Blue");
                Box box4 = new Box();
                box4.setBox_length(18); box4.setBox_width(4); box4.setBox_name("Box 4"); box4.setBox_color("Orange");
                Box box5 = new Box();
                box5.setBox_length(12); box5.setBox_width(6); box5.setBox_name("Box 5"); box5.setBox_color("Black");

                list.add(box1);
                list.add(box2);
                list.add(box3);
                list.add(box4);
                list.add(box5);

                handler.createXmlFile(fileName,list);



            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Log.d("TAG","not writeable");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
