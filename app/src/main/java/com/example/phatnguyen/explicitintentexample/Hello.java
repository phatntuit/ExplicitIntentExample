package com.example.phatnguyen.explicitintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hello extends AppCompatActivity {

    private String hoten;
    private String namsinh;
    private String hello;
    private TextView tvhello;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        tvhello = (TextView)findViewById(R.id.tvhello);
        btnBack = (Button)findViewById(R.id.btnBack);

        //Intent truyen sang
        Intent intent = this.getIntent();
        this.hoten = intent.getStringExtra("hoten");
        this.namsinh = intent.getStringExtra("namsinh");
        this.hello = "Hello " + hoten + "! born in "+namsinh;
        tvhello.setText(hello);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
    }
    // Khi Activity này hoàn thành,
    // có thể cần gửi phản hồi gì đó về cho Activity đã gọi nó
    @Override
    public void finish() {

        // Chuẩn bị dữ liệu Intent.
        Intent data = new Intent();
        data.putExtra("feedback","this is feedback from Hello Activity : "+hello);
        // Activity đã hoàn thành OK, trả về dữ liệu.
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
