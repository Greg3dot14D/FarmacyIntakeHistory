package com.example.greg3d.cureintakedispatcher.dialog.calc;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;

import java.lang.reflect.Field;

/**
 * Created by greg3d on 29.10.17.
 */

public class CalcDialog extends DialogFragment implements View.OnClickListener{

    private Controls controls;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.calcdialog, null, false);
        controls = new Controls();
        ViewFactory.InitView(v, controls);
        ActivityFactory.setListener(this, controls);
        return  v;
    }

    @Override
    public void onClick(View view) {
        String text = controls.b_text_TextView.getText().toString();

        Field [] fields = controls.getClass().getFields();

        for(Field f: fields){
            try {
                if(f.get(controls).equals(view)) {
                    String name = f.getAnnotation(Name.class).value();
                    if("c".equals(name))
                        text = "";
                    else
                        text += name;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        controls.b_text_TextView.setText(text);
        //Show.show(view.getContext(), text);
    }
}
