package com.example.greg3d.cureintakedispatcher.elements;

import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.framework.annotations.SetView;

/**
 * Created by greg3d on 05.11.17.
 */
@SetView(TextView.class)
public class DoubleView extends BaseElement{

    public DoubleView(Object view) {
        super(view);
    }

    public void setValue(Object value){
        this.view.setText(value.toString());
    }

    public Double getValue(){
        return Double.valueOf(this.view.getText().toString());
    }
}
