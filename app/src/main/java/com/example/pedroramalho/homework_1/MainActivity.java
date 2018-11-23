package com.example.pedroramalho.homework_1;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public int color=60;
    public int [] colors ={Color.RED,color= Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.BLACK};
    public  Button  buttons ;
    public Button background;
    public Button font ;
    public Intent intent;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    public int click=0;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(click==0) {
                   backgroundPlayer.pause();
                   click=1;
               }
               else {
                   backgroundPlayer.start();
                   click=0;
               }

            }

        });


         intent = new Intent(getApplicationContext(), SecondActivity.class);
         background = (Button) findViewById(R.id.button1);
         buttons = (Button)findViewById(R.id.button2);
         font = (Button)findViewById(R.id.button3);


        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            intent.putExtra("value",background.getId());
            startActivityForResult(intent,1);
            Toast.makeText(MainActivity.this, "background", Toast.LENGTH_SHORT).show();
            }
        });

        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value",buttons.getId());
                startActivityForResult(intent,2);
                Toast.makeText(MainActivity.this, "buttons", Toast.LENGTH_SHORT).show();
            }
        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value",font.getId());
                startActivityForResult(intent,3);
                Toast.makeText(MainActivity.this, "font", Toast.LENGTH_SHORT).show();
            }
        });


        


    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundPlayer = MediaPlayer.create(this, R.raw.mario);
        backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });
    }


    @Override
    protected void onStop(){
        super.onStop();
        backgroundPlayer.release();
    }

    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data){
        if (resultcode == RESULT_OK && requestcode == 1) {
            color = (int) data.getIntExtra("color",color);
            getWindow().getDecorView().setBackgroundColor(colors[color]);
        }

        if (resultcode == RESULT_OK && requestcode == 2) {


            color = (int) data.getIntExtra("color", color);
            background.setBackgroundColor(colors[color]);
            buttons.setBackgroundColor(colors[color]);
            font.setBackgroundColor(colors[color]);
        }

        if (resultcode == RESULT_OK && requestcode == 3) {


            color = (int) data.getIntExtra("color", color);
            background.setTextColor(colors[color]);
            buttons.setTextColor(colors[color]);
            font.setTextColor(colors[color]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
