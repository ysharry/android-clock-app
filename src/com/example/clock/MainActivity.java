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
				isCanceled = true;//����ǰ�ı�isCanceledֵ���Ը�֪"�鿴����"��ť�������Ѿ���ִ��
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, TriggerActivity.class);
				intent.putExtra("hour", setHour);
				intent.putExtra("minute", setMinute);
				MainActivity.this.startActivity(intent);//��ת�����������activity
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//���鿴���ӡ���ť������
		checkButton = (Button)findViewById(R.id.check_button);
		checkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog info = new AlertDialog.Builder(MainActivity.this).create();
				info.setIcon(R.drawable.dialog_icon);
				info.setTitle("�����õ����ӣ�");
				info.setButton(DialogInterface.BUTTON_POSITIVE,"ȷ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				});
				
				if(isCanceled == true){info.setMessage("δ��������");}//����isCanceled���ж��Ƿ���δִ�е�����
				else{
					currentAlarm = "��ǰ���ӽ���"+ setHour + "��" + setMinute + "������";
					info.setMessage(currentAlarm);
					info.setButton(DialogInterface.BUTTON_NEGATIVE,"ȡ������", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							isCanceled = true;//ȡ������ʱ���ı�isCanceled��ֵ����֪���鿴���ӡ���ť�������ѱ�ȡ��
							handler.removeCallbacks(r);//ȡ������ʱ����r����Ϣ�������ٻأ��Ӷ���ֹ������run����
						    Toast t = Toast.makeText(MainActivity.this, "���ѳɹ�ȡ�������ӣ�", Toast.LENGTH_LONG);
						    t.setGravity(Gravity.CENTER, 0, 0);
							t.show();
						}
					});
				}
				info.show();
			}
		});
		
		//���ӡ�ȷ�ϡ���ť������
		confirmButton = (Button)findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new OnClickListener(){
        
        	public void onClick(View v) {
        		timePutIn = (TimePicker)findViewById(R.id.time_put_in);
    			setHour = timePutIn.getCurrentHour();//��ȡ�û������Сʱ��ֵ
    			setMinute = timePutIn.getCurrentMinute();//��ȡ�û�����ķ��ӵ�ֵ
    			time = new Time();
    			time.setToNow();
    			timeDifference = setHour*60*60 + setMinute*60 - time.hour*60*60 - time.minute*60-time.second;//����ʱ������Ϊ��λ
    		    
    			if (timeDifference >= 0){
    				handler.removeCallbacks(r);//���r�Ѿ�����Ϣ�����У������ٻ�
    				isCanceled = false;
    				int hDifference = timeDifference/3600;
        		    int mDifference = timeDifference%3600/60;
        		    int sDifference = timeDifference%3600%60;
        		    if (hDifference != 0){s = "���ӽ���"+hDifference+"Сʱ"+ mDifference+"��"+sDifference+"�������";}
        		    else if (mDifference !=0){s = "���ӽ���"+ mDifference+"��"+sDifference+"�������";}
        		    else{s = "���ӽ���"+sDifference+"�������";}
    				Toast t = Toast.makeText(MainActivity.this, "�������óɹ�!"+" "+s, Toast.LENGTH_LONG);
    				t.setGravity(Gravity.CENTER, 0, 0);
    				t.show();
    				handler.postDelayed(r, timeDifference*1000);//��r��������Ϣ���У�������ʱ�佫��ȡ������������run����
    			}
    			else{
    				Toast t = Toast.makeText(MainActivity.this, "��ǰ�汾��֧�ָù��ܣ�����������ʱ��", Toast.LENGTH_LONG);
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
