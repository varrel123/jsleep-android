package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        Account account = MainActivity.accountObject;

        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView balance = findViewById(R.id.balance);

        name.setText(account.name);
        email.setText(account.email);
        balance.setText(Double.toString(account.balance));
    }
}