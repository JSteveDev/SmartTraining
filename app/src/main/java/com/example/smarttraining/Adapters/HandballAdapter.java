package com.example.smarttraining.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Models.Historique.Handball;
import com.example.smarttraining.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandballAdapter extends RecyclerView.Adapter<HandballAdapter.ItemViewHolder> {

	private Context context;
	private List<Handball> items;

	public HandballAdapter(List<Handball> items) {
		this.items = items;
	}

	@NonNull
	@Override
	public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.adapter_handball, parent, false);

		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
		holder.updateWithItem(this.items.get(position));
	}

	@Override
	public int getItemCount() { return items.size(); }

	public Handball getItem(int position) { return this.items.get(position);
	}

	public void updateData(List<Handball> items) {
		this.items = items;
		this.notifyDataSetChanged();
	}


	public class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.handball_date) TextView date;
		@BindView(R.id.handball_equipe1) TextView equipe1;
		@BindView(R.id.handball_equipe2) TextView equipe2;
		@BindView(R.id.handball_capitaine1) TextView capitaine1;
		@BindView(R.id.handball_capitaine2) TextView capitaine2;
		@BindView(R.id.handball_period) TextView period;
		@BindView(R.id.handball_score) TextView score;

		public ItemViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void updateWithItem(Handball handball) {
			this.date.setText(handball.getDate().toString());
			this.equipe1.setText(handball.getTeamName1());
			this.equipe2.setText(handball.getTeamName2());
			this.capitaine1.setText(handball.getCaptain1());
			this.capitaine2.setText(handball.getCaptain2());
			this.period.setText("Period : " + handball.getPeriod() + " minutes");
			this.score.setText(handball.getScore1() + " - " + handball.getScore2());

		}
	}

}

