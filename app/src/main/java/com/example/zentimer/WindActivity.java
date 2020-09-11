package com.example.zentimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WindActivity extends AppCompatActivity {

    int y = 0, m = 0, d = 0, h = 0, mi = 0;
    public String originDay = null;

    private ArrayList<String> TestArray_countList = new ArrayList<String>();
    private ArrayList<String> Squirrel_countList = new ArrayList<String>();
    private ArrayList<String> DragonKingRabbit_countList = new ArrayList<String>();
    private ArrayList<String> GoodPig_countList = new ArrayList<String>();

    private ArrayList<String> Goblin_countList = new ArrayList<String>();
    private ArrayList<String> Puppeteer_countList = new ArrayList<String>();
    private ArrayList<String> SPY_countList = new ArrayList<String>();

    private ArrayList<String> White_tiger_countList = new ArrayList<String>();
    private ArrayList<String> Red_tiger_countList = new ArrayList<String>();

    private ArrayList<String> PK_White_tiger_countList = new ArrayList<String>();
    private ArrayList<String> PK_Red_tiger_countList = new ArrayList<String>();


    long now = System.currentTimeMillis();
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
    private Date nowDate = new Date(now);
    private Date nowDate2 = Calendar.getInstance().getTime();
    private Calendar nowTime;

    String formatDate = sdfNow.format(nowDate);

    TextView dateNow;

    private Context mContext;
    private String timeChoice;

    TextView clockTextView;
    TextView clockTextView2;
    private static Handler mHandler;
    private static Handler mHandler2;
    private static Handler nowTimeHandler;

    private int maxCount = 100;

    String laterDay = null;

    private Thread mainThread;
    private Boolean thSw = true;

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("상태", "onPause");
        mainThread.interrupt();
        Log.i("상태1", String.valueOf(mainThread.isInterrupted()));
        thSw = false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("상태", "onResume " + mainThread);
        //mainThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("상태", "onStart");


        setContentView(R.layout.activity_wind);

//        new Thread() {
//            @Override
//            public void run() {
//                Log.i("쓰레기", "22");
//                Textdown();
//                super.run();
//            }
//        }.start();

        thSw = true;
        mContext = this;

        timeChoice = PerferenceManager.getString(mContext, "rebuild");

        Log.i("시간값0", timeChoice);

        if (timeChoice != null) {
            Log.i("시간값1", timeChoice);
        } else {
            Log.i("시간값2", "22");
        }


        dateNow = findViewById(R.id.nowTime);
//        dateNow.setText(formatDate);

        dateNow.setText(timeChoice);

        // 현재 시간에 5시간 더하기
        Calendar calFive = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        String fiveHour = format.format(nowDate2);
        calFive.setTime(nowDate2);
        calFive.add(Calendar.HOUR, 5);

        // 실시간 시간확인
        clockTextView = findViewById(R.id.clock);
        clockTextView2 = findViewById(R.id.clock2);

        // 쓰레드로 바로바로 카운트
        nowTimeHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                nowTime = Calendar.getInstance();
                super.handleMessage(msg);
            }
        };

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Calendar cal = Calendar.getInstance();

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String time = mFormat.format(date);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String strTime = sdf.format(cal.getTime());

                Date nowDate1 = null;

                try {
                    nowDate1 = sdf.parse(strTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("내용파악8", "22");
                Log.i("타이머계산2", "22");

                if (originDay != null) {
                    Log.i("내용파악3", originDay);
                    // 5시간 젠 타이밍 받기
                    hour(DragonKingRabbit_countList, 5);
                    hour(Squirrel_countList, 5);

                    for (String i : DragonKingRabbit_countList) {
                        Log.i("내용파악2", i);
                    }

                    // 3시간 젠 타이밍 받기
                    hourMinute(Goblin_countList, 2, 48);
                    hourMinute(Puppeteer_countList, 2, 48);
                    hourMinute(SPY_countList, 2, 48);

                    // 6시간 젠타이밍 받기
                    hour(White_tiger_countList, 6);
                    hour(Red_tiger_countList, 6);

                    // 8시간 젠 타이밍 받기
                    hour(PK_White_tiger_countList, 8);
                    hour(PK_Red_tiger_countList, 8);

                    // 9시간 젠 타이밍 받기
                    hour(GoodPig_countList, 9);

//                test1min(TestArray_countList);
                }


                clockTextView = findViewById(R.id.clock);
                clockTextView.setText(strTime);
            }
        };

        mHandler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (timeChoice != null){
                    Log.i("시간값6", timeChoice);
                }

                clockTextView2 = findViewById(R.id.clock2);
                timeChoice = PerferenceManager.getString(mContext, "rebuild");
                Log.i("텍스트내용", timeChoice);
                clockTextView2.setText(timeChoice);
                if (timeChoice != null) {
//                    originDay = timeChoice;
//                    Log.i("시간값4", originDay);
                } else {
                    Log.i("시간값5", "22");
                }
            }
        };

        class NewRunnable implements Runnable {
            @Override
            public void run() {
                Log.i("상태99", String.valueOf(thSw));
                while (thSw) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    nowTimeHandler.sendEmptyMessage(0);
                    mHandler.sendEmptyMessage(0);
                    mHandler2.sendEmptyMessage(0);

                }
            }
        }

        NewRunnable nr = new NewRunnable();
        mainThread = new Thread(nr);
        mainThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("상태", "onStop");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("상태", "onCreate");
        setContentView(R.layout.activity_wind);
        mContext = this;

        new Thread() {
            @Override
            public void run() {
                Log.i("내용파악5", "22");
                Textdown();
                super.run();
            }
        }.start();



        timeChoice = PerferenceManager.getString(mContext, "rebuild");
        Log.i("시간값0", timeChoice);
        if (timeChoice != null) {
            Log.i("시간값1", timeChoice);
        } else {
            Log.i("시간값2", "22");
        }


        dateNow = findViewById(R.id.nowTime);
//        dateNow.setText(formatDate);

//        text = PerferenceManager.getString(mContext, "rebuild");
//        Log.i("테스트0", text);

        dateNow.setText(timeChoice);

        // 현재 시간에 5시간 더하기
        Calendar calFive = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        String fiveHour = format.format(nowDate2);
        calFive.setTime(nowDate2);
        calFive.add(Calendar.HOUR, 5);

        // 실시간 시간확인
        clockTextView = findViewById(R.id.clock);
        clockTextView2 = findViewById(R.id.clock2);

        // 쓰레드로 바로바로 카운트
        nowTimeHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                nowTime = Calendar.getInstance();
                super.handleMessage(msg);
            }
        };

        if (originDay != null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Calendar cal = Calendar.getInstance();

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    String time = mFormat.format(date);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    String strTime = sdf.format(cal.getTime());

                    Date nowDate1 = null;

                    try {
                        nowDate1 = sdf.parse(strTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (originDay != null) {
                        Log.i("타이머계산1", "22");
                        // 5시간 젠 타이밍 받기
                        hour(DragonKingRabbit_countList, 5);
                        hour(Squirrel_countList, 5);

                        for (String i : DragonKingRabbit_countList) {
                            Log.i("내용파악1", i);
                        }


                        // 3시간 젠 타이밍 받기
                        hourMinute(Goblin_countList, 2, 48);
                        hourMinute(Puppeteer_countList, 2, 48);
                        hourMinute(SPY_countList, 2, 48);


                        // 6시간 젠타이밍 받기
                        hour(White_tiger_countList, 6);
                        hour(Red_tiger_countList, 6);

                        // 8시간 젠 타이밍 받기
                        hour(PK_White_tiger_countList, 8);
                        hour(PK_Red_tiger_countList, 8);

                        // 9시간 젠 타이밍 받기
                        hour(GoodPig_countList, 9);
                    }


//                test1min(TestArray_countList);

                    clockTextView = findViewById(R.id.clock);
                    clockTextView.setText(strTime);
                }
            };

            mHandler2 = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    clockTextView2 = findViewById(R.id.clock2);
                    timeChoice = PerferenceManager.getString(mContext, "rebuild");
                    clockTextView2.setText(timeChoice);
                }
            };

            class NewRunnable implements Runnable {
                @Override
                public void run() {
                    while (thSw) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        nowTimeHandler.sendEmptyMessage(0);
                        mHandler.sendEmptyMessage(0);
                        mHandler2.sendEmptyMessage(0);

                    }
                }
            }

            NewRunnable nr = new NewRunnable();
            mainThread = new Thread(nr);
            mainThread.start();
        }

    }


    public void hour(ArrayList<String> fiveZen, int hour) {
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

            // 현재 시간 받기
            String strTime = format.format(cal2.getTime());

            // 젠 타이밍 쌓기 전에 초기화 작업
            fiveZen.clear();

            Date regDate;
            Log.i("오리지널체크",originDay);
            regDate = format.parse(originDay);

            cal2.setTime(regDate);

            // 시간 더하기
            cal2.add(Calendar.HOUR, hour);
            String a_end_time = format.format(cal2.getTime());
            if (cal2.compareTo(nowTime) > 0) {
                fiveZen.add(a_end_time);
            } else {
                fiveZen.remove(a_end_time);
            }

            for (int i = 0; i <= maxCount; i++) {

                regDate = format.parse(a_end_time);
                cal2.setTime(regDate);
                cal2.add(Calendar.HOUR, hour);
                a_end_time = format.format(cal2.getTime());

                if (cal2.compareTo(nowTime) > 0) {
                    fiveZen.add(a_end_time);
                } else {
                    fiveZen.remove(a_end_time);
                }
            }

            for (String i : fiveZen) {
                Log.i("내용물확인1", i);
            }


            FileOutputStream fos = null;
            try {
                fos = openFileOutput("hour5.txt", Context.MODE_PRIVATE);
                fos.write(fiveZen.toString().getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("사이즈확인1", String.valueOf(fiveZen.size()));
            // 걍 클리어가 아니라 중복내용만 삭제 하자

            if (fiveZen.size() > 4000) {
                Log.i("사이즈확인5", "사이즈오버");
                fiveZen.clear();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void hourMinute(ArrayList<String> fiveZen, int hour, int minute) {
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

            // 현재 시간 받기
            String strTime = format.format(cal2.getTime());

            Date regDate;
            regDate = format.parse(originDay);

            cal2.setTime(regDate);

            // 시간 더하기
            cal2.add(Calendar.HOUR, hour);
            cal2.add(Calendar.MINUTE, minute);
            String a_end_time = format.format(cal2.getTime());
            if (cal2.compareTo(nowTime) > 0) {
                fiveZen.add(a_end_time);
            } else {
                fiveZen.remove(a_end_time);
            }

            for (int i = 0; i <= maxCount; i++) {

                regDate = format.parse(a_end_time);
                cal2.setTime(regDate);
                cal2.add(Calendar.HOUR, hour);
                cal2.add(Calendar.MINUTE, minute);
                a_end_time = format.format(cal2.getTime());

                if (cal2.compareTo(nowTime) > 0) {
                    fiveZen.add(a_end_time);
                } else {
                    fiveZen.remove(a_end_time);
                }
            }

            for (String i : fiveZen) {
                Log.i("내용물확인", i);
            }


            FileOutputStream fos = null;
            try {
                fos = openFileOutput("hour5.txt", Context.MODE_PRIVATE);
                fos.write(fiveZen.toString().getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("사이즈확인1", String.valueOf(fiveZen.size()));
            // 걍 클리어가 아니라 중복내용만 삭제 하자

            if (fiveZen.size() > 4000) {
                Log.i("사이즈확인5", "사이즈오버");
                fiveZen.clear();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void test1min(ArrayList<String> fiveZen) {
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

            // 현재 시간 받기
            String strTime = format.format(cal2.getTime());

            Date regDate;
            regDate = format.parse(originDay);

            cal2.setTime(regDate);

            // 시간 더하기
            cal2.add(Calendar.MINUTE, 1);
            String a_end_time = format.format(cal2.getTime());
            if (cal2.compareTo(nowTime) > 0) {
                fiveZen.add(a_end_time);
            } else {
                fiveZen.remove(a_end_time);
            }

            for (int i = 0; i <= maxCount; i++) {

                regDate = format.parse(a_end_time);
                cal2.setTime(regDate);
                cal2.add(Calendar.MINUTE, 1);
                a_end_time = format.format(cal2.getTime());

                if (cal2.compareTo(nowTime) > 0) {
                    fiveZen.add(a_end_time);
                } else {
                    fiveZen.remove(a_end_time);
                }
            }

            for (String i : fiveZen) {
                Log.i("내용물확인", i);
            }


            FileOutputStream fos = null;
            try {
                fos = openFileOutput("hour5.txt", Context.MODE_PRIVATE);
                fos.write(fiveZen.toString().getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("사이즈확인1", String.valueOf(fiveZen.size()));
            // 걍 클리어가 아니라 중복내용만 삭제 하자

            if (fiveZen.size() > 4000) {
                Log.i("사이즈확인5", "사이즈오버");
                fiveZen.clear();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void dayChoice(View view) {
        showTime();
        showDate();
    }

    public void dateTimeCheck(View view) {
        Toast.makeText(getApplicationContext(), timeChoice, Toast.LENGTH_SHORT).show();
    }

    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month + 1;
                d = dayOfMonth;
                PerferenceManager.setString(mContext, "rebuild", y + "/" + m + "/" + d + " " + h + ":" + mi);

            }
        }, 2020, 7, 18);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                mi = minute;
                PerferenceManager.setString(mContext, "rebuild", y + "/" + m + "/" + d + " " + h + ":" + mi);

            }
        }, 21, 12, true);

        timePickerDialog.setMessage("메시지");
        timePickerDialog.show();
    }



    public void clockClick(View view) {
        startActivityForResult(new Intent(this, CountActivity.class), 200);
    }

    public void zenClick(View view) {
        Intent zenIntent = new Intent(this, ZenActivity.class);


        // 용왕토끼 젠 데이터 전송
        zenIntent.putExtra("DragonKingRabbit_countList", DragonKingRabbit_countList);
        for (String i : DragonKingRabbit_countList) {
            Log.i("토끼시간", i);
        }
        // 다람쥐 젠 데이터 전송
        zenIntent.putExtra("Squirrel_countList", Squirrel_countList);
        // 착한돼지 젠 데이터 전송
        zenIntent.putExtra("GoodPig_countList", GoodPig_countList);


        // 도깨비 젠 데이터 전송
        zenIntent.putExtra("Goblin_countList", Goblin_countList);
        // 인형술사 젠 데이터 전송
        zenIntent.putExtra("Puppeteer_countList", Puppeteer_countList);
        // 세작 젠 데이터 전송
        zenIntent.putExtra("SPY_countList", SPY_countList);


        // 일반 백호왕 젠 데이터 전송
        zenIntent.putExtra("White_tiger_countList", White_tiger_countList);
        // 일반 적호왕 젠 데이터 전송
        zenIntent.putExtra("Red_tiger_countList", Red_tiger_countList);

        // 폭 백호왕 젠 데이터 전송
        zenIntent.putExtra("PK_White_tiger_countList", PK_White_tiger_countList);
        // 폭 적호왕 젠 데이터 전송
        zenIntent.putExtra("PK_Red_tiger_countList", PK_Red_tiger_countList);

//        zenIntent.putExtra("TestArray_countList", TestArray_countList);
        startActivityForResult(zenIntent, 200);
    }

    public static Uri getUriFromPath(ContentResolver cr, String path) {
        Uri fileUri = Uri.parse(path);
        String filePath = fileUri.getPath();
        Cursor c = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, "_data = '" + filePath + "'", null, null);
        c.moveToNext();
        int id = c.getInt(c.getColumnIndex("_id"));
        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

        return uri;
    }

    public void Textdown() {
        try {
            // 원하는 페이지에 대한 URL 생성
            URL url = new URL("http://mireenetwice.mireene.com/wind/server_time.txt");

            // 서버에서 반환 한 모든 텍스트 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                // tr은 한 줄의 텍스트입니다. readLine ()은 개행 문자를 제거합니다.
                originDay = str;
                Log.i("내용파악7", originDay);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}

