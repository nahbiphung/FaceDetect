package com.example.admin.facedetect;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    TextView tvRegistry,tvForgotPassword;
    EditText edtUsername;
    TextInputLayout edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.btn_Login);
        tvRegistry = findViewById(R.id.tv_Registry);
        tvForgotPassword = findViewById(R.id.tv_ForgotPassword);
        edtUsername = findViewById(R.id.edt_UserAccount);
        edtPass = findViewById(R.id.edt_Password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Comming soon...",Toast.LENGTH_SHORT).show();
            }
        });

        tvRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 101){
            edtUsername.setText(data.getStringExtra("name"));
            edtPass.getEditText().setText(data.getStringExtra("pass"));
        }
    }
}
