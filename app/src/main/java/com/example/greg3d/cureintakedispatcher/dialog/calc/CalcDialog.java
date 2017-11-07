package com.example.greg3d.cureintakedispatcher.dialog.calc;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.commands.IObjectCommand;
import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * Created by greg3d on 29.10.17.
 */

public class CalcDialog extends DialogFragment implements View.OnClickListener{

    private Controls controls;
    private IObjectCommand command;

    public CalcDialog setCommand(IObjectCommand command){
        this.command = command;
        return this;
    }

    public static void show(Activity activity, IObjectCommand command){
        CalcDialog dialog = new CalcDialog().setCommand(command);
        dialog.show(activity.getFragmentManager(), "tag");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        getDialog().setTitle("Set Price");

        View v = inflater.inflate(R.layout.calcdialog, null, false);
        controls = new Controls();
        ViewFactory.InitView(v, controls);
        ActivityFactory.setListener(this, controls);
        return  v;
    }

    @Override
    public void onClick(View view) {
        String result = controls.b_text_TextView.getText().toString();

        Field [] fields = controls.getClass().getFields();

        for(Field f: fields){
            try {
                if(f.get(controls).equals(view)) {
                    String name = f.getAnnotation(Name.class).value();
                    if("c".equals(name))
                        result = "0";
                    else if("del".equals(name)) {
                        if(result.length() == 1)
                            result = "0";
                        else
                            result = result.substring(0, result.length()-1);
                    }
                    else if("Ok".equals(name)){
                        this.command.execute(result);
                        this.onDestroyView();
                        return;
                    }
                    else if("0".equals(result))
                        result = name;
                    else
                        result += name;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        controls.b_text_TextView.setText(getValidPrice(result));
    }
    private static String getValidPrice(String value){
        String regexp = "(([0-9]){1,9}|(([0-9]){1,9}\\.([0-9]){0,2}))";
        if(Pattern.matches(regexp, value))
            return value;
        return getValidPrice(value.substring(0, value.length()-1));
    }
}
