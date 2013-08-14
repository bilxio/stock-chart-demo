package org.stockchart.demo;

import org.stockchart.StockChartActivity;
import org.stockchart.core.Area;
import org.stockchart.series.LinearSeries;

import android.graphics.Color;
import android.os.Bundle;

public class LogarithmicScaleActivity  extends StockChartActivity
{
	private static final int POINTS_COUNT = 1000;
	
	private LinearSeries fSeries;

	
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
		double value = 1.0;
		
		for(int i=0;i<POINTS_COUNT;i++)
		{
			fSeries.addPoint(value);
			value+=1.0;
		}
	}

	@Override
	protected void initChart() 
	{
		fSeries = new LinearSeries();
		fSeries.setName("default");		
		fSeries.getAppearance().setOutlineColor(Color.RED);
		
		Area a = this.getStockChartView().addArea();
		
		a.getRightAxis().setLogarithmic(true);
		a.getSeries().add(fSeries);
	}

	@Override
	protected void restoreChart() 
	{
		fSeries = (LinearSeries) this.getStockChartView().findSeriesByName("default");

	}
}
