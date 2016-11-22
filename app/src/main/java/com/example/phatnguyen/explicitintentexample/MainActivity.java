package com.example.phatnguyen.explicitintentexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     *
     * Intent tường minh (Explicit intents): Là những ý định (intent) chỉ định rõ ràng tên của các thành phần mục tiêu để xử lý
     * trong đó, trường mục tiêu (tùy chọn) được sét một giá trị cụ thể thông qua các phương thức setComponent() hoặc setClass().
     */
    public static final int REQUEST_CODE = 200;
    TextView tvfeedback ;
    EditText ethoten,etnamsinh;
    Button btnsubmit,btncallme ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mapping
        tvfeedback = (TextView) findViewById(R.id.tvfeedback);
        ethoten = (EditText)findViewById(R.id.ethoten);
        etnamsinh = (EditText)findViewById(R.id.etnamsinh);
        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btncallme = (Button) findViewById(R.id.btncallme);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayHello(v);
            }
        });
        btncallme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*Khong tuomg minh*/
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:0985305846"));
                startActivity(call);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK
                && requestCode == REQUEST_CODE){
            String feedback = data.getStringExtra("feedback");
            this.tvfeedback.setText(feedback);
        }
        else{
            Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_LONG).show();
        }
    }

    public void sayHello(View v){

        String hoten = ethoten.getText().toString();
        String namsinh = etnamsinh.getText().toString();
        if(hoten.length()==0){
            Toast.makeText(
                    MainActivity.this,
                    "Nhap vao TEN",
                    Toast.LENGTH_LONG
            ).show();
        }
        else{
            /*Tuong  minh*/
            Intent intent = new Intent(MainActivity.this,Hello.class);
            intent.putExtra("hoten", hoten);
            intent.putExtra("namsinh", namsinh);

            // Yêu cầu start Activity chỉ định trong Intent.
            // (Gửi yêu cầu mà không cần phản hồi).
            // this.startActivity(intent);

            // Yêu cầu start Activity và chờ phản hồi.
            this.startActivityForResult(intent,REQUEST_CODE);
        }

    }
}
