package com.example.greg3d.cureintakedispatcher.elements;

import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.framework.interfaces.ISetView;

/**
 * Created by greg3d on 05.11.17.
 */

public class BaseElement <T extends TextView> implements ISetView {
    protected T view;

    public BaseElement(Object view) {
        this.view = (T) view;
    }

    @Override
    public void setView(View view) {
        this.view = (T)view;
    }

    @Override
    public View getView() {
        return this.view;
    }
}
