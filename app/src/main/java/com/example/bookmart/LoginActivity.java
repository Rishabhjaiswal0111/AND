package com.example.bookmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }

    public void change_register(View view){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }

    public void login_success(View view) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        DatabaseHelper db = new DatabaseHelper(this);
        boolean b = db.check_login(email.getText().toString(), password.getText().toString());

        if (b) {
            Toast.makeText(this, "" + "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddBook.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "" + "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }
    }
}