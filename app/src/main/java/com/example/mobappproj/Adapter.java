package com.example.mobappproj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Game> games;

    ArrayList<String> titles;
    ArrayList<String> publishers;
    ArrayList<String> genres;
    ArrayList<String> prices;
    Context ctx;

    public Adapter(Context ctx, ArrayList<String> titles, ArrayList<String> publishers, ArrayList<String> genres, ArrayList<String> prices){
        this.titles = titles;
        this.publishers = publishers;
        this.genres = genres;
        this.prices = prices;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.custom_games_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_games_layout, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gameTitle.setText(titles.get(position));
        holder.gamePublisher.setText(publishers.get(position));
        holder.gameGenre.setText(genres.get(position));
        double price = Double.parseDouble(prices.get(position));
        holder.gamePrice.setText("$"+String.format("%.2f", price));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ctx, titles.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, ItemActivity.class);
                intent.putExtra("EXTRA_TITLE", titles.get(position));
                intent.putExtra("EXTRA_PUB", publishers.get(position));
                intent.putExtra("EXTRA_GEN", genres.get(position));
                intent.putExtra("EXTRA_PRICE", price);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView gameTitle, gamePublisher, gameGenre, gamePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.gameTitle);
            gamePublisher = itemView.findViewById(R.id.gamePublisher);
            gameGenre = itemView.findViewById(R.id.gameGenre);
            gamePrice = itemView.findViewById(R.id.gamePrice);
        }
    }
}
