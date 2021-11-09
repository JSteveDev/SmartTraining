package com.example.smarttraining.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.Models.Historique.Ultimate;
import com.example.smarttraining.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UltimateAdapter extends RecyclerView.Adapter<UltimateAdapter.ItemViewHolder> {

	private Activity context;
	private List<Ultimate> dataList;
	private RoomDBHistory database;

	public UltimateAdapter(Activity context, List<Ultimate> dataList) {
		this.context = context;
		this.dataList = dataList;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ultimate, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
		Ultimate data = dataList.get(position);
		database = RoomDBHistory.getInstance(context);

		holder.date.setText(data.getDate());
		holder.equipe1.setText(data.getTeamName1());
		holder.equipe2.setText(data.getTeamName2());
		holder.capitaine1.setText(data.getCaptain1());
		holder.capitaine2.setText(data.getCaptain2());
		holder.score.setText(context.getResources().getString(R.string.undefined_score, String.valueOf(data.getScore1()), String.valueOf(data.getScore2())));


		holder.deleteButton.setOnClickListener(view -> {
			Ultimate d = dataList.get(holder.getAdapterPosition());
			database.ultimateDao().deleteItem(d);
			int position1 = holder.getAdapterPosition();
			dataList.remove(position1);
			notifyItemRemoved(position1);
			notifyItemRangeChanged(position1, dataList.size());
		});
	}

	@Override
	public int getItemCount() { return dataList.size(); }

	// --- ItemViewHolder ---

	public class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.ultimate_date) TextView date;
		@BindView(R.id.ultimate_equipe1) TextView equipe1;
		@BindView(R.id.ultimate_equipe2) TextView equipe2;
		@BindView(R.id.ultimate_capitaine1) TextView capitaine1;
		@BindView(R.id.ultimate_capitaine2) TextView capitaine2;
		@BindView(R.id.ultimate_score) TextView score;
		@BindView(R.id.ultimate_delete_ic) ImageView deleteButton;

		public ItemViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}

