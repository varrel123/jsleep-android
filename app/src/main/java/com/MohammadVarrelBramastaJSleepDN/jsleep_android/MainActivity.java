package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            ArrayList<String> data = new ArrayList<>();
            String test = null;

        try{
            InputStream IS = getAssets().open("randomRoomList.json");
            int size = IS.available();
            byte[] Data1 = new byte[size];


            IS.read(Data1);

            IS.close();


            test = new String(Data1,"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        Type listRoomType = new TypeToken<ArrayList<Room>>() { }.getType();

        ArrayList<Room> roomList = new Gson().fromJson(test, listRoomType);



        for (Room room: roomList){data.add(room.name);}




        ListView listView = findViewById(R.id.ListVieww);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return true;
    }











     @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.search_button:
                Intent aboutMeIntent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(aboutMeIntent);
                return true;



                default:
                return super.onOptionsItemSelected(item);
        }
    }
}