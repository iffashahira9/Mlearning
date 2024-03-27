package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoViewBody5 extends AppCompatActivity {
    VideoView videoBody5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_body5);

        VideoView videoBody5 = findViewById(R.id.videoBody5);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.bodyparts;

        Uri uri = Uri.parse(videoPath);
        videoBody5.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoBody5.setMediaController(mediaController);
        mediaController.setAnchorView(videoBody5);
        videoBody5.start();

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(videoViewBody5.this,selectedTopicModule.class));
        finish();
    }
}