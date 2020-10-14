package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tv_CountryName,tv_Cases,tv_todayCases,tv_totalDeaths,tv_todayDeaths,tv_recovered,tv_active,tv_critical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details Of "+AffectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_CountryName=findViewById(R.id.tv_CountryName);
        tv_Cases=findViewById(R.id.tv_Cases);
        tv_todayCases=findViewById(R.id.tv_todayCases);
        tv_totalDeaths=findViewById(R.id.tv_totalDeaths);
        tv_todayDeaths=findViewById(R.id.tv_todayDeaths);
        tv_recovered=findViewById(R.id.tv_recovered);
        tv_active=findViewById(R.id.tv_active);
        tv_critical=findViewById(R.id.tv_critical);


        tv_CountryName.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        tv_Cases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tv_todayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tv_totalDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
        tv_todayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeaths());
        tv_recovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tv_active.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
        tv_critical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);




    }
}