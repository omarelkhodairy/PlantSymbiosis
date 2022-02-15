package com.example.symbio.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.symbio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertPlantFragment extends Fragment {

    View view;
    Button insertPlantButton;
    EditText plantName, favourableLight, soilType, wateringCondition, maximumProduction, descriptionPlant;
    Plant plantObj= new Plant();



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_insert_plant, container, false);
        insertPlantButton = view.findViewById(R.id.insertPlantButton);
        plantName = view.findViewById(R.id.plantName);
        favourableLight = view.findViewById(R.id.favourableLight);
        soilType = view.findViewById(R.id.soilType);
        wateringCondition = view.findViewById(R.id.wateringCondition);
        maximumProduction = view.findViewById(R.id.maximumProduction);
        descriptionPlant = view.findViewById(R.id.descriptionPlant);



        insertPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantObj.setName(plantName.getText().toString().toLowerCase());
                plantObj.setFavourableLight(favourableLight.getText().toString().toLowerCase());
                plantObj.setSoilType(soilType.getText().toString().toLowerCase());
                plantObj.setWateringCondition(wateringCondition.getText().toString().toLowerCase());
                plantObj.setMaxProduction(maximumProduction.getText().toString().toLowerCase());
                plantObj.setDescription(descriptionPlant.getText().toString().toLowerCase());
                FirebaseDatabase databaseinstance = FirebaseDatabase.getInstance();

                DatabaseReference userNode = databaseinstance.getReference("plantApp");

                userNode.child("plants").child(plantObj.getName()).setValue(plantObj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Plant Added Successfully", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        return view;
    }
}