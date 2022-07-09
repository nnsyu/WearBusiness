package com.ewoo.wearbusiness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CodeWriteActivity extends Activity {

    private InputMethodManager imm;
    private EditText etWrite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_wirte);
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        etWrite = findViewById(R.id.a_code_write_et_write);
        etWrite.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("로그", "onKey");
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (etWrite.getText().length() != 6) {
                        Toast.makeText(CodeWriteActivity.this, "6자리 입력", Toast.LENGTH_SHORT).show();
                        showKeyboard();
                        return true;
                    }
                    Intent intent = new Intent(CodeWriteActivity.this, SignInActivity.class);
                    intent.putExtra("code", etWrite.getText().toString());
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        showKeyboard();
    }

    private void showKeyboard() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                if(etWrite.requestFocus()) {
                    if(imm != null) {
                        imm.showSoftInput(etWrite, InputMethodManager.SHOW_IMPLICIT);
                        imm.toggleSoftInput(0, 0);
                    } else {
                        Log.d("로그", "키보드를 열 수 없습니다.");
                    }
                }
            }

        }
    },500);
    }
}
