package com.ewoo.wearbusiness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//import com.ewoo.wearbusiness.databinding.ActivityMainBinding;

public class SignInActivity extends Activity {

//    private ActivityMainBinding binding;

    private LinearLayout layoutCode;
    private Button btnFinish;
    private TextView tvCode1, tvCode2, tvCode3, tvCode4, tvCode5, tvCode6;

    private String strCode;
    private ArrayList<TextView> tvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        setContentView(R.layout.activity_sign_in);

        strCode = getIntent().getStringExtra("code");
        tvList = new ArrayList<>();

        layoutCode = findViewById(R.id.layoutCode);

        tvCode1 = findViewById(R.id.tvCode1);
        tvCode2 = findViewById(R.id.tvCode2);
        tvCode3 = findViewById(R.id.tvCode3);
        tvCode4 = findViewById(R.id.tvCode4);
        tvCode5 = findViewById(R.id.tvCode5);
        tvCode6 = findViewById(R.id.tvCode6);

        tvList.add(tvCode1);
        tvList.add(tvCode2);
        tvList.add(tvCode3);
        tvList.add(tvCode4);
        tvList.add(tvCode5);
        tvList.add(tvCode6);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strCode != null && !strCode.isEmpty()) {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.putExtra("code", strCode);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "회사코드 입력", Toast.LENGTH_SHORT).show();
                }
            }
        });

        layoutCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, CodeWriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        separateCode(strCode);

    }

    public void separateCode(String code) {
        if (strCode != null && !strCode.isEmpty()) {
            for (int i = 0; i < strCode.length(); i++) {
                String[] filter = strCode.split("");
                tvList.get(i).setText(filter[i]);
            }
        }
    }
}