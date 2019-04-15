// Generated code from Butter Knife. Do not modify!
package com.example.sundemocaculator.ui.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.sundemocaculator.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165339;

  private View view2131165340;

  private View view2131165341;

  private View view2131165335;

  private View view2131165336;

  private View view2131165337;

  private View view2131165345;

  private View view2131165332;

  private View view2131165333;

  private View view2131165334;

  private View view2131165348;

  private View view2131165329;

  private View view2131165330;

  private View view2131165331;

  private View view2131165338;

  private View view2131165328;

  private View view2131165343;

  private View view2131165342;

  private View view2131165344;

  private View view2131165346;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scroll_view, "field 'scrollView'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.text_c, "field 'textC' and method 'onViewClicked'");
    target.textC = Utils.castView(view, R.id.text_c, "field 'textC'", TextView.class);
    view2131165339 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_del, "field 'textDel' and method 'onViewClicked'");
    target.textDel = Utils.castView(view, R.id.text_del, "field 'textDel'", TextView.class);
    view2131165340 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_div, "field 'textDiv' and method 'onViewClicked'");
    target.textDiv = Utils.castView(view, R.id.text_div, "field 'textDiv'", TextView.class);
    view2131165341 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_7, "field 'text7' and method 'onViewClicked'");
    target.text7 = Utils.castView(view, R.id.text_7, "field 'text7'", TextView.class);
    view2131165335 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_8, "field 'text8' and method 'onViewClicked'");
    target.text8 = Utils.castView(view, R.id.text_8, "field 'text8'", TextView.class);
    view2131165336 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_9, "field 'text9' and method 'onViewClicked'");
    target.text9 = Utils.castView(view, R.id.text_9, "field 'text9'", TextView.class);
    view2131165337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_mul, "field 'textMul' and method 'onViewClicked'");
    target.textMul = Utils.castView(view, R.id.text_mul, "field 'textMul'", TextView.class);
    view2131165345 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_4, "field 'text4' and method 'onViewClicked'");
    target.text4 = Utils.castView(view, R.id.text_4, "field 'text4'", TextView.class);
    view2131165332 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_5, "field 'text5' and method 'onViewClicked'");
    target.text5 = Utils.castView(view, R.id.text_5, "field 'text5'", TextView.class);
    view2131165333 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_6, "field 'text6' and method 'onViewClicked'");
    target.text6 = Utils.castView(view, R.id.text_6, "field 'text6'", TextView.class);
    view2131165334 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_sub, "field 'textSub' and method 'onViewClicked'");
    target.textSub = Utils.castView(view, R.id.text_sub, "field 'textSub'", TextView.class);
    view2131165348 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_1, "field 'text1' and method 'onViewClicked'");
    target.text1 = Utils.castView(view, R.id.text_1, "field 'text1'", TextView.class);
    view2131165329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_2, "field 'text2' and method 'onViewClicked'");
    target.text2 = Utils.castView(view, R.id.text_2, "field 'text2'", TextView.class);
    view2131165330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_3, "field 'text3' and method 'onViewClicked'");
    target.text3 = Utils.castView(view, R.id.text_3, "field 'text3'", TextView.class);
    view2131165331 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_add, "field 'textAdd' and method 'onViewClicked'");
    target.textAdd = Utils.castView(view, R.id.text_add, "field 'textAdd'", TextView.class);
    view2131165338 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_0, "field 'text0' and method 'onViewClicked'");
    target.text0 = Utils.castView(view, R.id.text_0, "field 'text0'", TextView.class);
    view2131165328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_equal, "field 'textEqual' and method 'onViewClicked'");
    target.textEqual = Utils.castView(view, R.id.text_equal, "field 'textEqual'", TextView.class);
    view2131165343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.textScreen = Utils.findRequiredViewAsType(source, R.id.text_screen, "field 'textScreen'", TextView.class);
    view = Utils.findRequiredView(source, R.id.text_dot, "field 'textDot' and method 'onViewClicked'");
    target.textDot = Utils.castView(view, R.id.text_dot, "field 'textDot'", TextView.class);
    view2131165342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_left_bracket, "field 'textLeftBracket' and method 'onViewClicked'");
    target.textLeftBracket = Utils.castView(view, R.id.text_left_bracket, "field 'textLeftBracket'", TextView.class);
    view2131165344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_right_bracket, "field 'textRightBracket' and method 'onViewClicked'");
    target.textRightBracket = Utils.castView(view, R.id.text_right_bracket, "field 'textRightBracket'", TextView.class);
    view2131165346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scrollView = null;
    target.textC = null;
    target.textDel = null;
    target.textDiv = null;
    target.text7 = null;
    target.text8 = null;
    target.text9 = null;
    target.textMul = null;
    target.text4 = null;
    target.text5 = null;
    target.text6 = null;
    target.textSub = null;
    target.text1 = null;
    target.text2 = null;
    target.text3 = null;
    target.textAdd = null;
    target.text0 = null;
    target.textEqual = null;
    target.textScreen = null;
    target.textDot = null;
    target.textLeftBracket = null;
    target.textRightBracket = null;

    view2131165339.setOnClickListener(null);
    view2131165339 = null;
    view2131165340.setOnClickListener(null);
    view2131165340 = null;
    view2131165341.setOnClickListener(null);
    view2131165341 = null;
    view2131165335.setOnClickListener(null);
    view2131165335 = null;
    view2131165336.setOnClickListener(null);
    view2131165336 = null;
    view2131165337.setOnClickListener(null);
    view2131165337 = null;
    view2131165345.setOnClickListener(null);
    view2131165345 = null;
    view2131165332.setOnClickListener(null);
    view2131165332 = null;
    view2131165333.setOnClickListener(null);
    view2131165333 = null;
    view2131165334.setOnClickListener(null);
    view2131165334 = null;
    view2131165348.setOnClickListener(null);
    view2131165348 = null;
    view2131165329.setOnClickListener(null);
    view2131165329 = null;
    view2131165330.setOnClickListener(null);
    view2131165330 = null;
    view2131165331.setOnClickListener(null);
    view2131165331 = null;
    view2131165338.setOnClickListener(null);
    view2131165338 = null;
    view2131165328.setOnClickListener(null);
    view2131165328 = null;
    view2131165343.setOnClickListener(null);
    view2131165343 = null;
    view2131165342.setOnClickListener(null);
    view2131165342 = null;
    view2131165344.setOnClickListener(null);
    view2131165344 = null;
    view2131165346.setOnClickListener(null);
    view2131165346 = null;
  }
}
