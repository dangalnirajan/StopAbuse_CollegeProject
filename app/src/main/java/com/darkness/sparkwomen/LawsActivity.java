package com.darkness.sparkwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class LawsActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LawsActivity.this,MainActivity.class));
        LawsActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);
        RecyclerView recyclerView = findViewById(R.id.recycleLaws);
        // put description on strings.xml
        String[] laws = new String[]{"The Prohibition of Child Marriage Act","Special Marriage Act","Dowry Prohibition Act","Nepali Divorce Act","Maternity Benefit Act","Medical Termination of Pregnancy Act","Sexual Harassment of Women at Workplace (Prevention, Prohibition and Redress) Act","Indecent Representation of Women(Prevention) Act","National Commission for Women Act","Equal Remuneration Act"};

        MyAdapter adapter = new MyAdapter(this, laws, position -> {
            Intent intent = new Intent(LawsActivity.this,LawDisplayerActivity.class);
            intent.putExtra("position",position);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        findViewById(R.id.backBtn).setOnClickListener(view -> {
            startActivity(new Intent(LawsActivity.this,MainActivity.class));
            LawsActivity.this.finish();
        });
    }
}