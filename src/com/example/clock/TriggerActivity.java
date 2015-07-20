package com.example.clock;

import com.example.clock.TriggerActivity;
import com.example.clock.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TriggerActivity extends Activity{
	private MediaPlayer alarmPlayer;
	private ImageView clock;
	private TextView hintView;
	private Button cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trigger);
		clock = (ImageView)findViewById(R.id.clock);
		clock.setImageResource(R.drawable.clock);
		alarmPlayer = MediaPlayer.create(this, R.raw.alarm);
		alarmPlayer.start();
		alarmPlayer.setLooping(true);
		
		Intent intent = getIntent();
		int setHour = intent.getIntExtra("hour",0);
	    int setMinute = intent.getIntExtra("minute",0);

		hintView = (TextView)findViewById(R.id.alarm_hint);
		hintView.setText("闹铃提示：现在是北京时间"+ setHour+ "点" + setMinute+ "分");
		cancelButton = (Button)findViewById(R.id.cancel);
		cancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				alarmPlayer.stop();
				TriggerActivity.this.finish();
			}
			
		});
		
	}
	

}
