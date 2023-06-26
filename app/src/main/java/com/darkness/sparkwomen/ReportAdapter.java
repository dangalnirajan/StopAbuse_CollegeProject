package com.darkness.sparkwomen;

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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<MyViewsHolder> {
    private Context context;
    private List<DataClass> detailReportList;

    public ReportAdapter(Context context, List<DataClass> detailReportList) {
        this.context = context;
        this.detailReportList = detailReportList;
    }

    @NonNull
    @Override
    public MyViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent, false);
        return new MyViewsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewsHolder holder, int position) {
        Glide.with(context).load(detailReportList.get(position).getImage()).into(holder.reportImage);
        holder.reportName.setText(detailReportList.get(position).getName());
        holder.reportContact.setText(detailReportList.get(position).getContact());
        holder.reportAddress.setText(detailReportList.get(position).getAddress());
        holder.reportDescription.setText(detailReportList.get(position).getDescription());

        holder.reportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReportDetailActivity.class);
                intent.putExtra("Image",detailReportList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",detailReportList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Address",detailReportList.get(holder.getAdapterPosition()).getAddress());
                intent.putExtra("Description",detailReportList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Contact",detailReportList.get(holder.getAdapterPosition()).getContact());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailReportList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        detailReportList=searchList;
        notifyDataSetChanged();
    }
}

class  MyViewsHolder extends RecyclerView.ViewHolder{
    ImageView reportImage;
    TextView reportName,reportContact,reportDescription,reportAddress;
    CardView reportCard;

    public MyViewsHolder(View itemView) {
        super(itemView);

        reportImage= itemView.findViewById(R.id.recImage);
        reportCard= itemView.findViewById(R.id.recCard);
        reportName= itemView.findViewById(R.id.report_name);
        reportAddress= itemView.findViewById(R.id.report_address);
        reportContact= itemView.findViewById(R.id.report_contact);
        reportDescription= itemView.findViewById(R.id.report_description);
    }
}