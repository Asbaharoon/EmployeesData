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
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername,loginPassword;

    Button login;

    boolean loginData=false;

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsername=findViewById(R.id.login_username_et);
        loginPassword=findViewById(R.id.login_password_et);
        login = findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginCheck(path+"employees.txt");

                if( (loginUsername.getText().toString().equals("Admin") && loginPassword.getText().toString().equals("Admin")) || loginData )
                {
                    Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);

                    String userName = loginUsername.getText().toString();

                    Bundle b = new Bundle();
                    b.putString("userName",userName);
                    intent.putExtras(b);

                    startActivity(intent);

                   loginUsername.setText("");
                   loginPassword.setText("");
                }

                else
                {
                    Toast.makeText(LoginActivity.this, "ERROR : WRONG LOGIN DATA", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private void loginCheck(String fileName)
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
                String[] parts=line.split("-");
                String userNameFile=parts[1];
                String passwordFile=parts[3];

                if(userNameFile.equals(loginUsername.getText().toString()) && passwordFile.equals(loginPassword.getText().toString()))
                {
                    loginData=true;

                    return;
                }


                line = bufferedReader.readLine();
                loginData=false;
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
