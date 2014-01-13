package com.example.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class Weather extends Activity {
	String request = "http://api.worldweatheronline.com/premium/v1/weather.ashx?q=";
	String city;
	String all = "&format=xml&num_of_days=3&key=mwbm2pptw3pmb6acmeagyhgb";
	TextView textCity,tekTemp,todayTempMorning,tomorrowTempMorning,after_tomorrowTempMorning
	,todayTempDay,tomorrowTempDay,after_tomorrowTempDay,todayTempEvening,tomorrowTempEvening,after_tomorrowTempEvening,today,tomorrow,
	after_tomorrow,todayMorning,todayDay,todayEvening,tomorrowMorning,tomorrowDay,tomorrowEvening,after_tomorrowMorning,
	after_tomorrowDay,after_tomorrowEvening;
	ImageView tekWeather,todayWeatherMorning,tomorrowWeatherMorning,after_tomorrowWeatherMorning
	,todayWeatherDay,tomorrowWeatherDay,after_tomorrowWeatherDay,todayWeatherEvening,tomorrowWeatherEvening,after_tomorrowWeatherEvening;
	String city1;
	
	HashMap<Integer,Integer> mas = new HashMap<Integer,Integer>();
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		city1 = getIntent().getExtras().getString("city");
		city = "";
	    for (int h = 0; h < city1.length(); h++)
	    {
	    	if (city1.charAt(h) ==' ')
	    	{
	    		city +='+';
	    	} else 
	    	{
	    		city += city1.charAt(h);
	    	}
	    }
		load();
		loadId();
		MyTask q = new MyTask();
		
		q.execute();
	}
	
	public void loadId()
	{
		textCity = (TextView)findViewById(R.id.textcity);
		tekTemp = (TextView)findViewById(R.id.tekTemp);
		tekWeather = (ImageView)findViewById(R.id.tekWeather);
		
		todayTempMorning = (TextView)findViewById(R.id.todayTempMorning);
		todayTempDay = (TextView)findViewById(R.id.todayTempDay);
		todayTempEvening= (TextView)findViewById(R.id.todayTempEvening);
		
		tomorrowTempMorning = (TextView)findViewById(R.id.tomorrowTempMorning);
		tomorrowTempDay = (TextView)findViewById(R.id.tomorrowTempDay);
		tomorrowTempEvening = (TextView)findViewById(R.id.tomorrowTempEvening);
		
		after_tomorrowTempMorning = (TextView)findViewById(R.id.after_tomorrowTempMorning);
		after_tomorrowTempDay = (TextView)findViewById(R.id.after_tomorrowTempDay);
		after_tomorrowTempEvening = (TextView)findViewById(R.id.after_tomorrowTempEvening);
		
		todayWeatherMorning = (ImageView)findViewById(R.id.todayWeatherMorning);
		todayWeatherDay = (ImageView)findViewById(R.id.todayWeatherDay);
		todayWeatherEvening= (ImageView)findViewById(R.id.todayWeatherEvening);
		
		tomorrowWeatherMorning = (ImageView)findViewById(R.id.tomorrowWeatherMorning);
		tomorrowWeatherDay = (ImageView)findViewById(R.id.tomorrowWeatherDay);
		tomorrowWeatherEvening = (ImageView)findViewById(R.id.tomorrowWeatherEvening);
		
		after_tomorrowWeatherMorning = (ImageView)findViewById(R.id.after_tomorrowWeatherMorning);
		after_tomorrowWeatherDay = (ImageView)findViewById(R.id.after_tomorrowWeatherDay);
		after_tomorrowWeatherEvening = (ImageView)findViewById(R.id.after_tomorrowWeatherEvening);
		
		today = (TextView)findViewById(R.id.today);
		tomorrow = (TextView)findViewById(R.id.tomorrow);
		after_tomorrow = (TextView)findViewById(R.id.after_tomorrow);
		
		todayMorning = (TextView)findViewById(R.id.morning);
		todayDay = (TextView)findViewById(R.id.day);
		todayEvening = (TextView)findViewById(R.id.evening);
		
		tomorrowMorning = (TextView)findViewById(R.id.tomorrowMorning);
		tomorrowDay = (TextView)findViewById(R.id.tomorrowday);
		tomorrowEvening = (TextView)findViewById(R.id.tomorrowevening);
		
		after_tomorrowMorning = (TextView)findViewById(R.id.after_tomorrowMorning);
		after_tomorrowDay = (TextView)findViewById(R.id.after_tomorrowday);
		after_tomorrowEvening = (TextView)findViewById(R.id.after_tomorrowevening);
		
		
		
	}
	public void load()
	{
		mas.put(395,R.drawable.rain_snow );
		mas.put(392,R.drawable.rain_snow );
		mas.put(389,R.drawable.rain_snow );
		mas.put(386,R.drawable.rain_snow );
		mas.put(377,R.drawable.hail );
		mas.put(374,R.drawable.hail );
		mas.put(371,R.drawable.rain_snow );
		mas.put(368,R.drawable.sun_snow );
		mas.put(365,R.drawable.rain_snow );
		mas.put(362,R.drawable.rain_snow );
		mas.put(359,R.drawable.big_rain );
		mas.put(356,R.drawable.rain );
		mas.put(353,R.drawable.sun_rain );
		mas.put(350,R.drawable.hail );
		mas.put(338,R.drawable.big_snow );
		mas.put(335,R.drawable.snow );
		mas.put(332,R.drawable.snow );
		mas.put(329,R.drawable.snow );
		mas.put(326,R.drawable.sun_snow );
		mas.put(323,R.drawable.sun_snow );
		mas.put(320,R.drawable.rain_snow );
		mas.put(317,R.drawable.sun_snow );
		mas.put(314,R.drawable.rain_snow );
		mas.put(311,R.drawable.rain_snow );
		mas.put(308,R.drawable.big_rain );
		mas.put(305,R.drawable.big_rain );
		mas.put(302,R.drawable.rain );
		mas.put(299,R.drawable.rain );
		mas.put(296,R.drawable.sun_rain );
		mas.put(293,R.drawable.sun_rain );
		mas.put(284,R.drawable.rain );
		mas.put(281,R.drawable.rain );
		mas.put(266,R.drawable.rain );
		mas.put(263,R.drawable.rain );
		mas.put(260,R.drawable.fog );
		mas.put(248,R.drawable.fog );
		mas.put(230,R.drawable.big_snow );
		mas.put(227,R.drawable.big_snow );
		mas.put(200,R.drawable.storm );
		mas.put(185,R.drawable.rain_snow );
		mas.put(182,R.drawable.rain_snow );
		mas.put(179,R.drawable.snow );
		mas.put(176,R.drawable.rain );
		mas.put(143,R.drawable.fog );
		mas.put(122,R.drawable.clouds );
		mas.put(119,R.drawable.cloud );
		mas.put(116,R.drawable.partly );
		mas.put(113,R.drawable.sun );

	}
	public class MyTask extends AsyncTask<Void,Void,Void>
	{
		String tek;	
		String ans = "";
		int i;
		int flag = 0;
		int sost = 0;
		Now now = new Now();
		My_Weather today = new My_Weather();
		My_Weather tomorrow = new My_Weather();
		My_Weather after_tomorrow = new My_Weather();
		String out,tek2;
		int time = 0;
		int count = 0;
		int mod;
		@Override
		public Void doInBackground(Void... params) {
			try {
				URL url = new URL(request+city+all);
				URLConnection connection;				
				connection = url.openConnection();				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				while((tek = reader.readLine()) != null)
				{
					ans += tek;									
				}
				reader.close();
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
			}
			return null;
		}
		void parser()
		{
			
			for(i = 0; i < ans.length(); )
			{		
				
				if (ans.charAt(i) == '<' && ans.charAt(i +1) != '/'){	
					i++;
					tek = gets();					
					if (tek.equals("time"))
					{
						out = out_gets();						
						time = Integer.parseInt(out);
						count++;
						mod = count % 8;								
						if (mod == 2 || mod == 5 || mod == 0)
						{
							flag++;
						}
					} else
					{
					   fill();	
					   fill2();
						
					}
					if (tek.equals("current_condition"))
					{
						sost = 1;
					} else 
					{
						if (sost == 1)
						{
							if (tek.equals("temp_C"))
							{
								tek = out_gets();
								now.temp = Integer.parseInt(tek);								
							} else 
							{
								if (tek.equals("weatherCode"))
								{
									tek = out_gets();
									now.sost = Integer.parseInt(tek);									
								}
								else
								{
									tek = out_gets();
								}
							}
							
						} else 
						{							
							if (ans.charAt(i) != '<')
							{
								tek = out_gets();
							}
						}
					} 	  
					
					
				} else 
				{
					if (ans.charAt(i) == '<' && ans.charAt(i +1) == '/')
					{
						i = i + 2;
						tek = gets();
						if (tek.equals("current_condition"))
						{
							sost = 0;
						}
					}
				}
			}
				
			
		}
		void fill()
		{
			if (tek.equals("tempC"))
			{
				int div = flag /3;								
				out = out_gets();
				
				if (div == 0 || div == 1 && mod ==0)
				{
					
					if (mod == 2)
					{
						today.night = Integer.parseInt(out);
					}
					if (mod == 5)
					{
						today.day = Integer.parseInt(out);
					}
					if (mod == 0)
					{
						today.evening = Integer.parseInt(out);
					}
					
				}
				if (div == 1)
				{
					
					if (mod == 2)
					{
						tomorrow.night = Integer.parseInt(out);
					}
					if (mod == 5)
					{
						tomorrow.day = Integer.parseInt(out);
					}
					if (mod == 0)
					{
						tomorrow.evening = Integer.parseInt(out);
					}
				}
				if (div == 2)
				{
					
					if (mod == 2)
					{
						after_tomorrow.night = Integer.parseInt(out);
					}
					if (mod == 5)
					{
						after_tomorrow.day = Integer.parseInt(out);
					}
					if (mod == 0)
					{
						after_tomorrow.evening = Integer.parseInt(out);
					}
				}
			}
		}
		void fill2()
		{
			if (flag != 0)
			   {
				   if (tek.equals("weatherCode"))
				   {
					   int div = flag /3;								
						out = out_gets();
						int mod1 = flag % 3;
						System.out.println(div+ " "+ mod1);
						if (div == 0 || div==1 && mod1==0)
						{
							if (mod1 == 1)
							{
								today.sost1 =Integer.parseInt(out); 
							}
							if (mod1 == 2)
							{
								today.sost2 =Integer.parseInt(out); 
							}
							if (mod1 == 0)
							{
								today.sost3 = Integer.parseInt(out); 
							}
							//today.sost =Integer.parseInt(out); 
						}
						if (div == 1)
						{
							if (mod1 == 1)
							{
								tomorrow.sost1 =Integer.parseInt(out); 
							}
							if (mod1 == 2)
							{
								tomorrow.sost2 =Integer.parseInt(out); 
							}
							if (mod1 == 0)
							{
								tomorrow.sost3 =Integer.parseInt(out); 
							}
							//tomorrow.sost = Integer.parseInt(out);
						}
						if (div == 2)
						{
							if (mod1 == 1)
							{
								after_tomorrow.sost1 =Integer.parseInt(out); 
							}
							if (mod1 == 2)
							{
								after_tomorrow.sost2 =Integer.parseInt(out); 
							}
							if (mod1 == 0)
							{
								after_tomorrow.sost3 =Integer.parseInt(out); 
							}
							//after_tomorrow.sost = Integer.parseInt(out);
						}
				   }
			   }
		}
		String gets()

		{
			String tek1 = "";			
			while (ans.charAt(i) != '>')
			{
				tek1 += ans.charAt(i);
				i++;
			}
			i++;			
			return tek1;
		}
	    String out_gets()

	    {
	    	String tek1 = "";
	    	while(ans.charAt(i) != '<')
	    	{
	    		tek1 += ans.charAt(i);
	    		i++;
	    	}
	    	
	    	return tek1;
	    }
	    @SuppressLint("NewApi")
		private  Point getDisplaySize(final Display display) {
			Point point = new Point();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) { // API
																				// LEVEL
																				// 13
				display.getSize(point);
			} else {
				point.x = display.getWidth();
				point.y = display.getHeight();
			}
			return point;
		}
	    @Override
		protected void onPostExecute(Void params) {
	    	super.onPostExecute(params);
	    	try{
	    	parser();
	    	if (today.sost1 == 0)
			{
				
					throw new Exception("No town");
				
			}
	    	textCity.setTextColor(Color.DKGRAY);
			textCity.setText(city1);
			tekTemp.setText(now.temp+"°C");
			Bitmap b = BitmapFactory.decodeResource(getApplicationContext()	.getResources(), mas.get(now.sost));
			Display display = getWindowManager().getDefaultDisplay();
			Point size = getDisplaySize(display);
			
			int width = size.x;
			int height = size.y;
			//width = Math.min((int) width , b.getWidth());
			Bitmap c = Bitmap.createScaledBitmap(b, (int)(width/1.5), (int) (height / 2.0),true);
			
			tekWeather.setImageBitmap(c);
		    Weather.this.today.setText("Today");
			
		    todayMorning.setText("Morning");
			todayTempMorning.setText(today.night +"°C");
			todayDay.setText("Day");
			todayTempDay.setText(today.day+"°C");
			todayEvening.setText("Evening");
			todayTempEvening.setText(today.evening+"°C");
			
			Weather.this.tomorrow.setText("Tomorrow");
			 tomorrowMorning.setText("Morning");
			tomorrowTempMorning.setText(tomorrow.night +"°C");
			 tomorrowDay.setText("Day");
			tomorrowTempDay.setText(tomorrow.day+"°C");
			 tomorrowEvening.setText("Evening");
			tomorrowTempEvening.setText(tomorrow.evening+"°C");
			
			Weather.this.after_tomorrow.setText("After Tomorrow");
			after_tomorrowMorning.setText("Morning");
			after_tomorrowTempMorning.setText(after_tomorrow.night +"°C");
			after_tomorrowDay.setText("Day");
			after_tomorrowTempDay.setText(after_tomorrow.day+"°C");
			after_tomorrowEvening.setText("Evening");
			after_tomorrowTempEvening.setText(after_tomorrow.evening+"°C");
			
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(today.sost1));
			todayWeatherMorning.setImageBitmap(b);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(today.sost2));
			todayWeatherDay.setImageBitmap(b);
			//System.out.println(today.sost3);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(today.sost3));
			todayWeatherEvening.setImageBitmap(b);
			
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(tomorrow.sost1));
			tomorrowWeatherMorning.setImageBitmap(b);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(tomorrow.sost2));
			tomorrowWeatherDay.setImageBitmap(b);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(tomorrow.sost3));
			tomorrowWeatherEvening.setImageBitmap(b);
			
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(after_tomorrow.sost1));
			after_tomorrowWeatherMorning.setImageBitmap(b);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(after_tomorrow.sost2));
			after_tomorrowWeatherDay.setImageBitmap(b);
			b = BitmapFactory.decodeResource(getApplicationContext().getResources(), mas.get(after_tomorrow.sost3));
			after_tomorrowWeatherEvening.setImageBitmap(b);
			
	    	
			//System.out.println("today.sost1="+today.sost1);
			//System.out.println("today.sost2="+today.sost2);
			
			/*System.out.println("tomorrow.night="+tomorrow.night);
			System.out.println("tomorrow.day="+tomorrow.day);
			System.out.println("tomorrow.evening="+tomorrow.evening);
			System.out.println("tomorrow.sost="+tomorrow.sost);
			
			System.out.println("after_tomorrow.night="+after_tomorrow.night);
			System.out.println("after_tomorrow.day="+after_tomorrow.day);
			System.out.println("after_tomorrow.evening="+after_tomorrow.evening);
			System.out.println("after_tomorrrow.sost="+after_tomorrow.sost);*/}
	    	catch(Exception e)
	    	{
	    		textCity.setText("Error");
	    	}
		}
	    	
	}
	public class My_Weather
	{
		int night;
		int day;
		int evening;
		int sost;
		int sost1,sost2,sost3;
		
	}
	public class Now
	{
		int temp;
		int sost;
		
	}
	
	

}
