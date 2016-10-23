package com.multi_threadweek06;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void create(View view) {

        String filename = "numbers.txt";
        String fileContents = "";
        ProgressBar  progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setMax(100);
        int progressStatus = 0;


        for (int i = 1; i <= 10; i++) {
            fileContents += i + "\n";
            progressBar.setProgress(progressStatus += 10);
            try {
                Thread.sleep(250);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
            Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressBar.setProgress(0);
    }

    public void load(View view) {
        final ProgressBar  progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setMax(100);
        int progressStatus = 0;
        ArrayAdapter showArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.mainListView);
        listView.setAdapter(showArray);


        String openFile = getFilesDir() + "/" + "numbers.txt";

        //Get the text file
        File file = new File(openFile);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                showArray.add(line);
                progressBar.setProgress(progressStatus += 10);
                Thread.sleep(250);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(), "File Loaded", Toast.LENGTH_SHORT).show();
    }

    public void clearButton(View view) {

        ArrayAdapter showArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.mainListView);
        listView.setAdapter(showArray);
        showArray.clear();
        showArray.notifyDataSetChanged();

        Toast.makeText(getBaseContext(), "Cleared", Toast.LENGTH_SHORT).show();

    }
}
