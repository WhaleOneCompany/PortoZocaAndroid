package br.com.portozoca;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.portozoca.core.ActivityConstants;


public class MainActivity extends Activity implements ActivityConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConference(View v){
        Intent it = new Intent(this, TravelActivity.class);
        startActivityForResult(it, ID_MAIN_ACTIVITY);
    }

    public void onClickSynchronize(View v){
        Toast.makeText(this, getString(R.string.synchronize_success), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ID_CONFERENCE_ACTIVITY){
            onResultConferenceActivity();
        }
    }

    private void onResultConferenceActivity(){
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT)
                .show();
    }

}
