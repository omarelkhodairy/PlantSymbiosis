package com.example.symbio.ui.main;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
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
    ArrayAdapter<String> adapter, adapter1, adapter2, ok;
    Symbiosis s = new Symbiosis();
    String s1;
    int value1, value2, value3;
    private bfs graph;
    ArrayList<String> spinnerArray; //changed
    private ArrayList<Integer> suggestion;
    private ArrayList<String> suggestionNames;
    private ArrayList<String> plants;
    private DatabaseReference myDB;

    int getIndex(List<String> plants, String node){
        for(int i=0; i < plants.size(); i++){
            if (node.equals(plants.get(i)))
                return i;
        }
        return 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


        view = inflater.inflate(R.layout.fragment_insert_symbiosis, container, false);


        spinnerArray = new ArrayList<String>(); // changed
        suggestion = new ArrayList<Integer>();
        plants= new ArrayList<String>();
        suggestionNames= new ArrayList<String>();
        graph= new bfs(0, plants.size(), plants.size());


        myDB= FirebaseDatabase.getInstance().getReference("plantApp").child("plants");
        myDB.addValueEventListener(new ValueEventListener() {
            @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                plants.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    String plant = dataSnapshot1.getKey();
                    plants.add(plant);
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("plantApp").child("symbiosis");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                graph= new bfs(0, plants.size(), plants.size());
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
                        Symbiosis s=dataSnapshot2.getValue(Symbiosis.class);
                        if(s.getTypeOfRelationship().equals("mutualism")){
                            graph.addEdge( getIndex(plants ,s.getPlant1()) , getIndex(plants ,s.getPlant2()) );
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        ///////////////////////////////////////////////////////////////// old
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("plantApp").child("plants");
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                spinnerArray.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
                graph.adj.add(new ArrayList<Integer>());
                graph.setVertices(graph.getVertices()+1);
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


        ok = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,suggestionNames );

        insertSymbiosisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setPlant1(plantName1.toLowerCase());
                s.setPlant2(plantName2.toLowerCase());
                s.setTypeOfRelationship(typeOfRelation.toLowerCase());
                s.setDescription(descriptionSymbiosis1.getText().toString().toLowerCase());



                FirebaseDatabase databaseinstance = FirebaseDatabase.getInstance();

                DatabaseReference userNode = databaseinstance.getReference("plantApp");

                userNode.child("symbiosis").child(s.getPlant1()).child(s.getPlant2()).setValue(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Symbiosis Added Successfully", Toast.LENGTH_LONG);


                        if( s.getTypeOfRelationship().equals("mutualism")){
                            graph.addEdge( getIndex(plants ,s.getPlant1()) , getIndex(plants ,s.getPlant2()) );
                        }
                        else{
                            graph.makePath(getIndex(plants, s.getPlant1()), getIndex(plants,s.getPlant2()));
                            suggestion.clear();
                            suggestion = graph.getPath();
                            suggestionNames.clear();
                            ok.clear();
                            /*String o =Integer.toString(suggestion.size());
                            suggestionNames.add( o);*/
                            ok.notifyDataSetChanged();
                            for (int i = 0; i < suggestion.size() ; i++) {
                                if(i ==0)
                                    suggestionNames.add("Plants Order for Better Production:");
                                String s=plants.get(graph.getPath().get(i));
                                suggestionNames.add(s);
                                ok.notifyDataSetChanged();
                            }

                        }
                    }



                });
                //userNode.child("symbiosis").child(s.getPlant1()).setValue(s);
                ////////// new




            }
        });


        ok.notifyDataSetChanged();
        ListView lvlistview = (ListView) view.findViewById(R.id.lvlistview);
        lvlistview.setAdapter(ok);

        return view;
    }
}