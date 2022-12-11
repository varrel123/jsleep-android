package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Room;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.BaseApiService;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.request.UtilsApi;
import com.google.gson.Gson;
import java.util.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    public static EditText username;
    public static EditText password;
    public static Account accountObject;
    BaseApiService mApiService;
    Context mContext;
    public static int roomsListViewSelected;
    static ArrayList<Room> roomList = new ArrayList<Room>();
    int pageInc = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mApiService = UtilsApi.getApiService();
            mContext = this;

            ArrayList<String> data = new ArrayList<>();
            String test = null;

            ListView listView = findViewById(R.id.ListVieww);
            Button next = findViewById(R.id.buttonNextProduct);
            next.setOnClickListener(view ->  {
                pageInc = pageInc + 1;
            });

            Button Prev = findViewById(R.id.buttonPrevProduct);
            Prev.setOnClickListener(view ->  {
                pageInc = pageInc - 1;
            });

            Button GO = findViewById(R.id.buttonGoProduct);
            GO.setOnClickListener(view ->  {
                requestAllRoom(pageInc,10);
                ArrayAdapter<Room> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);
                listView.setAdapter(adapter);
            });

            requestAllRoom(pageInc,10);

            ArrayAdapter<Room> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);

            listView.setAdapter(adapter);

        }

        public static Room roomSelected(){
            return roomList.get(roomsListViewSelected);
        }

        public boolean onPrepareOptionsMenu(Menu menu) {
            MenuItem Button = menu.findItem(R.id.search_button3);
            if(accountObject.renter == null) {
                Button.setVisible(false);
            }
            else{
                Button.setVisible(true);
            }
            return true;
        }

        protected List<Room> requestAllRoom(int page,  int pageSize){
            mApiService.getAllRoom(page,pageSize).enqueue(new Callback<List<Room>>() {
                @Override
                public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                    if (response.isSuccessful()) {
                        List<Room> room;
                        room = response.body();
                        for (Room room1: room){
                            roomList.add(room1.name);
                        }
                        System.out.println(room.toString());
                        Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                    Toast.makeText(mContext, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                    System.out.println(t);
                }
            });
            return null;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);

        if(accountObject.renter == null)
            menu.findItem(R.id.search_button3).setVisible(false);
        else
            menu.findItem(R.id.search_button3).setVisible(true);
        return(true);
    }

     @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.search_button:
                Intent aboutMeIntent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(aboutMeIntent);
                return true;

                case R.id.search_button3:
                    Intent intent = new Intent(this, CreateRoomActivity.class);
                    startActivity(intent);
                    return true;

                default:
                return super.onOptionsItemSelected(item);
        }
    }
}