package com.example.symbio.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.symbio.R;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> implements Filterable{
    Context context;
    ArrayList<Plant> pl;
    ArrayList<Plant> pl_filler;

    public MyAdapter2(Context c, ArrayList<Plant> p){
        context =c;
        pl=p;
        pl_filler=new ArrayList<>(pl);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview1,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {
        holder.pn.setText(pl.get(position).getName());
        holder.fl.setText(pl.get(position).getFavourableLight());
        holder.st.setText(pl.get(position).getSoilType());
        holder.wc.setText(pl.get(position).getWateringCondition());
        holder.mp.setText(pl.get(position).getMaxProduction());
        holder.d.setText(pl.get(position).getDescription());


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
            ArrayList<Plant> filteredList = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0 ){
                filteredList.addAll(pl_filler);


            }
            else {

                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Plant plant:pl_filler){
                    if (plant.getName().toLowerCase().contains(filterPattern) || plant.getFavourableLight().toLowerCase().contains(filterPattern) ||plant.getMaxProduction().toLowerCase().contains(filterPattern) || plant.getDescription().toLowerCase().contains(filterPattern)|| plant.getSoilType().toLowerCase().contains(filterPattern)|| plant.getWateringCondition().toLowerCase().contains(filterPattern)){
                        filteredList.add(plant);
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

        TextView pn,fl,st,wc,mp,d;
        String key;


        public MyViewHolder(View itemView)
        {
            super(itemView);
            pn=(TextView) itemView.findViewById(R.id.plantName1);
            fl=(TextView) itemView.findViewById(R.id.favourableLight1);
            st=(TextView) itemView.findViewById(R.id.soilType1);
            wc=(TextView) itemView.findViewById(R.id.wateringCondition1);
            mp=(TextView) itemView.findViewById(R.id.maximumProduction1);
            d=(TextView) itemView.findViewById(R.id.descriptionPlant1);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent  = new Intent(context, EditPlant.class);
                    intent.putExtra("key", key);
                    intent.putExtra("name", pn.getText().toString());
                    intent.putExtra("favourableLight", fl.getText().toString());
                    intent.putExtra("soilType", st.getText().toString());
                    intent.putExtra("wateringCondition", wc.getText().toString());
                    intent.putExtra("maxProduction", mp.getText().toString());
                    intent.putExtra("description", d.getText().toString());
                    context.startActivity(intent);
                    return false;
                }
            });





        }
    }

}
