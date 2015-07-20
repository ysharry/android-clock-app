package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View implements Runnable {

	Handler hd = new Handler();
	final int CENTER_X = 235;
	final int CENTER_Y = 150;

	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		hd.postDelayed(this, 0);
	}

	@Override
	public void run() {
		invalidate();
		hd.postDelayed(this, 1000);
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int r;
		Time time = new Time();
		time.setToNow();
		// System.out.println(time.hour + ":" + time.minute + ":"+ time.second);
		// ‘§±∏ª≠± 
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// ª≠±Ì≈Ã
		paint.setTextSize(35);
		canvas.drawText("Harry", 195, 110, paint);
		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				r = 5;
			} else {
				r = 2;
			}
			canvas.drawCircle((float) Math.sin(2 * Math.PI * i / 60) * 120
					+ CENTER_X, (float) (Math.cos(2 * Math.PI * i / 60)) * 120
					+ CENTER_Y, r, paint);
		}
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		canvas.drawCircle(CENTER_X, CENTER_Y, 138, paint);
		paint.setStrokeWidth(8);
		canvas.drawCircle(CENTER_X, CENTER_Y, 130, paint);

		// ª≠ ±’Î
		paint.setStrokeWidth(3);
		paint.setColor(Color.BLACK);
		canvas.drawLines(
				new float[] {
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* 60 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* -60 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								- Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* 8 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								- Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* -8 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								- Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* 8 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								- Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* -8 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* -10 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* 10 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* -10 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* 10 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60 + Math.PI / 2))
								* 8 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* -8 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* 8 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 2 + Math.PI / 6 * time.minute / 60))
								* -8 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* 60 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.hour / 12
								+ Math.PI / 6 * time.minute / 60))
								* -60 + CENTER_Y }, paint);

		// ª≠∑÷’Î
		paint.setStrokeWidth(2);
		canvas.drawLines(
				new float[] {
						(float) (Math.sin(2 * Math.PI * time.minute / 60))
								* 100 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60))
								* -100 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60
								- Math.PI / 2))
								* 5 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60
								- Math.PI / 2))
								* -5 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60
								- Math.PI / 2))
								* 5 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60
								- Math.PI / 2))
								* -5 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60))
								* -30 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60)) * 30
								+ CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60))
								* -30 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60)) * 30
								+ CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60
								+ Math.PI / 2))
								* 5 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60
								+ Math.PI / 2))
								* -5 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60
								+ Math.PI / 2))
								* 5 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60
								+ Math.PI / 2))
								* -5 + CENTER_Y,
						(float) (Math.sin(2 * Math.PI * time.minute / 60))
								* 100 + CENTER_X,
						(float) (Math.cos(2 * Math.PI * time.minute / 60))
								* -100 + CENTER_Y }, paint);

		// ª≠√Î’Î
		paint.setColor(Color.RED);
		canvas.drawLine((float) (Math.sin(2 * Math.PI * time.second / 60))
				* -30 + CENTER_X,
				(float) (Math.cos(2 * Math.PI * time.second / 60)) * 30
						+ CENTER_Y,
				(float) (Math.sin(2 * Math.PI * time.second / 60)) * 120
						+ CENTER_X,
				(float) (Math.cos(2 * Math.PI * time.second / 60)) * -120
						+ CENTER_Y, paint);

	}

}
