package com.example.android.employeesdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcomeTextview;
    Button insertEmployeeButton,viewEmployeesButton,editDepartmentButton,logoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        welcomeTextview=findViewById(R.id.welcome_tv);
        insertEmployeeButton=findViewById(R.id.choose_insert_employee);
        viewEmployeesButton=findViewById(R.id.choose_view_employees);
        editDepartmentButton=findViewById(R.id.choose_edit_department);
        logoutButton=findViewById(R.id.logout_btn);

        Bundle b = getIntent().getExtras();
        String username = b.getString("userName");

        welcomeTextview.setText("Welcome "+username);

        insertEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WelcomeActivity.this,InsertEmployeeActivity.class));
            }
        });


        viewEmployeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WelcomeActivity.this,ViewEmployeesActivity.class));
            }
        });


        editDepartmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WelcomeActivity.this,EditDepartmentActivity.class));
            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }



}
