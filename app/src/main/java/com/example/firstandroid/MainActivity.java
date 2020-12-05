package com.example.firstandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_SUMMA="summa";
    public static final String EXTRA_TIPS="tips";
    public static final String EXTRA_TOTAL="total";
    private static final int REQUEST_CODE=1;
    public static final String MESSAGE_YES="yes";
    private EditText summaEditText;
    private SeekBar seekBar;
    private TextView textSumma;
    private TextView textTips;
    private TextView textTotal;
    private Button send;
    private TextView result;
    private double summa,total,tips;
    private int procenttips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        summaEditText=findViewById(R.id.summa);
        seekBar=findViewById(R.id.teeps);
        textSumma=findViewById(R.id.textsumma);
        textTips=findViewById(R.id.texttips);
        textTotal=findViewById(R.id.texttotal);
        send=findViewById(R.id.send);
        result=findViewById(R.id.result);
        summaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                summa=Double.parseDouble(s.toString());
                procenttips=seekBar.getProgress();
                UpdateUI();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                summa=Double.parseDouble(summaEditText.getText().toString());
                procenttips=progress;
                UpdateUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra(EXTRA_SUMMA,summa);
                intent.putExtra(EXTRA_TIPS,tips);
                intent.putExtra(EXTRA_TOTAL,total);
               // startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    private void UpdateUI()
    {
        tips=summa*procenttips/100;
        total=summa+tips;
        textSumma.setText(Double.toString(summa));
        textTips.setText(Double.toString(tips));
        textTotal.setText(Double.toString(total));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                result.setText(data.getStringExtra(MESSAGE_YES));
            }
            else if(resultCode==RESULT_CANCELED)
            {
                procenttips=5;
                UpdateUI();
                seekBar.setProgress(procenttips);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}