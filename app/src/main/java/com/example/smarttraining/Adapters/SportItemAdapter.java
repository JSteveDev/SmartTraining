package com.example.smarttraining.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Controllers.Activities.BadmintonConfig;
import com.example.smarttraining.Controllers.Activities.HistoryActivity;
import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportItemAdapter extends RecyclerView.Adapter<SportItemAdapter.ItemViewHolder> {

    private Context context;
    private final String[] sportNameList;
	private final String[] sportImageList;

    public SportItemAdapter(String[] sportNameList, String[] sportImageList) {
        this.sportNameList = sportNameList;
        this.sportImageList = sportImageList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_sports_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.sportName.setText(String.valueOf(sportNameList[position]));
        int resImage = context.getResources().getIdentifier(String.valueOf(sportImageList[position]), "drawable", context.getPackageName());
        holder.sportImage.setImageResource(resImage);

        holder.sportCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BadmintonConfig.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sportNameList.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_sport_item_text)
        TextView sportName;
        @BindView(R.id.fragment_sport_item_image)
        ImageView sportImage;
        @BindView(R.id.fragment_sport_item_cardview)
        CardView sportCardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

