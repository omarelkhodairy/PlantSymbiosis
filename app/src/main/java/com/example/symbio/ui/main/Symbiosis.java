package com.example.symbio.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.symbio.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Symbiosis {




    String[] TypesOfRelationship = {"Commensalism", "Parasistism", "Mutualism", "Ectosymbiosis"};

    public String getTypeOfRelationship() {
        return TypeOfRelationship;
    }

    public void setTypeOfRelationship(String typeOfRelationship) {
        TypeOfRelationship = typeOfRelationship;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String TypeOfRelationship;



    String description;



    String plant1;

    public String getPlant1() {
        return plant1;
    }

    public void setPlant1(String plant1) {
        this.plant1 = plant1;
    }

    public String getPlant2() {
        return plant2;
    }

    public void setPlant2(String plant2) {
        this.plant2 = plant2;
    }

    String plant2;



}

