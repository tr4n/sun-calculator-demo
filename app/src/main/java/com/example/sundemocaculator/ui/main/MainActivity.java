package com.example.sundemocaculator.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sundemocaculator.R;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener {


    private static final int BUTTON_ADD = 0;
    private static final int BUTTON_SUB = 1;
    private static final int BUTTON_MUL = 2;
    private static final int BUTTON_DIV = 3;
    private static final int BUTTON_LEFT_BRACKET = 4;
    private static final int BUTTON_RIGHT_BRACKET = 5;
    private static final int BUTTON_DOT = 6;
    private boolean mPrepareClearScreen = false;
    private MainPresenter mMainPresenter;
    private ScrollView mScrollView;
    private TextView mTextC, mTextDel, mTextDiv, mText7, mText8, mText9, mTextMul, mText4, mText5, mText6, mTextSub, mText1, mText2, mText3, mTextAdd, mText0, mTextEqual, mTextScreen, mTextDot, mTextLeftBracket, mTextRightBracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        references();
        setupUI();
    }

    @Override
    public void onClick(View v) {
        String input = mTextScreen.getText().toString();
        switch (v.getId()) {
            case R.id.text_c:
                mMainPresenter.onPressC();
                break;
            case R.id.text_del:
                mMainPresenter.onPressDel(input);
                break;
            case R.id.text_equal:
                mMainPresenter.calculateExpression(input);
                break;
            case R.id.text_add:
                mMainPresenter.onPressSymbol(input, BUTTON_ADD);
                break;
            case R.id.text_sub:
                mMainPresenter.onPressSymbol(input, BUTTON_SUB);
                break;
            case R.id.text_mul:
                mMainPresenter.onPressSymbol(input, BUTTON_MUL);
                break;
            case R.id.text_div:
                mMainPresenter.onPressSymbol(input, BUTTON_DIV);
                break;
            case R.id.text_left_bracket:
                mMainPresenter.onPressSymbol(input, BUTTON_LEFT_BRACKET);
                break;
            case R.id.text_right_bracket:
                mMainPresenter.onPressSymbol(input, BUTTON_RIGHT_BRACKET);
                break;
            case R.id.text_dot:
                mMainPresenter.onPressSymbol(input, BUTTON_DOT);
                break;
            case R.id.text_0:
                mMainPresenter.onPressNumber(input, 0, mPrepareClearScreen);
                break;
            case R.id.text_1:
                mMainPresenter.onPressNumber(input, 1, mPrepareClearScreen);
                break;
            case R.id.text_2:
                mMainPresenter.onPressNumber(input, 2, mPrepareClearScreen);
                break;
            case R.id.text_3:
                mMainPresenter.onPressNumber(input, 3, mPrepareClearScreen);
                break;
            case R.id.text_4:
                mMainPresenter.onPressNumber(input, 4, mPrepareClearScreen);
                break;
            case R.id.text_5:
                mMainPresenter.onPressNumber(input, 5, mPrepareClearScreen);
                break;
            case R.id.text_6:
                mMainPresenter.onPressNumber(input, 6, mPrepareClearScreen);
                break;
            case R.id.text_7:
                mMainPresenter.onPressNumber(input, 7, mPrepareClearScreen);
                break;
            case R.id.text_8:
                mMainPresenter.onPressNumber(input, 8, mPrepareClearScreen);
                break;
            case R.id.text_9:
                mMainPresenter.onPressNumber(input, 9, mPrepareClearScreen);
                break;

        }
    }

    @Override
    public void showResult(String result) {
        mTextScreen.setText(result);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onScrollToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, mScrollView.getBottom());
            }
        });
    }

    @Override
    public void onPrepareClearScreen(boolean state) {
        mPrepareClearScreen = state;
    }

    private void setupUI() {
        mMainPresenter = new MainPresenter(this);
        mScrollView.setOnClickListener(this);
        mTextScreen.setOnClickListener(this);
        mTextC.setOnClickListener(this);
        mTextDel.setOnClickListener(this);
        mTextAdd.setOnClickListener(this);
        mTextSub.setOnClickListener(this);
        mTextDiv.setOnClickListener(this);
        mTextMul.setOnClickListener(this);
        mTextEqual.setOnClickListener(this);
        mTextLeftBracket.setOnClickListener(this);
        mTextRightBracket.setOnClickListener(this);
        mTextDot.setOnClickListener(this);
        mText0.setOnClickListener(this);
        mText1.setOnClickListener(this);
        mText2.setOnClickListener(this);
        mText3.setOnClickListener(this);
        mText4.setOnClickListener(this);
        mText5.setOnClickListener(this);
        mText6.setOnClickListener(this);
        mText7.setOnClickListener(this);
        mText8.setOnClickListener(this);
        mText9.setOnClickListener(this);
    }

    private void references() {
        mScrollView = findViewById(R.id.scroll_view);
        mTextScreen = findViewById(R.id.text_screen);
        mTextC = findViewById(R.id.text_c);
        mTextDel = findViewById(R.id.text_del);
        mTextAdd = findViewById(R.id.text_add);
        mTextSub = findViewById(R.id.text_sub);
        mTextDiv = findViewById(R.id.text_div);
        mTextMul = findViewById(R.id.text_mul);
        mTextEqual = findViewById(R.id.text_equal);
        mTextLeftBracket = findViewById(R.id.text_left_bracket);
        mTextRightBracket = findViewById(R.id.text_right_bracket);
        mTextDot = findViewById(R.id.text_dot);
        mText0 = findViewById(R.id.text_0);
        mText1 = findViewById(R.id.text_1);
        mText2 = findViewById(R.id.text_2);
        mText3 = findViewById(R.id.text_3);
        mText4 = findViewById(R.id.text_4);
        mText5 = findViewById(R.id.text_5);
        mText6 = findViewById(R.id.text_6);
        mText7 = findViewById(R.id.text_7);
        mText8 = findViewById(R.id.text_8);
        mText9 = findViewById(R.id.text_9);
    }


}