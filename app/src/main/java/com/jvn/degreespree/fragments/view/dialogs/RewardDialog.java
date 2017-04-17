package com.jvn.degreespree.fragments.view.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;

/**
 * Created by vishal on 11/7/15.
 */
public class RewardDialog extends DialogFragment {
    private GameController controller;

    private Integer remaining;
    private Integer learning;
    private Integer craft;
    private Integer integrity;
    private Reward reward;

    private boolean learningOn;
    private boolean craftOn;
    private boolean integrityOn;

    RewardCallback callback;

    private TextView mPointsRemaining;
    private Button mSubmit;

    // Learning
    private LinearLayout mLearning;
    private TextView mLearningPoints;
    private Button mLearningUp;
    private Button mLearningDown;

    // Craft
    private LinearLayout mCraft;
    private TextView mCraftPoints;
    private Button mCraftUp;
    private Button mCraftDown;

    // Integrity
    private LinearLayout mIntegrity;
    private TextView mIntegrityPoints;
    private Button mIntegrityUp;
    private Button mIntegrityDown;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.reward_fragment, null);
        builder.setView(v);

        initLearning(v);

        initCraft(v);

        initIntegrity(v);

        mPointsRemaining = (TextView) v.findViewById(R.id.points_remaining);

        mSubmit = (Button) v.findViewById(R.id.ok_button);

        mSubmit.setEnabled(false);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                reward.add(learning, craft, integrity, 0);
                callback.rewardCallback(reward);
            }
        });

        updateDisplays();

        return builder.create();
    }

    private void initLearning(View v) {
        // Learning
        mLearning = (LinearLayout) v.findViewById(R.id.learning_chooser);
        mLearningPoints = (TextView) v.findViewById(R.id.learning_points);
        mLearningUp = (Button) v.findViewById(R.id.learning_up);
        mLearningDown = (Button) v.findViewById(R.id.learning_down);

        mLearningDown.setEnabled(false);
        if (!learningOn) mLearning.setVisibility(LinearLayout.GONE);

        mLearningUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learning++;
                remaining--;
                if (remaining == 0) {
                    disableUp();
                    mSubmit.setEnabled(true);
                }
                mLearningDown.setEnabled(true);
                updateDisplays();
            }
        });

        mLearningDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learning--;
                remaining++;
                if (learning == 0) mLearningDown.setEnabled(false);
                mSubmit.setEnabled(false);
                enableUp();
                updateDisplays();
            }
        });

    }

    private void initCraft(View v) {
        mCraft = (LinearLayout) v.findViewById(R.id.craft_chooser);
        mCraftPoints = (TextView) v.findViewById(R.id.craft_points);
        mCraftUp = (Button) v.findViewById(R.id.craft_up);
        mCraftDown = (Button) v.findViewById(R.id.craft_down);

        mCraftDown.setEnabled(false);
        if (!craftOn) mCraft.setVisibility(LinearLayout.GONE);

        mCraftUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                craft++;
                remaining--;
                if (remaining == 0) {
                    disableUp();
                    mSubmit.setEnabled(true);
                }
                mCraftDown.setEnabled(true);
                updateDisplays();
            }
        });

        mCraftDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                craft--;
                remaining++;
                if (craft == 0) mCraftDown.setEnabled(false);
                mSubmit.setEnabled(false);
                enableUp();
                updateDisplays();
            }
        });
    }

    private void initIntegrity(View v) {
        mIntegrity = (LinearLayout) v.findViewById(R.id.integrity_chooser);
        mIntegrityPoints = (TextView) v.findViewById(R.id.integrity_points);
        mIntegrityUp = (Button) v.findViewById(R.id.integrity_up);
        mIntegrityDown = (Button) v.findViewById(R.id.integrity_down);

        if (!integrityOn) mIntegrity.setVisibility(LinearLayout.GONE);
        mIntegrityDown.setEnabled(false);

        mIntegrityUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrity++;
                remaining--;
                if (remaining == 0) {
                    disableUp();
                    mSubmit.setEnabled(true);
                }
                mIntegrityDown.setEnabled(true);
                updateDisplays();
            }
        });

        mIntegrityDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrity--;
                remaining++;
                if (integrity == 0) mIntegrityDown.setEnabled(false);
                mSubmit.setEnabled(false);
                enableUp();
                updateDisplays();
            }
        });
    }

    private void updateDisplays() {
        mPointsRemaining.setText(remaining + "");
        mLearningPoints.setText(learning + "");
        mCraftPoints.setText(craft + "");
        mIntegrityPoints.setText(integrity + "");
    }

    private void disableUp() {
        mLearningUp.setEnabled(false);
        mCraftUp.setEnabled(false);
        mIntegrityUp.setEnabled(false);
    }

    private void enableUp() {
        mLearningUp.setEnabled(true);
        mCraftUp.setEnabled(true);
        mIntegrityUp.setEnabled(true);
    }

    public void setup(int total, boolean learningOn, boolean craftOn, boolean integrityOn, RewardCallback callback, Reward reward) {
        learning = 0;
        craft = 0;
        integrity = 0;
        this.learningOn = learningOn;
        this.craftOn = craftOn;
        this.integrityOn = integrityOn;
        this.remaining = total;
        this.callback = callback;
        this.reward = reward;
    }

    public void bind(GameController controller) {
        this.controller = controller;
    }

}