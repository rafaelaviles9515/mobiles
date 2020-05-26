package com.example.ac13002utilidades;

import java.util.HashMap;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class TextToSpeechActivity extends Activity {
    TextToSpeech tts;
    TextView Texto;
    Button BtnPlay;
    Button BtnSave;
    private int numarch=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_text_to_speech_activity);
        Texto=(TextView) findViewById(R.id.edtText2Speech);
        BtnPlay = (Button) findViewById(R.id.btnText2SpeechPlay);
        BtnSave = (Button) findViewById(R.id.btnText2SpeechSave);
        tts = new TextToSpeech(this,OnInit);
        BtnPlay.setOnClickListener(onClick);
        BtnSave.setOnClickListener(onClick);
    }
    TextToSpeech.OnInitListener OnInit = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if (TextToSpeech.SUCCESS==status){
                tts.setLanguage(new Locale("spa","ESP"));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "TTS no disponible",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
    View.OnClickListener onClick=new View.OnClickListener() {
        @SuppressLint("SdCardPath")
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId()==R.id.btnText2SpeechPlay){
                tts.speak(Texto.getText().toString(), TextToSpeech.QUEUE_ADD, null);
            }
            if (v.getId()==R.id.btnText2SpeechSave){
                tts.speak(Texto.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                HashMap<String, String> myHashRender = new HashMap<String, String>();
                String Texto_tts =Texto.getText().toString();
                //Cada vez que guarde hara un nuevo archivo wav
                numarch=numarch+1;
                String destFileName =
                        Environment.getExternalStorageDirectory().getAbsolutePath() +
                                "/Download/tts"+numarch+".wav";
                myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,Texto_tts);
                tts.synthesizeToFile(Texto_tts, myHashRender, destFileName);
            }
        }
    };
    public void onDestroy(){
        tts.shutdown();
        super.onDestroy();
    }
}