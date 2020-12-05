package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView resSum;
    private TextView resTips;
    private TextView resTotal;
    private Button OK;
    private Button Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resSum=findViewById(R.id.resSum);
        resTips=findViewById(R.id.resTip);
        resTotal=findViewById(R.id.resTotal);
        OK=findViewById(R.id.OK);
        Cancel=findViewById(R.id.Cancel);
        Bundle arguments=getIntent().getExtras();
        if(arguments!=null)
        {
            double sum=arguments.getDouble(MainActivity.EXTRA_SUMMA,0);
            resSum.setText(getResources().getString(R.string.sumRes)+Double.toString(sum));
            double tips=arguments.getDouble(MainActivity.EXTRA_TIPS,0);
            resTips.setText(getResources().getString(R.string.tipRes)+Double.toString(tips));
            double total=arguments.getDouble(MainActivity.EXTRA_TOTAL,0);
            resTotal.setText(getResources().getString(R.string.totalRes)+Double.toString(total));
        }
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                data.putExtra(MainActivity.MESSAGE_YES,getResources().getString(R.string.yes));
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}