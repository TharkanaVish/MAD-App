package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    DBHelper myDb;
    EditText fullname, phoneno, email, address, username, password, repassword;
    Button register,login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DBHelper(this);

        fullname = (EditText) findViewById(R.id.fullname);
        phoneno = (EditText) findViewById(R.id.phoneNumber);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.log);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(register.this,Login.class);
                        startActivity(i);
                    }
                }
        );

        AddAppData();

    }
    public void AddAppData() {
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String empty = "";
                        String cfname = fullname.getText().toString();
                        String cpnum = phoneno.getText().toString();
                        String cemail = email.getText().toString();
                        String caddress = address.getText().toString();
                        String cusr = username.getText().toString();
                        String cpwd = password.getText().toString();
                        String crpwd = repassword.getText().toString();
                        // check if any of the fields are vaccant
                        if(cfname.matches("")||cpnum.matches("")||cemail.matches("")||caddress.matches("")||cusr.matches("")||cpwd.matches("")||crpwd.matches(""))
                        {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else if (cpwd.equals(crpwd)) {
                            // check if both password matches
                            Intent m = new Intent(view.getContext(),Registerview.class);
                            m.putExtra("fullname",fullname.getText().toString());
                            m.putExtra("phoneno",phoneno.getText().toString());
                            m.putExtra("email",email.getText().toString());
                            m.putExtra("address",address.getText().toString());
                            m.putExtra("username",username.getText().toString());
                            m.putExtra("Password",password.getText().toString());
                            startActivity(m);
                            // Save the Data in Database
                            boolean isInserted = myDb.insertRegisterData(fullname.getText().toString(), phoneno.getText().toString(), email.getText().toString(), address.getText().toString(), username.getText().toString(), password.getText().toString(), repassword.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(register.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(register.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                            return;

                        }

                    }
                });

    }

}