package com.pollub.app71;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView drag, drop;
    TextView total, success;
    int totalCount, successCount;
    MediaPlayer mp;
    AudioAttributes audioAttributes;
    SoundPool mySounds;
    int win_s, lose_s;

    int detect=0;




    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drag = findViewById(R.id.drag);
        drop = findViewById(R.id.drop);
        total = findViewById(R.id.total);
        success = findViewById(R.id.success);
        audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        mySounds = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();
        win_s = mySounds.load(this, R.raw.win, 1);
        lose_s = mySounds.load(this, R.raw.lose, 1);



        drag.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("NewApi")
            public boolean onTouch(View v, MotionEvent arg1) {

                // newPlainText(CharSequence label, CharSequence text)
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow =
                        new View.DragShadowBuilder(drag);
                // startDragAndDrop(data to be dragged, drag shadow, local data, flags)
                v.startDragAndDrop(data, shadow, null, 0);
                return true;
            }
        });

        drop.setOnDragListener(new View.OnDragListener() {
            @SuppressLint("SetTextI18n")
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_EXITED:
                    {


                        break;
                    }
                    case DragEvent.ACTION_DRAG_ENTERED:
                    {

                        break;
                    }
                    case DragEvent.ACTION_DRAG_LOCATION:
                    {


                        break;
                    }
                    case DragEvent.ACTION_DROP:
                    {
                        mySounds.play(win_s, 0.9f, 0.9f, 1, 0, 1);

                        successCount = successCount + 1;
                        success.setText("Successful drops: " + successCount);
                        detect=1;


                        break;
                    }
                    case DragEvent.ACTION_DRAG_ENDED:
                    {
                        if (detect !=1){
                            mySounds.play(lose_s, 0.9f, 0.9f, 1, 0, 1);

                        }

                        totalCount = totalCount + 1;
                        total.setText("Total drops: " + totalCount);

                        break;
                    }


                }
                return true;
            }
        });

    }
}