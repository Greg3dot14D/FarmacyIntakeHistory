package com.example.greg3d.cureintakedispatcher.framework.factory;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.css.BaseCss;
import com.example.greg3d.cureintakedispatcher.elements.BaseElement;
import com.example.greg3d.cureintakedispatcher.framework.annotations.DateFormated;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;
import com.example.greg3d.cureintakedispatcher.framework.annotations.SetView;
import com.example.greg3d.cureintakedispatcher.framework.interfaces.IDateFormated;
import com.example.greg3d.cureintakedispatcher.framework.interfaces.ISetView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by greg3d on 30.04.17.
 */
public class ActivityFactory {

    public static <T extends View> void InitActivity(T activity, Object conteiner){
        Field[] fields = conteiner.getClass().getDeclaredFields();
        String fieldName = "";
        for(Field field: fields){
            try {
                if(field.isAnnotationPresent(FindBy.class)){
                    int id = field.getAnnotation(FindBy.class).value();
                    if(field.getType().isAnnotationPresent(SetView.class)){
                        try {
                            Object o = field.getType().getConstructor(Object.class).newInstance(activity.findViewById(id));
                            if(field.isAnnotationPresent(DateFormated.class))
                                ((IDateFormated)o).setDateFormat(field.getAnnotation(DateFormated.class).value());
                            field.set(conteiner, o);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }else
                        field.set(conteiner, activity.findViewById(id));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getStackTrace().toString());
            }
        }
    }

    public static <T extends Activity> void InitActivity(T activity, Object conteiner){
        Field[] fields = conteiner.getClass().getDeclaredFields();
        String fieldName = "";
        for(Field field: fields){
            try {
                if(field.isAnnotationPresent(FindBy.class)){
                    int id = field.getAnnotation(FindBy.class).value();
                    if(field.getType().isAnnotationPresent(SetView.class)){
                        try {
                            Object o = field.getType().getConstructor(Object.class).newInstance(activity.findViewById(id));
                            if(field.isAnnotationPresent(DateFormated.class))
                                ((IDateFormated)o).setDateFormat(field.getAnnotation(DateFormated.class).value());
                            field.set(conteiner, o);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }else
                        field.set(conteiner, activity.findViewById(id));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getStackTrace().toString());
            }
        }
    }

    public static <T extends View.OnClickListener> void setListener(T activity, Object conteiner){
        Field[] fields = conteiner.getClass().getDeclaredFields();

        for(Field f: fields){
            try {
                if(f.isAnnotationPresent(FindBy.class)) {
                    if(f.get(conteiner) instanceof BaseElement)
                        ((ISetView) f.get(conteiner)).getView().setOnClickListener(activity);
                    else
                        ((View) f.get(conteiner)).setOnClickListener(activity);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getStackTrace().toString());
            }
        }
    }

    public static <T extends Activity, C extends BaseCss> void InitFonts(T activity, Object conteiner, C font){
        Field[] fields = conteiner.getClass().getDeclaredFields();

        for(Field field: fields){
            try {
                Object o = field.get(conteiner);

                if(o instanceof TextView)
                    InitFonts(activity, (TextView)o, font);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getStackTrace().toString());
            }
        }
    }

    public static <T extends Activity, C extends BaseCss> void InitFonts(T activity, TextView textView, C font){
        Typeface type = Typeface.createFromAsset(activity.getAssets(), font.getFont());
        textView.setTypeface(type);
        textView.setTextSize(font.getTextSize());
        textView.setTextColor(font.getTextColor());
        textView.setShadowLayer(
                font.getShadowRadius(),     //float radius
                font.getShadowDx(),         //float dx
                font.getShadowDy(),         //float dy
                font.getShadowColor()       //int color
        );
    }
}