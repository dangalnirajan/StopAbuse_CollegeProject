package com.darkness.sparkwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ReportDetailActivity extends AppCompatActivity {

    TextView detailName,detailAddress,detailContact,detailDescription;
    CheckBox checkBox;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        detailName=findViewById(R.id.detailName);
        detailAddress=findViewById(R.id.detailAddress);
        detailContact=findViewById(R.id.detailContact);
        detailDescription=findViewById(R.id.detailDescription);
        detailImage=findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            detailDescription.setText(bundle.getString("Description"));
            detailName.setText(bundle.getString("Name"));
            detailAddress.setText(bundle.getString("Address"));
            detailContact.setText(bundle.getString("Contact"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}