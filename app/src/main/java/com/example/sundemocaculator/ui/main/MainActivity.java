package com.example.sundemocaculator.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sundemocaculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    private boolean mResetScreen = false;
    private MainPresenter mMainPresenter;

    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_del)
    TextView textDel;
    @BindView(R.id.text_div)
    TextView textDiv;
    @BindView(R.id.text_7)
    TextView text7;
    @BindView(R.id.text_8)
    TextView text8;
    @BindView(R.id.text_9)
    TextView text9;
    @BindView(R.id.text_mul)
    TextView textMul;
    @BindView(R.id.text_4)
    TextView text4;
    @BindView(R.id.text_5)
    TextView text5;
    @BindView(R.id.text_6)
    TextView text6;
    @BindView(R.id.text_sub)
    TextView textSub;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_2)
    TextView text2;
    @BindView(R.id.text_3)
    TextView text3;
    @BindView(R.id.text_add)
    TextView textAdd;
    @BindView(R.id.text_0)
    TextView text0;
    @BindView(R.id.text_equal)
    TextView textEqual;
    @BindView(R.id.text_screen)
    TextView textScreen;
    @BindView(R.id.text_dot)
    TextView textDot;
    @BindView(R.id.text_left_bracket)
    TextView textLeftBracket;
    @BindView(R.id.text_right_bracket)
    TextView textRightBracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);

    }

    @OnClick({R.id.text_c, R.id.text_del, R.id.text_0, R.id.text_div, R.id.text_7, R.id.text_8, R.id.text_9, R.id.text_mul, R.id.text_4, R.id.text_5, R.id.text_6, R.id.text_sub, R.id.text_1, R.id.text_2, R.id.text_3, R.id.text_add, R.id.text_equal, R.id.text_dot, R.id.text_left_bracket, R.id.text_right_bracket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_c:
                mMainPresenter.onPressC();
                break;
            case R.id.text_del:
                mMainPresenter.onPressDel(textScreen.getText().toString());
                break;
            case R.id.text_equal:
                mMainPresenter.calculateExpression(textScreen.getText().toString());
                break;
            case R.id.text_add:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 0);
                break;
            case R.id.text_sub:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 1);
                break;
            case R.id.text_mul:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 2);
                break;
            case R.id.text_div:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 3);
                break;
            case R.id.text_left_bracket:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 4);
                break;
            case R.id.text_right_bracket:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 5);
                break;
            case R.id.text_dot:
                mMainPresenter.onPressSymbol(textScreen.getText().toString(), 6);
                break;
            case R.id.text_0:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        0,
                        mResetScreen
                );
                break;
            case R.id.text_1:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        1,
                        mResetScreen
                );
                break;
            case R.id.text_2:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        2,
                        mResetScreen
                );
                break;
            case R.id.text_3:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        3,
                        mResetScreen
                );
                break;
            case R.id.text_4:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        4,
                        mResetScreen
                );
                break;
            case R.id.text_5:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        5,
                        mResetScreen
                );
                break;
            case R.id.text_6:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        6,
                        mResetScreen
                );
                break;
            case R.id.text_7:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        7,
                        mResetScreen
                );
                break;
            case R.id.text_8:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        8,
                        mResetScreen
                );
                break;
            case R.id.text_9:
                mMainPresenter.onPressNumber(
                        textScreen.getText().toString(),
                        9,
                        mResetScreen
                );
                break;

        }
    }

    @Override
    public void showResult(String result) {
        textScreen.setText(result);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onScrollToEnd() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, scrollView.getBottom());
            }
        });
    }

    @Override
    public void onPrepareClearScreen(boolean state) {
        mResetScreen = state;
    }
}
