package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    // Object Creation as per XML file Id's


    TextView tvCases,recovered,active,todayCases,totalDeaths,critical,affectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases=findViewById(R.id.tvCases);
        recovered=findViewById(R.id.recovered);
        active=findViewById(R.id.active);
        todayCases=findViewById(R.id.todayCases);
        totalDeaths=findViewById(R.id.totalDeaths);
        critical=findViewById(R.id.critical);
        affectedCountries=findViewById(R.id.affectedCountries);
        simpleArcLoader=findViewById(R.id.simple_arc);
        scrollView=findViewById(R.id.scrollStatus);
        pieChart= findViewById(R.id.piechart);
        fetchData();
    }

    private void fetchData() {

       String url="https://corona.lmao.ninja/v2/all/";

        //simpleArcLoader.start();
        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            tvCases.setText(jsonObject.getString("cases"));
                            recovered.setText(jsonObject.getString("recovered"));
                            active.setText(jsonObject.getString("active"));
                            todayCases.setText(jsonObject.getString("todayCases"));
                            totalDeaths.setText(jsonObject.getString("deaths"));
                            critical.setText(jsonObject.getString("critical"));
                            affectedCountries.setText(jsonObject.getString("affectedCountries"));

                            //adding to piechart

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()),Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()),Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(totalDeaths.getText().toString()),Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()),Color.parseColor("#29B6F6")));

                            pieChart.startAnimation();
                           // simpleArcLoader.stop();
                           // simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                         //   simpleArcLoader.stop();
                          //  simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                //simpleArcLoader.stop();
               // simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue =Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    //API code


    public void goTrackCountries(View view)
    {
        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }

}