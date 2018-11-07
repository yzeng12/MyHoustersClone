package com.example.yzeng.myhoustersclone.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yzeng.myhoustersclone.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {

    ImageView icon_alerts;
    private PieChart pieChart;
    private HorizontalBarChart barChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page,container,false);


        pieChart = view.findViewById(R.id.pieChart);
        barChart = view.findViewById(R.id.horizontal_barChart);
        icon_alerts = view.findViewById(R.id.icon_alerts);


        addPieChart();
        addBarChart();

        icon_alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AlertsActivity.class));
            }
        });

        return view;
    }

    private void addBarChart() {

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2002,52.2f));
        barEntries.add(new BarEntry(2004,72.2f));
        barEntries.add(new BarEntry(2006,62.2f));
        barEntries.add(new BarEntry(2008,42.2f));

        barChart.setVisibility(View.VISIBLE);
        barChart.animateY(5000);

        BarDataSet barDataSet = new BarDataSet(barEntries,"Expenses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        barChart.setData(barData);
        barChart.setFitBars(true);

        Description description = new Description();
        description.setText("Expense Rate per Year");
        barChart.setDescription(description);
        barChart.invalidate();


    }

    private void addPieChart() {

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(52.2f, Integer.toString(2002)));
        pieEntries.add(new PieEntry(72.2f, Integer.toString(2004)));
        pieEntries.add(new PieEntry(62.2f, Integer.toString(2006)));
        pieEntries.add(new PieEntry(42.2f, Integer.toString(2008)));

        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateXY(5000,5000);

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Expenses per Year");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        Description description = new Description();
        description.setText("Expense Rate per Year");
        pieChart.setDescription(description);
        pieChart.invalidate();
    }
}