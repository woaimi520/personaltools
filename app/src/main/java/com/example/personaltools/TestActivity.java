package com.example.personaltools;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.button_open)
    Button button_open;
    @BindView(R.id.button_close)
    Button button_close;
    @BindView(R.id.button_status)
    Button button_status;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        ButterKnife.bind(this);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText("lcj");
//            }
//        }, 1 * 60 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        RefWatcher refWatcher= MyAppllication.getRefWatcher(this);
//        refWatcher.watch(this);

    }

    @OnClick({R.id.button_open,R.id.button_close,R.id.button_status})
    public void OnClick(View view){

             switch(view.getId()){
                 case R.id.button_open:
                     FlashlightControlManager.getInstance().openFlashlight();

                     break;
                 case R.id.button_close:
                     FlashlightControlManager.getInstance().closeFlashlight();
                     break;
                 case R.id.button_status:
                     boolean isOff = false;
                     isOff= FlashlightControlManager.getInstance().isFlashOff();
                     mTextView.setText("闪光灯="+(isOff?"关闭":"打开"));
                     break;

             }
    }


}
