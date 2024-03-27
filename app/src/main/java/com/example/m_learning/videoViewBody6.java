package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoViewBody6 extends AppCompatActivity {
    VideoView videoBody6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_body6);

        VideoView videoBody6 = findViewById(R.id.videoBody6);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.bodysense;

        Uri uri = Uri.parse(videoPath);
        videoBody6.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoBody6.setMediaController(mediaController);
        mediaController.setAnchorView(videoBody6);
        videoBody6.start();

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(videoViewBody6.this,selectedTopicModule6.class));
        finish();
    }

}