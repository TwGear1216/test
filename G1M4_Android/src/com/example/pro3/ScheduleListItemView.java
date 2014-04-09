package com.example.pro3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleListItemView extends LinearLayout{
	
	private Context mContext;
	 
    private TextView timeText;
    private TextView messageText;
    private LinearLayout linearLayout;
 
    public ScheduleListItemView(Context context) {
        super(context);
 
        mContext = context; 
 
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.schedule_listitem, this, true);
 
        timeText = (TextView) findViewById(R.id.timeText);
        messageText = (TextView) findViewById(R.id.messageText);
        linearLayout = (LinearLayout)findViewById(R.id.layout);
        //setBackgroundColor(Color.GREEN);//왜안되냐고
        //linear.setBackgroundColor(Color.GREEN);
 
    }
 
    public void setTime(String timeStr) {
        timeText.setText(timeStr);
    }
 
    public void setMessage(String messageStr) {
        messageText.setText(messageStr);
    }

	public void setLinear() {
		linearLayout.setBackgroundColor(Color.YELLOW);
	}
	public LinearLayout getLinear(){
		
		return this.linearLayout;
	}
	public void setLineardefault() {
		linearLayout.setBackgroundColor(Color.WHITE);
	}
   
}
