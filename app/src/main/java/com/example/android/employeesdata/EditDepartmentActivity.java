package com.example.android.employeesdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDepartmentActivity extends AppCompatActivity {

    private Button back,save;
    private EditText departmentsTxtFile;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_department);
        back=findViewById(R.id.back_btn);
        save=findViewById(R.id.save_btn);
        departmentsTxtFile=findViewById(R.id.edit_dep_file);

        viewDepartmentsInEditText(path+"Departments.txt");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                departmentsTxtFile.setEnabled(false);

                editDepartment(path+"Departments.txt",departmentsTxtFile.getText().toString());

            }
        });





    }


    private void viewDepartmentsInEditText(String fileName)
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            line = bufferedReader.readLine();
            while(line!=null)
            {
                departmentsTxtFile.append(line+"\n");

                line = bufferedReader.readLine();
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }



    private void editDepartment(String fileName, String DepText) {
        File depFile = new File(fileName);
        try {
            if (!depFile.exists())
                depFile.createNewFile();


                FileOutputStream fileOutputStream = new FileOutputStream(depFile, false);

                fileOutputStream.write((DepText).getBytes());


        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


}
