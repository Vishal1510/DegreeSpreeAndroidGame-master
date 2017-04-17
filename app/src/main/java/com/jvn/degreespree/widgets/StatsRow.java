package com.jvn.degreespree.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;
import android.widget.TextView;

import com.jvn.degreespree.models.Player;

/**
 * Created by vishal on 10/5/15.
 */
public class StatsRow extends TableRow {

    TextView name;
    TextView learning;
    TextView craft;
    TextView integrity;
    TextView qualityPoints;


    public StatsRow(Context context) {
        super(context);
        init();
    }

    public StatsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setNameValue(String nameValue) {
        name.setText(nameValue);
    }

    public void setLearningValue(int learningValue) {
        learning.setText(learningValue + "");
    }

    public void setCraftValue(int craftValue) {
        craft.setText(craftValue + "");
    }

    public void setIntegrityValue(int integrityValue) {
        integrity.setText(integrityValue + "");
    }

    public void setQualityPointsValue(int qualityPointsValue) {
        qualityPoints.setText(qualityPointsValue + "");
    }

    private void init() {
        name = new TextView(this.getContext());
        addView(name);
        name.getLayoutParams().height=LayoutParams.MATCH_PARENT;
        name.getLayoutParams().width=100;


        learning = new TextView(this.getContext());
        addView(learning);
        learning.getLayoutParams().height=LayoutParams.MATCH_PARENT;
        learning.getLayoutParams().width=80;


        craft = new TextView(this.getContext());
        addView(craft);
        craft.getLayoutParams().height=LayoutParams.MATCH_PARENT;
        craft.getLayoutParams().width=60;


        integrity = new TextView(this.getContext());
        addView(integrity);
        integrity.getLayoutParams().height=LayoutParams.MATCH_PARENT;
        integrity.getLayoutParams().width=80;


        qualityPoints = new TextView(this.getContext());
        addView(qualityPoints);
        qualityPoints.getLayoutParams().height=LayoutParams.MATCH_PARENT;
        qualityPoints.getLayoutParams().width=100;

    }

    public void update(Player player) {
        setLearningValue(player.getLearning());
        setCraftValue(player.getCraft());
        setIntegrityValue(player.getIntegrity());
        setNameValue(player.getPlayerName());
        setQualityPointsValue(player.getQualityPoints());
    }
}
