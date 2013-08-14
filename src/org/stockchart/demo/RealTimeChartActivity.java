package org.stockchart.demo;

import java.util.ArrayList;

import org.stockchart.StockChartActivity;
import org.stockchart.core.Area;
import org.stockchart.core.Axis.Side;
import org.stockchart.demo.utils.StockDataGenerator;
import org.stockchart.demo.utils.StockDataGenerator.Point;
import org.stockchart.series.BarSeries;
import org.stockchart.series.StockSeries;

import android.os.Bundle;
import android.os.Handler;

public class RealTimeChartActivity extends StockChartActivity
{
	private final StockDataGenerator fStockChartDataGenerator = new StockDataGenerator();
	
	private StockSeries fPriceSeries;
	private BarSeries fVolumeSeries;
	
	private Handler fHandler = new Handler();
		
	private static ArrayList<Point> POINTS = new ArrayList<Point>();
	
	private Runnable fUpdateTimeTask = new Runnable() 
	{
	   public void run() 
	   {		   
		   Point p = fStockChartDataGenerator.getNextPoint();
		   
		   fPriceSeries.addPoint(p.o, p.h, p.l, p.c);
		   fVolumeSeries.addPoint(0.0, p.v);
		   
		   POINTS.add(p);
		   
		   fHandler.postDelayed(this, 1000);   
		   
		   RealTimeChartActivity.this.getStockChartView().invalidate();
	   }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
        // timer
		fHandler.removeCallbacks(fUpdateTimeTask);
		fHandler.postDelayed(fUpdateTimeTask, 1000);
	
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		
		fHandler.removeCallbacks(fUpdateTimeTask);
	}
	
	@Override
	protected void initChart() 
	{
		fPriceSeries = new StockSeries();
		fPriceSeries.setName("price");				
		fPriceSeries.setYAxisSide(Side.RIGHT);
		
		fVolumeSeries = new BarSeries();
		fVolumeSeries.setName("volume");
		fVolumeSeries.setYAxisSide(Side.RIGHT);
		
		Area priceArea = this.getStockChartView().addArea();
		
		priceArea.getSeries().add(fPriceSeries);
		
		Area volumeArea = this.getStockChartView().addArea();
		volumeArea.getSeries().add(fVolumeSeries);
	}

	@Override
	protected void restoreChart() 
	{
		fPriceSeries = (StockSeries) this.getStockChartView().findSeriesByName("price");
		fVolumeSeries = (BarSeries) this.getStockChartView().findSeriesByName("volume");		
		
		
		fillPoints();
	}
	
	private void fillPoints()
	{
		for(Point p:POINTS)
		{
			fPriceSeries.addPoint(p.o, p.h, p.l, p.c);
			fVolumeSeries.addPoint(0.0, p.v);
		}
		
		this.getStockChartView().invalidate();
	}

}
