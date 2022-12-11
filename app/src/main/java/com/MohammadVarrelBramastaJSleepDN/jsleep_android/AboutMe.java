package com.MohammadVarrelBramastaJSleepDN.jsleep_android;


import static com.MohammadVarrelBramastaJSleepDN.jsleep_android.MainActivity.accountObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Renter;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.BaseApiService;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.UtilsApi;

public class AboutMe extends AppCompatActivity {

    BaseApiService mApiService;
    Context mContext;
    EditText RegisterRenterName, RegisterRenterPhone, RegisterRenterAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        Account account = accountObject;

        mApiService = UtilsApi.getApiService();
        mContext = this;

        TextView username = findViewById(R.id.Name);
        TextView email = findViewById(R.id.Email);
        TextView balance = findViewById(R.id.Balance);

        username.setText(account.name);
        email.setText(account.email);
        balance.setText(String.valueOf(account.balance));
        Button RegisterRenterButton = findViewById(R.id.ButtonRegisterRenter);

        Button RegisterRenter = findViewById(R.id.RegisterRenter);
        CardView registerRenterCard = findViewById(R.id.RegisterRenterCardView);
        RegisterRenterName = findViewById(R.id.RegisterRenterName);
        RegisterRenterAddress = findViewById(R.id.RegisterRenterAddress);
        RegisterRenterPhone = findViewById(R.id.RegisterPhoneNumber);

        Button CancelRenterButton = findViewById(R.id.ButtonCancelRenter);
        TextView RenterNameText = findViewById(R.id.RenterNameText);
        TextView RenterAddressText = findViewById(R.id.RenterAddressText);
        TextView RenterPhoneText = findViewById(R.id.RenterPhoneNumberText);
        TextView RenterNameFill = findViewById(R.id.RenterNameFill);
        TextView RenterAddressFill = findViewById(R.id.RenterAddressFill);
        TextView RenterPhoneFill = findViewById(R.id.RenterPhoneNumberFill);

        if (account.renter == null) {
            RegisterRenter.setVisibility(View.VISIBLE);
            registerRenterCard.setVisibility(View.INVISIBLE);
        } else {
            RegisterRenter.setVisibility(View.INVISIBLE);
            registerRenterCard.setVisibility(View.VISIBLE);
            RegisterRenterName.setVisibility(View.INVISIBLE);
            RegisterRenterAddress.setVisibility(View.INVISIBLE);
            RegisterRenterPhone.setVisibility(View.INVISIBLE);
            RegisterRenterButton.setVisibility(View.INVISIBLE);
            CancelRenterButton.setVisibility(View.INVISIBLE);
            RenterNameFill.setText(account.renter.username);
            RenterAddressFill.setText(account.renter.address);
            RenterPhoneFill.setText(account.renter.phoneNumber);

        }

        RegisterRenter.setOnClickListener(v -> {
            RegisterRenter.setVisibility(View.INVISIBLE);
            registerRenterCard.setVisibility(View.VISIBLE);
            RenterNameText.setVisibility(View.INVISIBLE);
            RenterAddressText.setVisibility(View.INVISIBLE);
            RenterPhoneText.setVisibility(View.INVISIBLE);
            RenterNameFill.setVisibility(View.INVISIBLE);
            RenterAddressFill.setVisibility(View.INVISIBLE);
            RenterPhoneFill.setVisibility(View.INVISIBLE);
        });

        CancelRenterButton.setOnClickListener(v -> {
            RenterNameText.setText("");
            RenterAddressText.setText("");
            RenterPhoneText.setText("");
            registerRenterCard.setVisibility(View.INVISIBLE);
            RegisterRenter.setVisibility(View.VISIBLE);
        });

        RegisterRenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Renter renter = requestRegisterRenter();
            }
        });

        EditText TopUp = findViewById(R.id.AmountFill);
        Button TopUpButton = findViewById(R.id.TopUpButton);
        TopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(TopUp.getText().toString());
                TopUp(amount);
            }
        });
    }

    /**
     * Method to request register renter
     * @return Renter
     */
    protected Renter requestRegisterRenter() {
        System.out.println(accountObject.id);
        System.out.println(RegisterRenterName.getText().toString());
        System.out.println(RegisterRenterAddress.getText().toString());
        System.out.println(RegisterRenterPhone.getText().toString());
        mApiService.registerRenter(accountObject.id, RegisterRenterName.getText().toString(), RegisterRenterAddress.getText().toString(), RegisterRenterPhone.getText().toString()).enqueue(new Callback<Renter>() {
            /**
             * method untuk menginisialisasi register renter
             * @param call call
             * @param response response
             */
            @Override
            public void onResponse(@NonNull Call<Renter> call, @NonNull Response<Renter> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Register Renter Success", Toast.LENGTH_SHORT).show();
                    accountObject.renter = response.body();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);

                } else {
                    System.out.println(response.errorBody());
                    Toast.makeText(mContext, "Register Renter Failed", Toast.LENGTH_SHORT).show();

                }
            }

            /**
             * method untuk menginisialisasi register renter
             * @param call call
             * @param t t
             */
            @Override
            public void onFailure(@NonNull Call<Renter> call, @NonNull Throwable t) {
                System.out.println(t);
                Toast.makeText(mContext, "Register Renter Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    /**
     * This method is called when the top up button is clicked.
     */
    public void TopUp(int amount) {
        mApiService.topUp(accountObject.id, amount).enqueue(new Callback<Account>() {
            @Override
            /**
             * This method is called when the request is successful
             */
            public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                if (response.isSuccessful()) {
                    Account account = response.body();
                    accountObject.balance = account.balance;
                    Toast.makeText(mContext, "Top Up Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AboutMe.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(mContext, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(mContext, "Fail to Top Up" , Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            /**
             * This method is called when the request fails
             */
            public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                Toast.makeText(mContext, "Fail to Top Up", Toast.LENGTH_SHORT).show();
            }
        });
    }

}