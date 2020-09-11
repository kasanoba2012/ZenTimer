package com.example.zentimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ZenActivity extends AppCompatActivity {

    private ArrayList<String> TestArray_countList = new ArrayList<String>();

    private TextView DragonKingRabbit_RemainingTime;
    private TextView DragonKingRabbit_ZenTime;
    private ArrayList<String> DragonKingRabbit_countList;


    private TextView GoodPig_RemainingTime;
    private TextView GoodPig_ZenTime;
    private ArrayList<String> GoodPig_countList;

    private TextView Squirrel_RemainingTime;
    private TextView Squirrel_ZenTime;
    private ArrayList<String> Squirrel_countList;

    private TextView Goblin_RemainingTime;
    private TextView Goblin_ZenTime;
    private ArrayList<String> Goblin_countList;

    private TextView Puppeteer_RemainingTime;
    private TextView Puppeteer_ZenTime;
    private ArrayList<String> Puppeteer_countList;

    private TextView SPY_RemainingTime;
    private TextView SPY_ZenTime;
    private ArrayList<String> SPY_countList;

    private TextView White_tiger_RemainingTime;
    private TextView White_tiger_ZenTime;
    private ArrayList<String> White_tiger_countList;

    private TextView Red_tiger_RemainingTime;
    private TextView Red_tiger_ZenTime;
    private ArrayList<String> Red_tiger_countList;

    private TextView PK_White_tiger_RemainingTime;
    private TextView PK_White_tiger_ZenTime;
    private ArrayList<String> PK_White_tiger_countList;

    private TextView PK_Red_tiger_RemainingTime;
    private TextView PK_Red_tiger_ZenTime;
    private ArrayList<String> PK_Red_tiger_countList;

    private int maxCount = 100;
    private Calendar nowTime;

    private static Handler mHandler;

    private Date DragonKingRabbit_NextTime1;

    private Boolean reTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen);

        Intent zenIntent = getIntent(); // 데이터 수신

        // DragonKingRabbit 용왕토끼
        DragonKingRabbit_countList = zenIntent.getExtras().getStringArrayList("DragonKingRabbit_countList");
        DragonKingRabbit_RemainingTime = findViewById(R.id.DragonKingRabbit_RemainingTime);
        DragonKingRabbit_ZenTime = findViewById(R.id.DragonKingRabbit_ZenTime);
        DragonKingRabbit_ZenTime.setText(DragonKingRabbit_countList.get(0));
        Button DragonKingRabbit_Btn = findViewById(R.id.DragonKingRabbit_AlramBtn);
        DragonKingRabbit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(DragonKingRabbit_countList.get(0));
            }
        });
        Button DragonKingRabbit_Channel = findViewById(R.id.DragonKingRabbit_Channel);
        DragonKingRabbit_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(DragonKingRabbit_countList, 5);
            }
        });


        // GoodPig 착한돼지
        GoodPig_countList = zenIntent.getExtras().getStringArrayList("GoodPig_countList");
        GoodPig_RemainingTime = findViewById(R.id.GoodPig_RemainingTime);
        GoodPig_ZenTime = findViewById(R.id.GoodPig_ZenTime);
        GoodPig_ZenTime.setText(GoodPig_countList.get(0));
        Button GoodPig_Btn = findViewById(R.id.GoodPig_AlramBtn);
        GoodPig_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(GoodPig_countList.get(0));
            }
        });
        Button GoodPig_Channel = findViewById(R.id.GoodPig_Channel);
        GoodPig_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(GoodPig_countList, 5);
            }
        });

        // Squirrel 다람쥐
        Squirrel_countList = zenIntent.getExtras().getStringArrayList("Squirrel_countList");
        Squirrel_RemainingTime = findViewById(R.id.Squirrel_RemainingTime);
        Squirrel_ZenTime = findViewById(R.id.Squirrel_ZenTime);
        Squirrel_ZenTime.setText(Squirrel_countList.get(0));
        Button Squirrel_Btn = findViewById(R.id.Squirrel_AlramBtn);
        Squirrel_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(Squirrel_countList.get(0));
            }
        });
        Button Squirrel_Channel = findViewById(R.id.Squirrel_Channel);
        Squirrel_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(Squirrel_countList, 5);
            }
        });


        // Goblin 도깨비
        Goblin_countList = zenIntent.getExtras().getStringArrayList("Goblin_countList");
        Goblin_RemainingTime = findViewById(R.id.Goblin_RemainingTime);
        Goblin_ZenTime = findViewById(R.id.Goblin_ZenTime);
        Goblin_ZenTime.setText(Goblin_countList.get(0));
        Button Goblin_Btn = findViewById(R.id.Goblin_AlramBtn);
        Goblin_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(Goblin_countList.get(0));
            }
        });
        Button Goblin_Channel = findViewById(R.id.Goblin_Channel);
        Goblin_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(Goblin_countList, 5);
            }
        });


        // Puppeteer 인형술사
        Puppeteer_countList = zenIntent.getExtras().getStringArrayList("Puppeteer_countList");
        Puppeteer_RemainingTime = findViewById(R.id.Puppeteer_RemainingTime);
        Puppeteer_ZenTime = findViewById(R.id.Puppeteer_ZenTime);
        Puppeteer_ZenTime.setText(Puppeteer_countList.get(0));
        Button Puppeteer_Btn = findViewById(R.id.Puppeteer_AlramBtn);
        Puppeteer_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(Puppeteer_countList.get(0));
            }
        });
        Button Puppeteer_Channel = findViewById(R.id.Puppeteer_Channel);
        Puppeteer_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(Puppeteer_countList, 5);
            }
        });

        // SPY 세작
        SPY_countList = zenIntent.getExtras().getStringArrayList("SPY_countList");
        SPY_RemainingTime = findViewById(R.id.SPY_RemainingTime);
        SPY_ZenTime = findViewById(R.id.SPY_ZenTime);
        SPY_ZenTime.setText(SPY_countList.get(0));
        Button SPY_Btn = findViewById(R.id.SPY_AlramBtn);
        SPY_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(SPY_countList.get(0));
            }
        });
        Button SPY_Channel = findViewById(R.id.SPY_Channel);
        SPY_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(SPY_countList, 5);
            }
        });


        // White_tiger 백호왕
        White_tiger_countList = zenIntent.getExtras().getStringArrayList("White_tiger_countList");
        White_tiger_RemainingTime = findViewById(R.id.White_tiger_RemainingTime);
        White_tiger_ZenTime = findViewById(R.id.White_tiger_ZenTime);
        White_tiger_ZenTime.setText(White_tiger_countList.get(0));
        Button White_tiger_Btn = findViewById(R.id.White_tiger_AlramBtn);
        White_tiger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(White_tiger_countList.get(0));
            }
        });
        Button White_tiger_Channel = findViewById(R.id.White_tiger_Channel);
        White_tiger_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(White_tiger_countList, 5);
            }
        });

        // Red_tiger 백호왕
        Red_tiger_countList = zenIntent.getExtras().getStringArrayList("Red_tiger_countList");
        Red_tiger_RemainingTime = findViewById(R.id.Red_tiger_RemainingTime);
        Red_tiger_ZenTime = findViewById(R.id.Red_tiger_ZenTime);
        Red_tiger_ZenTime.setText(Red_tiger_countList.get(0));
        Button Red_tiger_Btn = findViewById(R.id.Red_tiger_AlramBtn);
        Red_tiger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(Red_tiger_countList.get(0));
            }
        });
        Button  Red_tiger_Channel = findViewById(R.id.Red_tiger_Channel);
        Red_tiger_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart( Red_tiger_countList, 5);
            }
        });

        // PK_White_tiger 폭 백호왕
        PK_White_tiger_countList = zenIntent.getExtras().getStringArrayList("PK_White_tiger_countList");
        PK_White_tiger_RemainingTime = findViewById(R.id.PK_White_tiger_RemainingTime);
        PK_White_tiger_ZenTime = findViewById(R.id.PK_White_tiger_ZenTime);
        PK_White_tiger_ZenTime.setText(PK_White_tiger_countList.get(0));
        Button PK_White_tiger_Btn = findViewById(R.id.PK_White_tiger_AlramBtn);
        PK_White_tiger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(PK_White_tiger_countList.get(0));
            }
        });
        Button PK_White_tiger_Channel = findViewById(R.id.PK_White_tiger_Channel);
        PK_White_tiger_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(PK_White_tiger_countList, 5);
            }
        });

        // PK_Red_tiger 폭 적호왕
        PK_Red_tiger_countList = zenIntent.getExtras().getStringArrayList("PK_Red_tiger_countList");
        PK_Red_tiger_RemainingTime = findViewById(R.id.PK_Red_tiger_RemainingTime);
        PK_Red_tiger_ZenTime = findViewById(R.id.PK_Red_tiger_ZenTime);
        PK_Red_tiger_ZenTime.setText(PK_Red_tiger_countList.get(0));
        Button PK_Red_tiger_Btn = findViewById(R.id.PK_Red_tiger_AlramBtn);
        PK_Red_tiger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlramCheck(PK_Red_tiger_countList.get(0));
            }
        });
        Button PK_Red_tiger_Channel = findViewById(R.id.PK_Red_tiger_Channel);
        PK_Red_tiger_Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZenReStart(PK_Red_tiger_countList, 5);
            }
        });


        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                // 실시간으로 현재 시간 받기
                if (reTimer == false) {
                    long DragonKingRabbit_diff = timeDiff(DragonKingRabbit_countList, DragonKingRabbit_ZenTime);
                    dateCalculation(DragonKingRabbit_diff, DragonKingRabbit_RemainingTime);
                }


                long GoodPig_diff = timeDiff(GoodPig_countList, GoodPig_ZenTime);
                dateCalculation(GoodPig_diff, GoodPig_RemainingTime);

                long Squirrel_diff = timeDiff(Squirrel_countList, Squirrel_ZenTime);
                dateCalculation(Squirrel_diff, Squirrel_RemainingTime);


                long Goblin_diff = timeDiff(Goblin_countList, Goblin_ZenTime);
                dateCalculation(Goblin_diff, Goblin_RemainingTime);

                long Puppeteer_diff = timeDiff(Puppeteer_countList, Puppeteer_ZenTime);
                dateCalculation(Puppeteer_diff, Puppeteer_RemainingTime);

                long SPY_diff = timeDiff(SPY_countList, SPY_ZenTime);
                dateCalculation(SPY_diff, SPY_RemainingTime);

                long White_tiger_diff = timeDiff(White_tiger_countList, White_tiger_ZenTime);
                dateCalculation(White_tiger_diff, White_tiger_RemainingTime);

                long Red_tiger_diff = timeDiff(Red_tiger_countList, Red_tiger_ZenTime);
                dateCalculation(Red_tiger_diff, Red_tiger_RemainingTime);

                long PK_White_tiger_diff = timeDiff(PK_White_tiger_countList, PK_White_tiger_ZenTime);
                dateCalculation(PK_White_tiger_diff, PK_White_tiger_RemainingTime);

                long PK_Red_tiger_diff = timeDiff(PK_Red_tiger_countList, PK_Red_tiger_ZenTime);
                dateCalculation(PK_Red_tiger_diff, PK_Red_tiger_RemainingTime);
            }
        };

        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(0);
                }
            }
        }

        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();


    }

    public void ZenReStart(ArrayList<String> zenlist, int hour) {
        nowTime = Calendar.getInstance();
        Log.i("시간첵1", String.valueOf(nowTime.getTime()));

        reTimer = true;
        zenlist.clear();

        hour(zenlist, 5);

        reTimer = false;
        for (String i : zenlist) {
            Log.i("시간첵3", i);
        }
    }

    public long timeDiff(List countList, TextView ZenTimeView) {
        Log.i("체크다1", "22");
        Calendar nowTime = Calendar.getInstance();
        String RemainingZentime = (String) countList.get(0);
        Calendar zen_Time = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

        try {
            Date DragonKingRabbit_NextTime = format.parse(RemainingZentime);
            zen_Time.setTime(DragonKingRabbit_NextTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (zen_Time.compareTo(nowTime) > 0) {
            Log.i("체크다", zen_Time.getTime() + " / " + nowTime.getTime());
        } else {
            countList.remove(0);
            String next_Time = (String) countList.get(0);
            ZenTimeView.setText(next_Time);
        }

        long diff = zen_Time.getTimeInMillis() - nowTime.getTimeInMillis();
        Log.i("시간체크1", zen_Time.getTimeInMillis() + " / " + nowTime.getTimeInMillis());

        return diff;
    }

    public void dateCalculation(long diff, TextView textView) {
        long sec = diff / 1000;
        long min = diff / (60 * 1000);
        long hour = diff / (60 * 60 * 1000);
        long day = diff / (24 * 60 * 60 * 1000);

        textView.setText(day + "일" + (hour - day * 24) + "시간" + (min - hour * 60) + "분" + (sec - min * 60) + "초");
    }


    public void AlramCheck(String countList) {
        // 여기에 알람 울리게 하자
        int hour, hour_24, minute;
        String am_pm;
        if (Build.VERSION.SDK_INT >= 23) {
            hour_24 = 12;
            minute = 01;
        } else {
            hour_24 = 19;
            minute = 01;
        }
//        if(hour_24 > 12) {
//            am_pm = "PM";
//            hour = hour_24 - 12;
//        }
//        else
//        {
//            hour = hour_24;
//            am_pm="AM";
//        }
        // 현재 지정된 시간으로 알람 시간 설정
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour_24);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

        try {
            Date NextTime = format.parse(countList);
            calendar.setTime(NextTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        Date currentDateTime = calendar.getTime();
        String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
        Toast.makeText(getApplicationContext(), date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();

        //  Preference에 설정한 값 저장
        SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
        editor.putLong("nextNotifyTime", (long) calendar.getTimeInMillis());
        editor.apply();


        diaryNotification(calendar);

    }


    void diaryNotification(Calendar calendar) {
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean dailyNotify = sharedPref.getBoolean(SettingsActivity.KEY_PREF_DAILY_NOTIFICATION, true);
        Boolean dailyNotify = true; // 무조건 알람을 사용

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        // 사용자가 매일 알람을 허용했다면
        if (dailyNotify) {
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }

            // 부팅 후 실행되는 리시버 사용가능하게 설정
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

        }
//        else { //Disable Daily Notifications
//            if (PendingIntent.getBroadcast(this, 0, alarmIntent, 0) != null && alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//                //Toast.makeText(this,"Notifications were disabled",Toast.LENGTH_SHORT).show();
//            }
//            pm.setComponentEnabledSetting(receiver,
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                    PackageManager.DONT_KILL_APP);
//        }
    }


    public void hour(ArrayList<String> fiveZen, int hour) {

        for (String i : fiveZen) {
            Log.i("날라토스", i);
        }

        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);

            // 현재 시간 받기
            String strTime = format.format(cal2.getTime());

            Date regDate;
            regDate = format.parse(strTime);

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
                Log.i("내용물확인0", i);
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
}