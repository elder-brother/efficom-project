package com.efficom.efficommap.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.efficom.efficommap.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jourda_c on 2/20/17.
 */

public class HelloWorld extends AppCompatActivity {
    @BindView(R.id.button) Button button;

    @Override protected void onCreate(
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helloworld);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
