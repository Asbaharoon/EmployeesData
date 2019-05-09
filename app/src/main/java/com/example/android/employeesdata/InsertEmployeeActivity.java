package com.example.android.employeesdata;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class InsertEmployeeActivity extends AppCompatActivity {

    private EditText id,name;
    private RadioButton radioMale,radioFemale;
    private RadioGroup radioGroup;
    private EditText password;
    private Spinner departmentSpinner;
    private Button insertButton,backButton;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
    private String[] departments= {"HR","Marketing","Engineering","Sales","Planning"};
    private ArrayList<String> spinnerData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);

        id = findViewById(R.id.id_et);
        name = findViewById(R.id.name_et);
        radioMale =  findViewById(R.id.radio_male);
        radioFemale = findViewById(R.id.radio_female);
        radioGroup=findViewById(R.id.radioGroup);
        departmentSpinner = findViewById(R.id.dep_spinner);
        password = findViewById(R.id.password_et);
        insertButton =  findViewById(R.id.insertButton);
        backButton=findViewById(R.id.back_btn);

       addDepartment(path+"Departments.txt",departments);


        readDepartments(path+"Departments.txt");

       ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerData);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        departmentSpinner.setAdapter(arrayAdapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender="";
                if(radioMale.isChecked())
                    gender = "Male";
                else if(radioFemale.isChecked())
                    gender = "Female";

                if(id.getText().toString().isEmpty() || name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || departmentSpinner.getSelectedItem().toString().isEmpty() )
                {
                    Toast.makeText(InsertEmployeeActivity.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    String EmployeeText = id.getText().toString()+"-"+name.getText().toString()+"-"+departmentSpinner.getSelectedItem()
                            +"-"+password.getText().toString()+"-"+gender;

                    insertEmployee(path+"employees.txt",EmployeeText);

                    id.setText("");
                    name.setText("");
                    departmentSpinner.setSelection(0,false);
                    password.setText("");
                    radioGroup.clearCheck();
                }



            }
        });
    }





        private void insertEmployee(String fileName, String EmployeeText) {
        File empFile = new File(fileName);
        try {
            if (!empFile.exists())
                empFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(empFile, true);
            fileOutputStream.write((EmployeeText + System.getProperty("line.separator")).getBytes());
            Toast.makeText(getApplicationContext(), "Employee data inserted in "+path, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }



    private void addDepartment(String fileName, String[] DepText) {
        File depFile = new File(fileName);
        try {
            if (!depFile.exists())
            {
                depFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(depFile, true);
                for(int i=0 ; i<DepText.length ; i++)
                {
                    fileOutputStream.write((DepText[i] + System.getProperty("line.separator")).getBytes());
                }

            }


        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    private void readDepartments(String fileName)
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            line = bufferedReader.readLine();
            while(line!=null)
            {
                spinnerData.add(line);

                line = bufferedReader.readLine();
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}




