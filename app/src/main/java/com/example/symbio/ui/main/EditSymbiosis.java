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

import java.util.List;

public class EditSymbiosis extends AppCompatActivity {

    FirebaseDatabase databaseinstance = FirebaseDatabase.getInstance();

    DatabaseReference userNode = databaseinstance.getReference("plantApp");
    private EditText plantName1;
    private EditText plantName2;
    private EditText typeOfRelationship;
    private EditText description;

    String key, plant1, plant2, typeOfRelationship1, description1;

    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_symbiosis);
        key = getIntent().getStringExtra("key");
        plant1 = getIntent().getStringExtra("plant1");
        plant2 = getIntent().getStringExtra("plant2");
        typeOfRelationship1 = getIntent().getStringExtra("typeOfRelationship");
        description1 = getIntent().getStringExtra("description");



        plantName1 = findViewById(R.id.plantName1);
        plantName2 = findViewById(R.id.plantName2);
        typeOfRelationship = findViewById(R.id.TypeOfRelationship);
        description = findViewById(R.id.descriptionSymbiosis);

        plantName1.setText(plant1);
        plantName2.setText(plant2);
        typeOfRelationship.setText(typeOfRelationship1);
        description.setText(description1);

        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Symbiosis symbiosis = new Symbiosis();
                symbiosis.setPlant1(plantName1.getText().toString());
                symbiosis.setPlant2(plantName2.getText().toString());
                symbiosis.setTypeOfRelationship(typeOfRelationship.getText().toString());
                symbiosis.setDescription(description.getText().toString());

                if(!plant1.equals(symbiosis.getPlant1()) || !plant2.equals(symbiosis.getPlant2())){
                    userNode.child("symbiosis").child(plant1).child(plant2).setValue(null);
                }

                userNode.child("symbiosis").child(symbiosis.getPlant1()).child(symbiosis.getPlant2()).setValue(symbiosis).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditSymbiosis.this, "Symbiosis Updated Successfully ", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userNode.child("symbiosis").child(plant1).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditSymbiosis.this, "Symbiosis record deleted ", Toast.LENGTH_LONG).show();
                        finish(); return;

                    }
                });


            }
        });


    }
}