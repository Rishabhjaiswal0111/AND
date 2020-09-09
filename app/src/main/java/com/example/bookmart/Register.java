package com.example.bookmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent=getIntent();
    }
    public void add_entery(View view){
        EditText f_name=findViewById(R.id.F_name);
        EditText l_name=findViewById(R.id.L_name);
        EditText email=findViewById(R.id.Email2);
        EditText password=findViewById(R.id.password2);
        DatabaseHelper db=new DatabaseHelper(this);

        boolean b1 = db.check_register(email.getText().toString());
        Toast.makeText(this,""+b1,Toast.LENGTH_SHORT).show();
        if(b1)
        {
            Toast.makeText(this,""+"Email is already registered",Toast.LENGTH_SHORT).show();
        }

        else{
            boolean b = db.add_new(f_name.getText().toString(), l_name.getText().toString(), email.getText().toString(), password.getText().toString());
            Toast.makeText(this,""+"Registered Successfully",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}