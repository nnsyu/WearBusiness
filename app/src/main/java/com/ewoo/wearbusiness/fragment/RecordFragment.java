package com.ewoo.wearbusiness.fragment;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ewoo.wearbusiness.FirebaseManager;
import com.ewoo.wearbusiness.MainActivity;
import com.ewoo.wearbusiness.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordFragment extends Fragment {

    private MainActivity mainActivity;
    private View mView;

    private Button btnRecord, btnPlay;
//    private ActivityMainBinding binding;

    private StorageReference mFirebaseStorage;

    private MediaRecorder mRecorder;
    private String mLocalFilePath = null;

    private MediaPlayer player = null;
    private boolean isRecording = false;
    private boolean isPlay = false;

    private String strCode;
    private Uri mUri;

    public RecordFragment() {

    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mainActivity = (MainActivity) getContext();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if(inflater == null || container == null) return null;

        mView = inflater.inflate(R.layout.fragment_main, null);

        initView();

        return mView;
    }

    private void initView() {

    }

    private void startPlaying(Uri uri) {
        player = new MediaPlayer();
        try {
            player.setDataSource(mainActivity, uri);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mLocalFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        //uploadAudio();
    }

    private void uploadAudio() {

        StorageReference firebasePath = mFirebaseStorage.child(strCode).child("Radio").child("new_radio.3gp");

        Uri localUri = Uri.fromFile(new File(mLocalFilePath));

        firebasePath.putFile(localUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(mainActivity, "전송완료", Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                FirebaseManager.radioRef.child("sendTime").setValue(sdf.format(new Date()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mainActivity, "전송실패", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
    }

    private void downloadAudio() {
        Log.d("로그", "다운로드");
        StorageReference firebasePath = mFirebaseStorage.child(strCode).child("Radio").child("new_radio.3gp");
        firebasePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                startPlaying(uri);
                mUri = uri;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
}
