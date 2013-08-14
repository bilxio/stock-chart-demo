package org.stockchart.demo;

import org.stockchart.StockChartActivity;
import org.stockchart.core.Area;
import org.stockchart.series.LinearSeries;

import android.graphics.Color;
import android.os.Bundle;

public class SimpleChartActivity extends StockChartActivity
{
	private static final int POINTS_COUNT = 100;
	
	private LinearSeries fSinSeries;
	private LinearSeries fCosSeries;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		
		populateChart();
		this.getStockChartView().invalidate();
	}

	private void populateChart()
	{
		double value = 0.0;
		
		for(int i=0;i<POINTS_COUNT;i++)
		{
			fSinSeries.addPoint(Math.sin(value));
			fCosSeries.addPoint(Math.cos(value));
			value+=0.1;
		}
	}

	@Override
	protected void initChart() 
	{
		fSinSeries = new LinearSeries();
		fSinSeries.setName("sin");		
		fSinSeries.getAppearance().setOutlineColor(Color.RED);
		
		fCosSeries = new LinearSeries();
		fCosSeries.getAppearance().setOutlineColor(Color.BLUE);
		fCosSeries.setName("cos");
		
		Area a = this.getStockChartView().addArea();
		
		a.getSeries().add(fCosSeries);
		a.getSeries().add(fSinSeries);
	}

	@Override
	protected void restoreChart() 
	{
		fSinSeries = (LinearSeries) this.getStockChartView().findSeriesByName("sin");
		fCosSeries = (LinearSeries) this.getStockChartView().findSeriesByName("cos");
	}
}
