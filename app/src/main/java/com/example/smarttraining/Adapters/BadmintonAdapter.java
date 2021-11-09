package com.example.smarttraining.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Models.Historique.Badminton;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadmintonAdapter extends RecyclerView.Adapter<BadmintonAdapter.ItemViewHolder> {

    private final Activity context;
    private final List<Badminton> dataList;
    private RoomDBHistory database;

    public BadmintonAdapter(Activity context, List<Badminton> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_badminton, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Badminton data = dataList.get(position);
        database = RoomDBHistory.getInstance(context);

        holder.date.setText(data.getDate());
        holder.equipe1.setText(data.getTeam1Names());
        holder.equipe2.setText(data.getTeam2Names());
        holder.nb_set.setText(context.getResources().getString(R.string.nb_set_gagnant, data.getNb_set_gagnant()));
        holder.point.setText(context.getResources().getString(R.string.point_par_set_historique, data.getNb_point()));
        holder.point_max.setText(context.getResources().getString(R.string.point_max_par_set, data.getNb_point_max()));

        holder.score.setText(data.getScore());

        holder.deleteButton.setOnClickListener(view -> {
            Badminton d = dataList.get(holder.getAdapterPosition());
            database.badmintonDao().deleteItem(d);
            int position1 = holder.getAdapterPosition();
            dataList.remove(position1);
            notifyItemRemoved(position1);
            notifyItemRangeChanged(position1, dataList.size());
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    // --- ItemViewHolder ---


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.badminton_date)
        TextView date;
        @BindView(R.id.badminton_equipe1)
        TextView equipe1;
        @BindView(R.id.badminton_equipe2)
        TextView equipe2;
        @BindView(R.id.badminton_nb_set)
        TextView nb_set;
        @BindView(R.id.badminton_nb_point)
        TextView point;
        @BindView(R.id.badminton_nb_point_max)
        TextView point_max;
        @BindView(R.id.badminton_delete_ic)
        ImageView deleteButton;
        @BindView(R.id.badminton_score)
        TextView score;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

