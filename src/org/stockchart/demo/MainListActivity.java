package org.stockchart.demo;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainListActivity extends ListActivity 
{	
	private static int[] ITEM_IDS = new int[] 
			{
				R.string.item_simple_chart,
				R.string.item_simple_stock_chart,
				R.string.item_realtime_stock_chart,
				R.string.item_logarithmic_scale
			};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getItems());
		
		this.setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
		
		int item_id = ITEM_IDS[position];
		
		switch(item_id)
		{
		case R.string.item_simple_chart:
			startActivity(new Intent(this, SimpleChartActivity.class));
			break;
		case R.string.item_simple_stock_chart:
			startActivity(new Intent(this, SimpleStockChartActivity.class));
			break;
		case R.string.item_realtime_stock_chart:
			startActivity(new Intent(this, RealTimeChartActivity.class));
			break;
		case R.string.item_logarithmic_scale:
			startActivity(new Intent(this, LogarithmicScaleActivity.class));
			break;
		}
	}
	
	private String[] getItems()
	{
		String[] result = new String[ITEM_IDS.length];
		
		for(int i=0;i<ITEM_IDS.length;i++)
		{
			result[i] = this.getString(ITEM_IDS[i]);
		}
		
		return result;
	}
}
