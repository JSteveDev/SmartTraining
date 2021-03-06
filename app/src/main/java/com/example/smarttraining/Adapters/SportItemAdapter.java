package com.example.smarttraining.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarttraining.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportItemAdapter extends RecyclerView.Adapter<SportItemAdapter.ItemViewHolder> {

	private Context context;
	private String[] sportNameList, sportImageList;

	public SportItemAdapter(String[] sportNameList, String[] sportImageList){
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
		int resImage = context.getResources().getIdentifier(String.valueOf(sportImageList[position]) , "drawable", context.getPackageName());
		holder.sportImage.setImageResource(resImage);
	}

	@Override
	public int getItemCount() {
		return sportNameList.length;
	}

	public class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.fragment_sport_item_text) TextView sportName;
		@BindView(R.id.fragment_sport_item_image) ImageView sportImage;


		public ItemViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}

