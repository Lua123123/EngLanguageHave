package com.example.englanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englanguage.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    private Context context = SignUpActivity.this;
    private String email;
    private String password;
    private String conformPassword;
    private String name;
    private EditText edt_name, edt_email, edt_password, edt_conformPassword;
    private Button btn_postSignUp;
    private TextView tv_haveAnAccount;
    private CheckBox checkBoxLogin;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_conformPassword = findViewById(R.id.edt_conformPassword);
        checkBoxLogin = findViewById(R.id.checkBoxSignUp);
        signUpViewModel = new SignUpViewModel(context);

        tv_haveAnAccount = findViewById(R.id.tv_haveAnAccount);
        tv_haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        btn_postSignUp = findViewById(R.id.btn_postSignUp);
        btn_postSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email.getText().toString().trim();
                password = edt_password.getText().toString().trim();
                conformPassword = edt_conformPassword.getText().toString().trim();
                name = edt_name.getText().toString().trim();

                if (checkBoxLogin.isChecked()) {
//                    if (email != null && password != null && conformPassword != null && name != null
//                            && !email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !conformPassword.isEmpty()) {
                        signUpViewModel.clickSignUp(email, password, name, conformPassword);
//                    } else {
//                        Toast toast = Toast.makeText(context, "NAME, EMAIL, PASSWORD AND CONFORM PASSWORD IS EMPTY", Toast.LENGTH_SHORT);
//                        signUpViewModel.customToast(toast);
                    }
//                }
                else {
                    Toast toast = Toast.makeText(context, "Please agree to the terms of the app!!!", Toast.LENGTH_LONG);
                    signUpViewModel.customToast(toast);
                }
            }
        });
    }
}