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

public class RegisterActivity extends AppCompatActivity {

    TextView tvHaveAccount;
    EditText edtRegUserName;
    TextInputLayout edtRegPassword,edtRegPassConfirm;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegUserName = findViewById(R.id.reg_UserAccount);
        edtRegPassword = findViewById(R.id.reg_Password);
        edtRegPassConfirm = findViewById(R.id.reg_PasswordConfirm);

        btnRegister = findViewById(R.id.btn_Register);
        tvHaveAccount = findViewById(R.id.tv_HaveAccount);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtRegUserName.getText().toString();
                String pass = edtRegPassword.getEditText().getText().toString();
                String passConfirm = edtRegPassConfirm.getEditText().getText().toString();

                if(pass.compareToIgnoreCase(passConfirm) == 0){
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("name",username);
                    intent.putExtra("pass",pass);
                    setResult(101,intent);
                    finish();
                    Toast.makeText(RegisterActivity.this, "Registered !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
