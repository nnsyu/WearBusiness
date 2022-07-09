package com.ewoo.wearbusiness.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ewoo.wearbusiness.MainActivity;
import com.ewoo.wearbusiness.R;

public class CodeWriteFragment extends Fragment {

    private MainActivity mainActivity;
    private View mView;

    public CodeWriteFragment() {

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

        mView = inflater.inflate(R.layout.framgnet_code_wirte, null);


        return mView;
    }
}
