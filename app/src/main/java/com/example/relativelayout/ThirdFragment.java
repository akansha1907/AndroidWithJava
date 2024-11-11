package com.example.relativelayout;

import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


import java.io.IOException;

public class ThirdFragment extends Fragment {
    Button btn,btn2,btn3;
   VideoView videoView;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_third, container, false);
        btn = view.findViewById(R.id.play_btn);
        btn2 = view.findViewById(R.id.stop_btn);
        btn3 = view.findViewById(R.id.pause_btn);
        videoView = view.findViewById(R.id.video_view);
        MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String pathA = "android.resource://"+requireActivity().getApplicationContext().getPackageName()+"/raw/song1";
        Uri audioUri = Uri.parse(pathA);
        //we can direct set pathA to mp but setting by uri is best way
        try {
            mp.setDataSource(requireContext().getApplicationContext(),audioUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                mp.seekTo(0);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });
        String videoPath = "android.resource://"+requireActivity().getApplicationContext().getPackageName()+"/raw/video";
        Uri uriVideo = Uri.parse(videoPath);
        Log.d("URI",uriVideo.toString());
       // videoView.setVideoPath(videoPath);            //we can also use this way to set video without uri
        videoView.setVideoURI(uriVideo);                //but this way is recommended
        videoView.start();              //without this line video will visible but not play to play video this line is important

        //Media controller is used to show pause, resume and seekbar on video
        MediaController controller = new MediaController(requireActivity());
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);

        return  view;
    }
}