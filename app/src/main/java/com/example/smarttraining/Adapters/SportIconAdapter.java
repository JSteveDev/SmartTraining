package com.example.smarttraining.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportIconAdapter extends RecyclerView.Adapter<SportIconAdapter.IconViewHolder> {

	private Context context;
	private String[] sportNameList, sportIconList;

	public SportIconAdapter(String[] sportNameList, String[] sportIconList){
		this.sportNameList = sportNameList;
		this.sportIconList = sportIconList;

	}

	@NonNull
	@Override
	public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.fragment_sport_icon, parent, false);

		return new IconViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
		holder.sportName.setText(String.valueOf(sportNameList[position]));
		int resImage = context.getResources().getIdentifier(String.valueOf(sportIconList[position]) , "drawable", context.getPackageName());
		holder.sportImage.setImageResource(resImage);
	}

	@Override
	public int getItemCount() {
		return sportNameList.length;
	}

	public class IconViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.fragment_sport_icone_text)
		TextView sportName;
		@BindView(R.id.fragment_sport_icone)
		ImageView sportImage;


		public IconViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}
