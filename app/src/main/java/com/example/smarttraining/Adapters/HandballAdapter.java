package com.example.smarttraining.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Models.Historique.Handball;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandballAdapter extends RecyclerView.Adapter<HandballAdapter.ItemViewHolder> {

	private Activity context;
	private List<Handball> dataList;
	private RoomDBHistory database;

	public HandballAdapter(Activity context, List<Handball> dataList) {
		this.context = context;
		this.dataList = dataList;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_handball, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
		Handball data = dataList.get(position);
		database = RoomDBHistory.getInstance(context);

		holder.date.setText(data.getDate());
		holder.equipe1.setText(data.getTeamName1());
		holder.equipe2.setText(data.getTeamName2());
		holder.capitaine1.setText(data.getCaptain1());
		holder.capitaine2.setText(data.getCaptain2());
		holder.period.setText("Period : " + data.getPeriod() + " minutes");
		holder.score.setText(data.getScore1() + " - " + data.getScore2());

		holder.deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Handball d = dataList.get(holder.getAdapterPosition());
				database.handballDao().deleteItem(d);
				int position = holder.getAdapterPosition();
				dataList.remove(position);
				notifyItemRemoved(position);
				notifyItemRangeChanged(position, dataList.size());
			}
		});
	}

	@Override
	public int getItemCount() { return dataList.size(); }


	// --- ItemViewHolder ---


	public class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.handball_date) TextView date;
		@BindView(R.id.handball_equipe1) TextView equipe1;
		@BindView(R.id.handball_equipe2) TextView equipe2;
		@BindView(R.id.handball_capitaine1) TextView capitaine1;
		@BindView(R.id.handball_capitaine2) TextView capitaine2;
		@BindView(R.id.handball_period) TextView period;
		@BindView(R.id.handball_score) TextView score;
		@BindView(R.id.handball_delete_ic) ImageView deleteButton;

		public ItemViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}

