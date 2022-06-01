package com.example.englanguage.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.englanguage.LoginActivity;
import com.example.englanguage.R;
import com.example.englanguage.model.signup.SignUp;
import com.example.englanguage.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseObservable {
    private Context context;


    public SignUpViewModel(Context context) {
        this.context = context;
    }

    public void clickSignUp(String email, String password, String name, String conformPassword) {

        API.api.postUsers(email, password, name, conformPassword).enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                SignUp signUp = response.body();
                Toast toast = Toast.makeText(context, "SIGN UP SUCCESSFULLY", Toast.LENGTH_SHORT);
                customToast(toast);
                Intent intent = new Intent(context, LoginActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("email", email);
//                bundle.putString("password", password);
//                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast toast = Toast.makeText(context, "Please check your Internet connection!!!", Toast.LENGTH_SHORT);
                customToast(toast);
            }
        });
    }

    public void customToast(Toast toast) {
        View toastView = toast.getView();
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(13);
        toastMessage.setTextColor(Color.YELLOW);
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setCompoundDrawablePadding(4);
        toastView.setBackgroundColor(Color.BLACK);
        toastView.setBackgroundResource(R.drawable.bg_toast);
        toast.show();
    }
}
