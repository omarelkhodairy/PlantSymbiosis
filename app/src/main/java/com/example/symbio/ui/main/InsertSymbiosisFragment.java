package com.example.symbio.ui.main;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.symbio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
public class InsertSymbiosisFragment extends Fragment {
    View view;
    Button insertSymbiosisButton;
    String plantName1;
    String plantName2;
    String typeOfRelation;
    EditText descriptionSymbiosis1;
    EditText plant1;
    EditText plant2;
    Spinner spinner, spinner1, spinner2;
    ArrayAdapter<String> adapter, adapter1, adapter2;
    Symbiosis s = new Symbiosis();
    String s1;
    int value1, value2, value3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_insert_symbiosis, container, false);



        final List<String> spinnerArray = new ArrayList<String>();


        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("plantApp").child("plants");
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                spinnerArray.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        spinner = (Spinner) view.findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);


        String relationship[] = s.TypesOfRelationship;
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, relationship);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);



        insertSymbiosisButton = view.findViewById(R.id.insertSymbiosisButton);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                value1 = position;
                plantName1 = parent.getSelectedItem().toString()
                        .trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                value2 = position;
                plantName2 = parent.getSelectedItem().toString()
                        .trim();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                value3 = position;
                typeOfRelation = parent.getSelectedItem().toString()
                        .trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        descriptionSymbiosis1 = view.findViewById(R.id.descriptionSymbiosis);




       insertSymbiosisButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                s.setPlant1(plantName1.toLowerCase());
                s.setPlant2(plantName2.toLowerCase());
                s.setTypeOfRelationship(typeOfRelation.toLowerCase());
                s.setDescription(descriptionSymbiosis1.getText().toString().toLowerCase());



                FirebaseDatabase databaseinstance = FirebaseDatabase.getInstance();

                DatabaseReference userNode = databaseinstance.getReference("plantApp");
              //  userNode.child("symbiosis").child(s.getPlant1()).child("plantOne").setValue(s.getPlant1());

               // userNode.child("symbiosis").child(s.getPlant1()).child("plantTwo").setValue(s.getPlant2());
               userNode.child("symbiosis").child(s.getPlant1()).child(s.getPlant2()).setValue(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       Toast.makeText(getActivity(),"Symbiosis Added Successfully", Toast.LENGTH_LONG).show();
                   }
               });



            }
        });
        return view;
    }
}