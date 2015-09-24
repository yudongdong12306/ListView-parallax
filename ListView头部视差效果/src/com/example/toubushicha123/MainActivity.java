package com.example.toubushicha123;

import com.example.toubushicha.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String[] ENGLISH = new String[] { "A", "B", "C", "D",
		"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
		"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private ParallaxListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ParallaxListView) findViewById(R.id.parallx);
		listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		View headView = View.inflate(this, R.layout.image, null);
		ImageView iv = (ImageView) headView.findViewById(R.id.iv);
		listView.addHeaderView(headView);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ENGLISH));
		listView.setImagerView(iv);

	}

}
