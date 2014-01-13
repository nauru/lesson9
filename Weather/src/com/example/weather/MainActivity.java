package com.example.weather;



import java.util.ArrayList;




import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	DataBaseHelper sqh;
	SQLiteDatabase sqdb;
	ArrayAdapter<String> adapter;
	ListView listview;
	ArrayList<String> city = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sqh = new DataBaseHelper(this);
		
		final EditText enter_city = (EditText)findViewById(R.id.editText1);
		Button add = (Button)findViewById(R.id.button1);
		
		ListView list_city = (ListView)findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, city);
		list_city.setAdapter(adapter);
		getCount();
		list_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View p,int position, long id) {
				String s = city.get(position);				
				Intent intent = new Intent(MainActivity.this,Weather.class);
				intent.putExtra("city", s);
				startActivity(intent);

			}
		});
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				sqdb = sqh.getWritableDatabase();				
				ContentValues cv = new ContentValues();				
				String s = enter_city.getText().toString();	
				enter_city.setText("");
				cv.put(DataBaseHelper.CATNAME, s);
				sqdb.insert(DataBaseHelper.TABLE_NAME, DataBaseHelper.CATNAME,cv);
				sqdb.close();
				getCount();
				

			}
		});	
		list_city.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View p,int position, long id) {
				sqdb = sqh.getWritableDatabase();
				
				String s = city.get(position);				
				sqdb.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper.CATNAME +" =?",new String[]{String.valueOf(s)});
				//sqdb.execSQL("DELETE FROM"+DataBaseHelper.TABLE_NAME+"WHERE"+DataBaseHelper.CATNAME+"="+s);
				sqdb.close();
				getCount();
				return true;
			}
			
		});
		
		
	}
	public void getCount() {
		sqdb = sqh.getWritableDatabase();
		city.clear();
		
		Cursor cursor = sqdb.query(DataBaseHelper.TABLE_NAME, null, null,null, 	null,null, null	);		
		System.out.println("cursor");
		while (cursor.moveToNext()) {			
			String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.CATNAME));
			city.add(name);
		}
		cursor.close();
		sqdb.close();
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
