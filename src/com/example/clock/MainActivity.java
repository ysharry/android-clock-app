package com.example.clock;

import com.example.clock.MainActivity;
import com.example.clock.TriggerActivity;
import com.example.clock.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.format.Time;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button confirmButton;
	private Button checkButton;
	private TimePicker timePutIn;
	private int setHour;
	private int setMinute;
	private Time time;
	private Handler handler = new Handler();
	private int timeDifference;
	private String s;
	private String currentAlarm;
	private boolean isCanceled = true;
	
	private Runnable r = new Runnable(){
		public void run(){
				isCanceled = true;//运行前改变isCanceled值，以告知"查看闹钟"按钮该闹钟已经被执行
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, TriggerActivity.class);
				intent.putExtra("hour", setHour);
				intent.putExtra("minute", setMinute);
				MainActivity.this.startActivity(intent);//跳转至触发闹铃的activity
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//“查看闹钟”按钮的设置
		checkButton = (Button)findViewById(R.id.check_button);
		checkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog info = new AlertDialog.Builder(MainActivity.this).create();
				info.setIcon(R.drawable.dialog_icon);
				info.setTitle("已设置的闹钟：");
				info.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				});
				
				if(isCanceled == true){info.setMessage("未设置闹钟");}//根据isCanceled来判断是否有未执行的闹钟
				else{
					currentAlarm = "当前闹钟将在"+ setHour + "点" + setMinute + "分启动";
					info.setMessage(currentAlarm);
					info.setButton(DialogInterface.BUTTON_NEGATIVE,"取消闹钟", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							isCanceled = true;//取消闹钟时，改变isCanceled的值来告知“查看闹钟”按钮该闹钟已被取消
							handler.removeCallbacks(r);//取消闹钟时，把r从消息队列中召回，从而阻止其运行run方法
						    Toast t = Toast.makeText(MainActivity.this, "您已成功取消该闹钟！", Toast.LENGTH_LONG);
						    t.setGravity(Gravity.CENTER, 0, 0);
							t.show();
						}
					});
				}
				info.show();
			}
		});
		
		//闹钟“确认”按钮的设置
		confirmButton = (Button)findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new OnClickListener(){
        
        	public void onClick(View v) {
        		timePutIn = (TimePicker)findViewById(R.id.time_put_in);
    			setHour = timePutIn.getCurrentHour();//获取用户输入的小时的值
    			setMinute = timePutIn.getCurrentMinute();//获取用户输入的分钟的值
    			time = new Time();
    			time.setToNow();
    			timeDifference = setHour*60*60 + setMinute*60 - time.hour*60*60 - time.minute*60-time.second;//计算时间差，以秒为单位
    		    
    			if (timeDifference >= 0){
    				handler.removeCallbacks(r);//如果r已经在消息队列中，则将其召回
    				isCanceled = false;
    				int hDifference = timeDifference/3600;
        		    int mDifference = timeDifference%3600/60;
        		    int sDifference = timeDifference%3600%60;
        		    if (hDifference != 0){s = "闹钟将在"+hDifference+"小时"+ mDifference+"分"+sDifference+"秒后启动";}
        		    else if (mDifference !=0){s = "闹钟将在"+ mDifference+"分"+sDifference+"秒后启动";}
        		    else{s = "闹钟将在"+sDifference+"秒后启动";}
    				Toast t = Toast.makeText(MainActivity.this, "闹钟设置成功!"+" "+s, Toast.LENGTH_LONG);
    				t.setGravity(Gravity.CENTER, 0, 0);
    				t.show();
    				handler.postDelayed(r, timeDifference*1000);//将r发送至消息队列，到闹铃时间将其取出，并运行其run方法
    			}
    			else{
    				Toast t = Toast.makeText(MainActivity.this, "当前版本不支持该功能，请重新输入时间", Toast.LENGTH_LONG);
    				t.setGravity(Gravity.CENTER, 0, 0);
    				t.show();
    			}
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
