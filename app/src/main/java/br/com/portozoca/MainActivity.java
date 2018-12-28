package br.com.portozoca;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import br.com.portozoca.core.ActivityConstants;
import br.com.portozoca.core.ColorConstants;
import br.com.portozoca.core.ColorSeekBarEventListener;
import br.com.portozoca.core.ui.SpannableStringBuilder;

public class MainActivity extends AppCompatActivity implements
        ColorConstants, ActivityConstants {

    SeekBar seekBar_Red;
    SeekBar seekBar_Green;
    SeekBar seekBar_Blue;

    TextView tv_selectColor;
    Button bt_ChangeActivity;
    FloatingActionButton fab_Contact;

    Integer colorValue_Red = 0;
    Integer colorValue_Green = 0;
    Integer colorValue_Blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar_Red = findViewById(R.id.sb_Red);
        seekBar_Green = findViewById(R.id.sb_Green);
        seekBar_Blue = findViewById(R.id.sb_Blue);

        tv_selectColor = findViewById(R.id.tv_selectColor);
        bt_ChangeActivity = findViewById(R.id.bt_ChangeActivity);
        fab_Contact = findViewById(R.id.fab_Contact);

        tv_selectColor.setText(SpannableStringBuilder.create(getString(R.string.select_color)).
                size(2f).color(getColor(R.color.colorPrimaryDark)).build());

        seekBar_Red.setProgress(colorValue_Red);
        seekBar_Green.setProgress(colorValue_Green);
        seekBar_Blue.setProgress(colorValue_Blue);

        seekBar_Red.getProgressDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        seekBar_Green.getProgressDrawable().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        seekBar_Blue.getProgressDrawable().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);

        seekBar_Red.setOnSeekBarChangeListener(new ColorSeekBarEventListener(){
            @Override
            public void run(Integer value) {
                colorValue_Red = value;
                updateButtonColor();
            }
        });

        seekBar_Green.setOnSeekBarChangeListener(new ColorSeekBarEventListener() {
            @Override
            public void run(Integer value) {
                colorValue_Green = value;
                updateButtonColor();
            }
        });

        seekBar_Blue.setOnSeekBarChangeListener(new ColorSeekBarEventListener() {
            @Override
            public void run(Integer value) {
                colorValue_Blue = value;
                updateButtonColor();
            }
        });
    }

    public void onClickChangeActivity(View v){
        Intent it = new Intent(this, ChangeColorActivity.class);
        it.putExtra(COLOR_R, colorValue_Red);
        it.putExtra(COLOR_G, colorValue_Green);
        it.putExtra(COLOR_B, colorValue_Blue);
        startActivityForResult(it, ID_MAIN_ACTIVITY);
    }

    public void onClickFloatActionButton(View v){
        Intent it = new Intent(this, ContactActivity.class);
        startActivityForResult(it, ID_MAIN_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ID_CHANGE_COLOR_ACTIVITY){
            updateButtonColor();
        } else if(resultCode == ID_CONTACT_ACTIVITY){
            Snackbar.make(fab_Contact, getString(R.string.thanks_for_using), Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateButtonColor(){
        Integer color = Color.rgb(colorValue_Red, colorValue_Green, colorValue_Blue);
        bt_ChangeActivity.setBackgroundColor(color);
    }

}
