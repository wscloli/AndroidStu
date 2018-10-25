package com.example.administrator.databasetest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       Context context=getActivity();
        //获取碎片布局
        View view=inflater.inflate(R.layout.login_fragment,container,false);
        //View view=LayoutInflater.from(getActivity()).inflate(R.layout.login_fragment, null);
        Button button=view.findViewById(R.id.button_Click);
        return view;

    }
}
