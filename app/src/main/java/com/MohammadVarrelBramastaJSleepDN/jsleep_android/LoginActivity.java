package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.BaseApiService;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    BaseApiService mApiService;
    EditText username,password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView Register = findViewById(R.id.registerNow);
        username = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });


        Button Login = findViewById(R.id.buttonLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestAccount();
                Intent move = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(move);
            }
        });
    }
    protected Account requestAccount(){
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext,"no Account id=0",Toast.LENGTH_SHORT).show();

            }
        });
        return null;
    }

}