package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.BedType;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.City;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Facility;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Room;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.BaseApiService;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    ArrayList<Facility> facilityArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        Spinner bedTypeCreateRoom =  findViewById(R.id.spinnerShipmentPlan);
        Spinner cityCreateRoom = findViewById(R.id.spinnerCategory);
        EditText nameCreate = findViewById(R.id.NameRoom);
        EditText sizeCreate = findViewById(R.id.SizeRoom);
        EditText priceCreate = findViewById(R.id.PriceRoom);
        EditText addressCreate = findViewById(R.id.AddressRoom);

        bedTypeCreateRoom.setAdapter(new ArrayAdapter<BedType>(this,android.R.layout.simple_spinner_item, BedType.values()));
        cityCreateRoom.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        final City[] cities = new City[1];
        final BedType[] bedTypes = new BedType[1];

        TextView create = findViewById(R.id.createButton);
        TextView cancel = findViewById(R.id.cancelButton);

        bedTypeCreateRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                bedTypes[0] = (BedType) bedTypeCreateRoom.getAdapter().getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cityCreateRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                cities[0] = (City) cityCreateRoom.getAdapter().getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Facility> facility = facilityArrayList;
                List<Room> room = requestRoom(MainActivity.accountObject, nameCreate.getText().toString(),
                        Integer.parseInt(sizeCreate.getText().toString()),
                        Double.parseDouble(priceCreate.getText().toString()), bedTypes[0], facility, cities[0], addressCreate.getText().toString());
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                startActivity(move);
            }
        });
    }

    protected List<Room> requestRoom(Account Accountid, String name, int size, double price, BedType bedType, ArrayList<Facility> facility, City city, String address) {
        mApiService.create(Accountid, name, size, price, facility, city, address, bedType).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(@NonNull Call<List<Room>> call,@NonNull Response<List<Room>> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(mContext, "Romm sukses dibuat", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Room>> call,@NonNull Throwable t) {
                Toast.makeText(mContext, "Romm failed dibuat", Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });
        return null;
    }

    public void CheckBox(View view){
        CheckBox ACCreateRoom = findViewById(R.id.cbAC);
        CheckBox RefCreateRoom = findViewById(R.id.cbRefrigerator);
        CheckBox SwimCreateRoom = findViewById(R.id.cbWifi);
        CheckBox BathubCreateRoom = findViewById(R.id.cbBathub);
        CheckBox BalconyCreateRoom = findViewById(R.id.cbBalcony);
        CheckBox FitnessCreateRoom = findViewById(R.id.cbFitness);
        CheckBox restaurantCreateRoom = findViewById(R.id.cbRestaurant);
        CheckBox wifiCreateRoom = findViewById(R.id.cbWifi);
        if(ACCreateRoom.isChecked()){
            facilityArrayList.add(Facility.AC);
        } else
            facilityArrayList.remove(Facility.AC);

        if(RefCreateRoom.isChecked()){
            facilityArrayList.add(Facility.Refrigerator);
        } else
            facilityArrayList.remove(Facility.Refrigerator);

        if(SwimCreateRoom.isChecked()){
            facilityArrayList.add(Facility.SwimmingPool);
        } else
            facilityArrayList.remove(Facility.SwimmingPool);

        if(BathubCreateRoom.isChecked()){
            facilityArrayList.add(Facility.Bathub);
        }else
            facilityArrayList.remove(Facility.Bathub);

        if(BalconyCreateRoom.isChecked()){
            facilityArrayList.add(Facility.Balcony);
        }else
            facilityArrayList.remove(Facility.Balcony);

        if(FitnessCreateRoom.isChecked()){
            facilityArrayList.add(Facility.FitnessCenter);
        }else
            facilityArrayList.remove(Facility.FitnessCenter);

        if(restaurantCreateRoom.isChecked()){
            facilityArrayList.add(Facility.Restaurant);
        }else
            facilityArrayList.remove(Facility.Restaurant);

        if(wifiCreateRoom.isChecked()){
            facilityArrayList.add(Facility.WiFi);
        }else
            facilityArrayList.remove(Facility.WiFi);

    }
}