package com.jvn.degreespree.widgets;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;

/**
 * Created by vishal on 10/5/15.
 */
public class PlayerIcon extends TextView {

    public PlayerIcon(Context context) {
        super(context);
    }

    public PlayerIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setup(Player player) {
        this.setBackground(getResources().getDrawable(R.drawable.text_box_border));
        this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        this.setText(player.getPlayerName());
        this.setTextColor(Color.parseColor("#CC0000"));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5, 5, 5, 5);
        this.setLayoutParams(params);
    }
}
