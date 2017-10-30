package com.example.greg3d.cureintakedispatcher.dialog.calc;

import android.widget.Button;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;
import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

/**
 * Created by greg3d on 30.10.17.
 */

public class Controls {

    @Name("0")
    @FindBy(R.id.calc_0)
    public Button b_0_Button;

    @Name("1")
    @FindBy(R.id.calc_1)
    public Button b_1_Button;

    @Name("2")
    @FindBy(R.id.calc_2)
    public Button b_2_Button;

    @Name("3")
    @FindBy(R.id.calc_3)
    public Button b_3_Button;

    @Name("4")
    @FindBy(R.id.calc_4)
    public Button b_4_Button;

    @Name("5")
    @FindBy(R.id.calc_5)
    public Button b_5_Button;

    @Name("6")
    @FindBy(R.id.calc_6)
    public Button b_6_Button;

    @Name("7")
    @FindBy(R.id.calc_7)
    public Button b_7_Button;

    @Name("8")
    @FindBy(R.id.calc_8)
    public Button b_8_Button;

    @Name("9")
    @FindBy(R.id.calc_9)
    public Button b_9_Button;

    @Name(".")
    @FindBy(R.id.calc_dot)
    public Button b_dot_Button;

    @Name("c")
    @FindBy(R.id.calc_c)
    public Button b_c_Button;

    @Name("del")
    @FindBy(R.id.calc_del)
    public Button b_del_Button;

    @Name("Ok")
    @FindBy(R.id.calc_Ok)
    public Button b_Ok_Button;

    @FindBy(R.id.calc_text)
    public TextView b_text_TextView;
}
