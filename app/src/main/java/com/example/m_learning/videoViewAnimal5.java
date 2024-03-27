package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoViewAnimal5 extends AppCompatActivity {
    VideoView videoAnimal5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_animal5);

        VideoView videoAnimal5 = findViewById(R.id.videoAnimal5);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.animal2;

        Uri uri = Uri.parse(videoPath);
        videoAnimal5.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoAnimal5.setMediaController(mediaController);
        mediaController.setAnchorView(videoAnimal5);
        videoAnimal5.start();
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(videoViewAnimal5.this,selectedTopicModule.class));
        finish();
    }
}
