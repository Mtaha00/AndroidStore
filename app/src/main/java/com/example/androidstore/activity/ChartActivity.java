package com.example.androidstore.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.model.detailProduct.ChartProduct;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {
    private static final String TAG = "ChartActivity";

    Bundle bundle;
    String id;
    int i=0;

    LineChart lineChart;
    List<ChartProduct> chartList=new ArrayList<>();


    //entry import from mikephil.charting
    List<Entry> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        bundle= getIntent().getExtras();

        id=bundle.getString(Key.id);

        lineChart=findViewById(R.id.lineChart);
        Map<String , String > map=new HashMap<>();
        map.put(Key.id,id);
        Api api= RetrofitInstance.getInstance();
        api.getChart(map).enqueue(new Callback<List<ChartProduct>>() {
            @Override
            public void onResponse(Call<List<ChartProduct>> call, Response<List<ChartProduct>> response) {
                List<ChartProduct> chartProducts=response.body();
                for (ChartProduct chartProduct:chartProducts){

                    chartList.add(chartProduct);
                    values.add(new Entry(i,Integer.valueOf(chartList.get(i).getPrice())));

                    LineDataSet lineDataSet=new LineDataSet(values, "قیمت محصول");
                    lineDataSet.setDrawFilled(true);
                    lineDataSet.setLineWidth(4f);

                    //age bekhay masahat zire sathe nemudar ro rangi koni
//                    lineDataSet.setFillDrawable(ContextCompat.getDrawable(ChartActivity.this,R.drawable.bg_chart));

                    List<ILineDataSet> iLineDataSets=new ArrayList<>();
                    iLineDataSets.add(lineDataSet);
                    LineData lineData=new LineData(iLineDataSets);

                    lineChart.setData(lineData);
                    lineChart.animateXY(1000,1000);

                    XAxis xAxis= lineChart.getXAxis();
                    xAxis.setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            //shayad to in khat error bekhorim jaye chartProducts.toArray().length adad bezar
                            xAxis.setLabelCount(5,true);
                            return chartList.get((int) value).getMonth();
                        }
                    });

                    i++;
                }



                Log.i(TAG, "onResponse: chart OK");
            }

            @Override
            public void onFailure(Call<List<ChartProduct>> call, Throwable t) {
                Log.i(TAG, "onFailure: chart : "+ t.toString());
            }
        });


    }
}