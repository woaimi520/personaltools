package com.example.personaltools;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView mTextView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("lcj");
            }
        }, 1 * 60 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        RefWatcher refWatcher= Myappllication.getRefWatcher(this);
//        refWatcher.watch(this);

    }
}
