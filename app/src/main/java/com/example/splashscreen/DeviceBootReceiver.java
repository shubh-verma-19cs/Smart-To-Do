package com.example.splashscreen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DeviceBootReceiver extends BroadcastReceiver{
    public static final String ACTION_ALARM = "notification.ALARM";
    BottomSheet b;
//    static String currentDateTime;

//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
//    String mail;
//    DatabaseReference databaseReference;

    @Override
    public void onReceive(Context context, Intent intent) {
//        b = new BottomSheet();
//        currentDateTime = b.sendCurrentDate();
//        firebaseAuth= FirebaseAuth.getInstance();
//        firebaseUser= firebaseAuth.getCurrentUser();
//        mail= firebaseUser.getUid();
//        Log.d("AAAAAAAAA",currentDateTime);
//        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(mail).child(currentDateTime).child("TaskName");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String taskName = snapshot.getValue().toString();
////                Log.d("MAMAMMA",taskName);
//                Toast.makeText(context, taskName, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        if (ACTION_ALARM.equals(intent.getAction())) {
            Toast.makeText(context, ACTION_ALARM, Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifID")
                    .setSmallIcon(R.drawable.task)
                    .setContentTitle("Task Reminder")
                    .setContentText("Task Desc")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(200, builder.build());






//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification notification = intent.getParcelableExtra( NOTIFICATION ) ;
//            if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
//                int importance = NotificationManager. IMPORTANCE_HIGH ;
//                NotificationChannel notificationChannel = new NotificationChannel( CH_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
//                assert notificationManager != null;
//                notificationManager.createNotificationChannel(notificationChannel) ;
//            }
//            int id = intent.getIntExtra( NOTIFICATION_ID , 0 ) ;
//            assert notificationManager != null;
//            notificationManager.notify(id , notification) ;

        }




//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            // on device boot compelete, reset the alarm
//            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
//
//            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(System.currentTimeMillis());
//            calendar.set(Calendar.HOUR_OF_DAY, 19);
//            calendar.set(Calendar.MINUTE, 29);
//            calendar.set(Calendar.SECOND, 1);
//
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                    AlarmManager.INTERVAL_DAY, pendingIntent);
//        }
    }
}
