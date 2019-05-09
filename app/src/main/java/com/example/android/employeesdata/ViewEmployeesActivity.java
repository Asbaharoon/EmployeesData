package com.example.android.employeesdata;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewEmployeesActivity extends AppCompatActivity {

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
    private List<String> arrayParts=new ArrayList<>();
    private String[] stringParts;
    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_employees);
        backButton=findViewById(R.id.back_btn);

        fromfileToArray(path+"employees.txt");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayParts);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void fromfileToArray(String fileName)
    {
        try {
            File File =  new File(fileName);
            if (!File.exists())
                File.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(File);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            line = bufferedReader.readLine();
            while(line!=null)
            {
                stringParts=line.split("-");

                String passwordFile=stringParts[3];

                    stringParts[0]=" ID : "+stringParts[0] + "\n";
                    stringParts[1]="Username : "+stringParts[1] + "\n";
                    stringParts[2]="Department : "+stringParts[2] + "\n";
                    stringParts[3]="Gender : "+stringParts[3];


                arrayParts.add(String.valueOf(Arrays.asList(stringParts)).replace(passwordFile,"").replaceAll("\\]","").replaceAll("\\[","").replaceAll("[,]","")) ;

                line = bufferedReader.readLine();
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}

