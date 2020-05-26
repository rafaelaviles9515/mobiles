package com.example.ac13002utilidades;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.File;

public class VideoActivity extends Activity {
    Button Play;
    Button Stop;
    VideoView video;//VideoView
    MediaController mediacontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_activity);
        video=(VideoView) findViewById(R.id.video);
        File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/Download/","video3.3gp");
        if (f.exists()){
            Uri uri= Uri.fromFile(f);
            video.setVideoURI(uri);
            mediacontrol=new MediaController(this);
            video.setMediaController(mediacontrol);
            mediacontrol.show();
        }
    }
}
