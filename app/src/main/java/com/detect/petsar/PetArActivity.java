package com.detect.petsar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.detect.petsar.env.Logger;
import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

public class PetArActivity extends AppCompatActivity {

    private static final int WIKITUDE_PERMISSIONS_REQUEST_CAMERA = 1;
    private static final Logger LOGGER = new Logger();
    private ArchitectView architectView;
    private String pet_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_ar);

        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, WIKITUDE_PERMISSIONS_REQUEST_CAMERA);
        }

        Intent intent = getIntent();
        try {
            pet_type = intent.getExtras().getString("pet_type");
            LOGGER.i("pet type : %s", pet_type);
        } catch (final Exception e) {
            LOGGER.e(e, "Couldn't fonund type and kind of pet");
        }

        this.architectView = (ArchitectView)this.findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("FhaGHCFvzwWr0WafQvXRRFMHhxPnfxEH6hG9ZGh1bIsJd7FKv+upoCdtXN/ENChF+wQtN+jKyvn9sA6AjHBPJ4Cb+aHVW9b4Do1sRYZfhS19NUNoGTdlLnlESQYE3tS/a62kL111r4ZVWGr04vWKz7t+cHuAvxAsBYUmYBR3Ra1TYWx0ZWRfX/ZVvf3gaEmcoUpsj47Pxy7Ooqd4qxTk5PkAVa2cyMNiacqDW2K79yJmXEHHPWgi7BitzDTC5cThUZ53drPLpc0YiXpykZFDlAIpf4dtxzPKvvhqAVbnG6YfAljMF9lq3+UnIEpeHlxNzrln9xtiTw/T53+pLiETn6GkxKmsEvb36HpXI7V6HFw1e36z1T7WuaZp0Mnp4P+IY4NaXiXPhwMK5po0Aqdon3H/vL7FoMt3Dcl5Tzjyl8RTLi2Y5ep74g45SioLyNaiYSklJT7EUUboRyNFNTAshgMBuXSY7bu00n/Jmi5F6a5VlAvMAp5MZTKsjaI0um2uWrNb6cMsDcdCDb29JaxfMgSJT1+F7dDvKdPeYNumllp9g0bkCZxClB5GgYBb9bW2FeDtRNAtIJLxpAGmitvO0889CTNgF+1chER7Ap8tu8FSbouscYSx/TZtbeo1+BFPio63WIOLN1XFF7+o3QZCbo2cY2jKn/e32OLJHaWXfbJgkOZEkK0uk10grwkgmznLaanzC74sYBmtCCO5XDt4WchGUx2scSUvkKgvrM8ieuXsn/Xw9QAig9KTyk6zy+QDprtpLDOjTbqCz9CdWWvVJx1UuF0U4qxGrnjlUGgdgTiXu5QWSAETg/PQsYkSc58mqPxJ6e0+OFcTMUzb/9LuA6LcOMj/iLHZumgkYb8UX2A=");
        this.architectView.onCreate(config);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.architectView.onPostCreate();
        try{
            if(pet_type.equals("dog")){
                this.architectView.load( "samples/05_InstantTracking_3_Interactivity/index.html" );
            }
            else{
                this.architectView.load( "samples/05_InstantTracking_3_Interactivity/index_cat.html" );
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        architectView.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        architectView.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        architectView.onPause();
    }
}