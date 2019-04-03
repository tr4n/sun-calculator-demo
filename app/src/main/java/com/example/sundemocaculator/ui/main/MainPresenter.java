package com.example.sundemocaculator.ui.main;

import android.util.Log;
import android.widget.Toast;

import com.example.sundemocaculator.util.NumberUtils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    private static final String OPERATORS = "+-*/().";
    private final MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void calculateExpression(String input) {
        if (input.length() == 0) return;
        Expression expression = new ExpressionBuilder(input).build();
        if (expression.validate().isValid()) {
            if (!(expression.evaluate() + "").contains("Infinity")) {
                mView.showResult(NumberUtils.getResult(expression.evaluate()));
                mView.onPrepareClearScreen(true);
                return;
            }
            Toast.makeText(mView.getContext(), "Infinity", Toast.LENGTH_SHORT).show();
            mView.onPrepareClearScreen(false);
            return;
        }
        Toast.makeText(mView.getContext(), "Error Expression", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showResult: " + "error expression");
        mView.onPrepareClearScreen(false);

    }

    @Override
    public void onPressDel(String input) {
        mView.showResult(input.length() > 0 ? input.substring(0, input.length() - 1) : "");

    }

    @Override
    public void onPressC() {
        mView.showResult("");
    }

    @Override
    public void onPressNumber(String input, int number, boolean reset) {
        if (reset) {
            mView.showResult("");
        }
        mView.showResult(input + number);
        mView.onScrollToEnd();
        mView.onPrepareClearScreen(false);
    }

    @Override
    public void onPressSymbol(String input, int index) {
        char operator = OPERATORS.charAt(index);
        mView.showResult(input + operator);
        mView.onScrollToEnd();
        mView.onPrepareClearScreen(false);

    }
}
