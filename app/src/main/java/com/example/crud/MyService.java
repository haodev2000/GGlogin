package com.example.crud;

import static com.example.crud.MyApplication.CHANEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private Noti mNoti;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Hảo","MyService onCreate");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
//
//    public class  Mybinder extends Binder {
//        MyService getService(){return  MyService.this;}
//    }
//
//    public void CHao(){
//        Toast.makeText(MyService.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//    }
//    public void RHao(){
//        Toast.makeText(MyService.this, "Đọc thành công", Toast.LENGTH_SHORT).show();
//    }
//    public void UHao(){
//        Toast.makeText(MyService.this, "Update thành công", Toast.LENGTH_SHORT).show();
//    }
//    public void DHao(){
//        Toast.makeText(MyService.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Noti song = (Noti) bundle.get("object_song");

            if(song != null) {
                mNoti = song;
                sendNotification(song);
            }
        }
        return START_NOT_STICKY;
    }


    private void sendNotification(Noti song) {
        Intent intent = new Intent(this, MainActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification);
        remoteViews.setTextViewText(R.id.tv_TieuDe,song.getTv_tieude());


        // CHANEL_ID ghi r nhấn alt enter để import từ MyAplication
        Notification notification = new NotificationCompat.Builder(this,CHANEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Hảo","MyService onDestroy");
    }
}
