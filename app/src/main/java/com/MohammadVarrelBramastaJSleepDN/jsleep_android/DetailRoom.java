package com.MohammadVarrelBramastaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Room;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Facility;

public class DetailRoom extends AppCompatActivity {
    Room iterRoom = MainActivity.roomSelected();
    Room rooms;
    CheckBox CekAC, CekRefrigerator, CekWifi, CekBathub, CekBalcony, CekRestaurant, CekSwimPool, CekFitCen;
    TextView Name, BedType, Size, Price, Address;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        rooms = MainActivity.roomList.get(MainActivity.roomsListViewSelected);

        CekBathub = findViewById(R.id.CekBoxBathub);
        CekBalcony = findViewById(R.id.CekBoxBalcony);
        CekRestaurant = findViewById(R.id.CekBoxRestaurant);
        CekSwimPool = findViewById(R.id.CekBoxSwimPool);
        CekFitCen = findViewById(R.id.CekBoxFitCenter);
        CekAC = findViewById(R.id.CekBoxAC);
        CekRefrigerator = findViewById(R.id.CekBoxRefrigerator);
        CekWifi = findViewById(R.id.CekBoxWifi);

        Size = findViewById(R.id.infoSizeDetailRoom);
        Size.setText("" + iterRoom.size);
        Price = findViewById(R.id.infoPriceDetailRoom);
        Price.setText("" + iterRoom.price);
        Address = findViewById(R.id.infoAddressDetailRoom);
        Address.setText("" + iterRoom.address);
        Name = findViewById(R.id.infoNameDetailRoom);
        Name.setText((CharSequence) iterRoom.name);
        BedType = findViewById(R.id.infoBedTypeDetailRoom);
        BedType.setText("" + iterRoom.bedType);

        if(rooms.facility.equals(Facility.AC))
                CekAC.setChecked(true);
        if(rooms.facility.equals(Facility.Refrigerator))
                CekRefrigerator.setChecked(true);
        if(rooms.facility.equals(Facility.WiFi))
                CekWifi.setChecked(true);
        if(rooms.facility.equals(Facility.Bathub))
                CekBathub.setChecked(true);
        if(rooms.facility.equals(Facility.Balcony))
                CekBalcony.setChecked(true);
        if(rooms.facility.equals(Facility.Restaurant))
                CekRestaurant.setChecked(true);
        if(rooms.facility.equals(Facility.SwimmingPool))
                CekSwimPool.setChecked(true);
        if(rooms.facility.equals(Facility.FitnessCenter))
                CekFitCen.setChecked(true);
    }
}