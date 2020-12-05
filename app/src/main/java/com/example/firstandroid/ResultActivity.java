package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView resSum;
    private TextView resTips;
    private TextView resTotal;
    private Button OK;
    private Button Cancel;
    private EditText Message;
    private EditText tel;
    private Button Phone;
    private Button Mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resSum=findViewById(R.id.resSum);
        resTips=findViewById(R.id.resTip);
        resTotal=findViewById(R.id.resTotal);
        OK=findViewById(R.id.OK);
        Cancel=findViewById(R.id.Cancel);
        Message=findViewById(R.id.sendMessage);
        Phone=findViewById(R.id.call);
        Mail=findViewById(R.id.sendMail);
        tel=findViewById(R.id.sendPhone);
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
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDial="tel:"+tel.getText().toString();
//                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));
            }
        });
        Mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSms="smsto:"+tel.getText().toString();
                String massegeText=Message.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.parse(toSms));
                intent.putExtra("sms_body",massegeText);
                startActivity(intent);
            }
        });
    }
}