package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
    }
    private void UpdateUI()
    {
        tips=summa*procenttips/100;
        total=summa+tips;
        textSumma.setText(Double.toString(summa));
        textTips.setText(Double.toString(tips));
        textTotal.setText(Double.toString(total));
    }
}