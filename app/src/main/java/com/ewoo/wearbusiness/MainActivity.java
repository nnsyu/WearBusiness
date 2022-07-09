package com.ewoo.wearbusiness;

import android.Manifest;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import com.ewoo.wearbusiness.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void changeFragment(int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (id) {
            case Define.FRAGMENT_MAIN:
                ft.replace(R.id.layout_frame, new MainFragment());
                Toast.makeText(this, "메인이동", Toast.LENGTH_SHORT).show();
                break;

            case Define.FRAGMENT_MENU_PLAY:
                ft.replace(R.id.layout_frame, new MenuPlayFragment());
                Toast.makeText(this, "메뉴재생이동", Toast.LENGTH_SHORT).show();
                break;

            case Define.FRAGMENT_MENU_EDIT:
                ft.replace(R.id.layout_frame, new MenuEditFragment());
                Toast.makeText(this, "메뉴작성이동", Toast.LENGTH_SHORT).show();
                break;

            case Define.FRAGMENT_SLIDE_EDIT:
                ft.replace(R.id.layout_frame, new SlideEditFragment());
                Toast.makeText(this, "슬라이드작성이동", Toast.LENGTH_SHORT).show();
                break;

            case Define.FRAGMENT_SETTING:
                ft.replace(R.id.layout_frame, new SettingFragment());
                Toast.makeText(this, "설정이동", Toast.LENGTH_SHORT).show();
                break;
        }

        ft.commit();
        Define.setFragmentId(id);
    }
}