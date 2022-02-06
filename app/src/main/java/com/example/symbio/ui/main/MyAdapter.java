package com.example.symbio.ui.main;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.symbio.R;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable
{   Context context;
    ArrayList<Symbiosis> pl;
    ArrayList<Symbiosis> pl_filler;

    public MyAdapter(Context c, ArrayList<Symbiosis> p){
        context =c;
        pl=p;
        pl_filler=new ArrayList<>(pl);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.plant1.setText(pl.get(position).getPlant1());
        holder.plant2.setText(pl.get(position).getPlant2());
        holder.tor.setText(pl.get(position).getTypeOfRelationship());
        holder.desc.setText(pl.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return pl.size();
    }

    @Override
    public Filter getFilter() {
        return plFilter;
    }
    private Filter plFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Symbiosis> filteredList = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0 ){
                filteredList.addAll(pl_filler);


            }
            else {

                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Symbiosis symbiosis:pl_filler){
                    if (symbiosis.getPlant1().toLowerCase().contains(filterPattern) || symbiosis.getPlant2().toLowerCase().contains(filterPattern) ||symbiosis.getTypeOfRelationship().toLowerCase().contains(filterPattern) || symbiosis.getDescription().toLowerCase().contains(filterPattern)){
                        filteredList.add(symbiosis);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values= filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
          pl.clear();
          pl.addAll((ArrayList) filterResults.values);
          notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView plant1,plant2,tor,desc;
        String key;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            plant1=(TextView) itemView.findViewById(R.id.plantName1);
            plant2=(TextView) itemView.findViewById(R.id.plantName2);
            tor=(TextView) itemView.findViewById(R.id.TypeOfRelationship);
            desc=(TextView) itemView.findViewById(R.id.descriptionSymbiosis);
            //mp=(TextView) itemView.findViewById(R.id.maximumproduction);
            //d=(TextView) itemView.findViewById(R.id.description);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent  = new Intent(context, EditSymbiosis.class);
                    intent.putExtra("key", key);
                    intent.putExtra("plant1", plant1.getText().toString());
                    intent.putExtra("plant2", plant2.getText().toString());
                    intent.putExtra("typeOfRelationship", tor.getText().toString());
                    intent.putExtra("description", desc.getText().toString());
                    context.startActivity(intent);
                    return false;
                }
            });



        }
    }

}





