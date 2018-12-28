package br.com.portozoca;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.portozoca.core.ActivityConstants;
import br.com.portozoca.core.ColorConstants;
import br.com.portozoca.utils.Strings;

public class ChangeColorActivity extends AppCompatActivity implements
        ColorConstants, ActivityConstants {

    Toast toast;

    Integer cv_Red;
    Integer cv_Green;
    Integer cv_Blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        Intent it = getIntent();

        cv_Red = it.getIntExtra(COLOR_R, 0);
        cv_Green = it.getIntExtra(COLOR_G,0);
        cv_Blue = it.getIntExtra(COLOR_B,0);

        Integer color = Color.rgb(cv_Red, cv_Green, cv_Blue);
        getWindow().getDecorView().setBackgroundColor(color);

        toast = Toast.makeText(this, String.format(getString(R.string.rgb_values),
                Strings.toHex(cv_Red, cv_Green, cv_Blue),
                cv_Red, cv_Blue, cv_Green), Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClickBackButton(View v){
        toast.cancel();
        setResult(ID_CHANGE_COLOR_ACTIVITY);
        finish();
    }

}
