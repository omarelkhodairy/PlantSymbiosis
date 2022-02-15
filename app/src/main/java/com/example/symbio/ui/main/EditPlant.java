package com.example.symbio.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.symbio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditPlant extends AppCompatActivity {
    FirebaseDatabase databaseinstance = FirebaseDatabase.getInstance();

    DatabaseReference userNode = databaseinstance.getReference("plantApp").child("plants");
    private EditText plantName;
    private EditText favourableLight;
    private EditText soilType;
    private EditText wateringCondition;
    private EditText maxProduction;
    private EditText description;

    String key, plant, favLight, soil, watering, max, des;

    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant);
        key = getIntent().getStringExtra("key");
        plant = getIntent().getStringExtra("name");
        favLight = getIntent().getStringExtra("favourableLight");
        soil = getIntent().getStringExtra("soilType");
        watering = getIntent().getStringExtra("wateringCondition");
        max = getIntent().getStringExtra("maxProduction");
        des = getIntent().getStringExtra("description");


        plantName = findViewById(R.id.plantName1);
        favourableLight = findViewById(R.id.favourableLight);
        soilType = findViewById(R.id.soilType);
        wateringCondition = findViewById(R.id.wateringCondition);
        maxProduction = findViewById(R.id.maximumProduction);
        description = findViewById(R.id.description);

        plantName.setText(plant);
        favourableLight.setText(favLight);
        soilType.setText(soil);
        wateringCondition.setText(watering);
        maxProduction.setText(max);
        description.setText(des);

        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plant plant = new Plant();
                plant.setName(plantName.getText().toString());
                plant.setFavourableLight(favourableLight.getText().toString());
                plant.setSoilType(soilType.getText().toString());
                plant.setWateringCondition(wateringCondition.getText().toString());
                plant.setMaxProduction(maxProduction.getText().toString());
                plant.setDescription(description.getText().toString());

                userNode.child(plant.getName()).setValue(plant).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditPlant.this, "Plant Updated Successfully ", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userNode.child(plant).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditPlant.this, "Plant Deleted Successfully ", Toast.LENGTH_LONG).show();
                        finish(); return;

                    }
                });


            }
        });

    }
}