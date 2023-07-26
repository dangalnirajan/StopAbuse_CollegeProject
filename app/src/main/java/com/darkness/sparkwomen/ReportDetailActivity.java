package com.darkness.sparkwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class ReportDetailActivity extends AppCompatActivity {

    TextView detailName,detailAddress,detailContact,detailDescription,detailCategory;
    CheckBox checkBox;
    ImageView detailImage;
    MaterialButton back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        detailName=findViewById(R.id.detailName);
        detailContact=findViewById(R.id.detailContact);
        detailAddress=findViewById(R.id.detailAddress);
        detailDescription=findViewById(R.id.detailDescription);
        detailImage=findViewById(R.id.detailImage);
        detailCategory=findViewById(R.id.detail_category);
        back=findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            boolean isAnonymous=bundle.getBoolean("postAnonymous");
//            detailDescription.setText(bundle.getString("Description"));
//            detailName.setText(bundle.getString("Name"));
//            detailContact.setText(bundle.getString("Contact"));
//            detailAddress.setText(bundle.getString("Address"));
            String description = bundle.getString("Description");
            String name = bundle.getString("Name");
            String contact = bundle.getString("Contact");
            String address = bundle.getString("Address");
            String category = bundle.getString("Category");

            if(isAnonymous){
                detailName.setVisibility(View.GONE);
                detailContact.setVisibility(View.GONE);
            }else {
                detailName.setVisibility(View.VISIBLE);
                detailContact.setVisibility(View.VISIBLE);
                detailName.setText(name);
                detailContact.setText(contact);
                detailCategory.setText(category);
            }
            detailDescription.setText(description);
            detailAddress.setText(address);

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportDetailActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}