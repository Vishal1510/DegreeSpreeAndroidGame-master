package com.jvn.degreespree.fragments.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;

/**
 * Created by vishal on 9/23/15.
 */
public class StartFragment extends Fragment {

    private GameController controller;

    public void bind(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fragment, container, false);
        return v;
    }

}
