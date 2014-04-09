package com.example.pro3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class CalendarMonthViewActivity extends Activity {
 
    GridView monthView;
    CalendarMonthAdapter monthViewAdapter;
 
    TextView monthText;
 
    int curYear;
    int curMonth;
 
    int curPosition;
    EditText scheduleInput;
    Button saveButton;
 
    ListView scheduleList;
    ScheduleListAdapter scheduleAdapter;
    ArrayList outScheduleList;
 
    public static final int REQUEST_CODE_SCHEDULE_INPUT = 1001;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        monthView = (GridView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);
 
        // set listener
        monthView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();
                
                
 
               // Toast.makeText(getApplicationContext(), day + "일이 선택되었습니다.",Toast.LENGTH_SHORT).show();
                //토스트메세지는 귀찮으므로 생략
 
                monthViewAdapter.setSelectedPosition(position);
                monthViewAdapter.notifyDataSetChanged();
 
                // set schedule to the TextView
                curPosition = position;
 
                outScheduleList = monthViewAdapter.getSchedule(position);
                if (outScheduleList == null) {
                    outScheduleList = new ArrayList<ScheduleListItem>();
                }
                scheduleAdapter.scheduleList = outScheduleList;
 
                scheduleAdapter.notifyDataSetChanged();
            }
        });
 
        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();
 
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();
 
                setMonthText();
            }
        });
 
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();
 
                setMonthText();
            }
        });
 
 
        curPosition = -1;
 
        scheduleList = (ListView)findViewById(R.id.scheduleList);
        scheduleAdapter = new ScheduleListAdapter(this);
        scheduleList.setAdapter(scheduleAdapter);
 
 
        scheduleList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                ScheduleListItem curItem = (ScheduleListItem) scheduleAdapter.getItem(position);
                
                //scheduleAdapter.removeItem(curItem); //삭제부분
                
                scheduleAdapter.setSelectedPosition(position);
                scheduleAdapter.notifyDataSetChanged();
 
                // set schedule to the TextView
                curPosition = position;
                
                
                
        
 
              /*  outScheduleList = monthViewAdapter.getSchedule(position);
                if (outScheduleList == null) {
                    outScheduleList = new ArrayList<ScheduleListItem>();
                }
                scheduleAdapter.scheduleList = outScheduleList;
 
                scheduleAdapter.notifyDataSetChanged();*/
                //여기까지 일단 주석처리
            }
        });
    }
 
 
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();
 
        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
    }
 
 
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        addOptionMenuItems(menu);
 
        return true;
    }
 
    private void addOptionMenuItems(Menu menu) {
        int id = Menu.FIRST;
        int id2= 2;
        menu.clear();
 
        menu.add(id, id, Menu.NONE, "일정 추가");
        menu.add(id2,id2,Menu.NONE, "일정 전체삭제");
      //  menu.add(3,3,0,"일정 선택삭제");
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                showScheduleInput();
 
                return true;
            case 2:
            	scheduleAdapter.clear();
            	 scheduleAdapter.notifyDataSetChanged();
            	 Toast.makeText(getApplicationContext(), "전체 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            	
            	return true;
            case 3:
            	//scheduleAdapter.removeItem(curItem);
            	return true;
            default:
                break;
         }
 
         return false;
    }
 
    private void showScheduleInput() {
        Intent intent = new Intent(this, ScheduleInputActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCHEDULE_INPUT);
    }
 
 
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
 
        if (requestCode == REQUEST_CODE_SCHEDULE_INPUT) {
            String time = intent.getStringExtra("time");
            String message = intent.getStringExtra("message");
 
            if (message != null) {
                Toast toast = Toast.makeText(getBaseContext(), "result code : " + resultCode + ", time : " + time + ", message : " + message, Toast.LENGTH_LONG);
                toast.show();
 
                ScheduleListItem aItem = new ScheduleListItem(time, message);
 
 
                if (outScheduleList == null) {
                    outScheduleList = new ArrayList();
                }
                outScheduleList.add(aItem);
 
                monthViewAdapter.putSchedule(curPosition, outScheduleList);
 
                scheduleAdapter.scheduleList = outScheduleList;
                scheduleAdapter.notifyDataSetChanged();
            }
        }
 
    }
 
}