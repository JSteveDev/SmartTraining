package com.example.smarttraining.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Models.Historique.BasketBall;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.Models.Historique.Volleyball;
import com.example.smarttraining.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VolleyballAdapter extends RecyclerView.Adapter<VolleyballAdapter.ItemViewHolder> {

	private Activity context;
	private List<Volleyball> dataList;
	private RoomDBHistory database;

	public VolleyballAdapter(Activity context, List<Volleyball> dataList) {
		this.context = context;
		this.dataList = dataList;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_volleyball, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
		Volleyball data = dataList.get(position);
		database = RoomDBHistory.getInstance(context);

		holder.date.setText(data.getDate());
		holder.equipe1.setText(data.getTeamName1());
		holder.equipe2.setText(data.getTeamName2());
		holder.capitaine1.setText(data.getCaptain1());
		holder.capitaine2.setText(data.getCaptain2());
		holder.score.setText(data.getScore1() + " - " + data.getScore2());
		holder.set.setText("Nombre de sets : " + data.getNb_set());
		holder.point.setText("Point maximum : " + data.getNb_point());
		holder.TM.setText("Durée du Temps Mort : " + data.getTpsTM());

		holder.deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Volleyball d = dataList.get(holder.getAdapterPosition());
				database.volleyballDao().deleteItem(d);
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

		@BindView(R.id.volleyball_date) TextView date;
		@BindView(R.id.volleyball_equipe1) TextView equipe1;
		@BindView(R.id.volleyball_equipe2) TextView equipe2;
		@BindView(R.id.volleyball_capitaine1) TextView capitaine1;
		@BindView(R.id.volleyball_capitaine2) TextView capitaine2;
		@BindView(R.id.volleyball_score) TextView score;
		@BindView(R.id.volleyball_delete_ic) ImageView deleteButton;
		@BindView(R.id.volleyball_set) TextView set;
		@BindView(R.id.volleyball_point_max) TextView point;
		@BindView(R.id.volleyball_TM) TextView TM;


		public ItemViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}

