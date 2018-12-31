package br.com.portozoca;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.portozoca.core.ActivityConstants;
import br.com.portozoca.core.DataConstants;
import br.com.portozoca.core.PermissionConstants;
import br.com.portozoca.core.TextUpdateWatcher;
import br.com.portozoca.core.TravelAdapter;
import br.com.portozoca.core.dao.TravelDAO;
import br.com.portozoca.core.db.Connection;
import br.com.portozoca.core.db.ConnectionFactory;

public class TravelActivity extends Activity implements ActivityConstants, PermissionConstants, DataConstants {

    private EditText tv_Search;
    private ListView lv_travels;

    private Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        conn = ConnectionFactory.getReadConnection(this);

        tv_Search = findViewById(R.id.tv_search);
        lv_travels = findViewById(R.id.lv_travels);

        tv_Search.addTextChangedListener(new TextUpdateWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchString = tv_Search.getText().toString();
                updateList(searchString);
            }
        });

        TravelAdapter travelAdapter = new TravelAdapter(this, new TravelDAO(conn).getCursor());
        lv_travels.setAdapter(travelAdapter);
    }

    public void onClickQrCodeButton(View v){
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_CALL);
        } else{
            doLaunchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_CALL && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            doLaunchCamera();
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.no_permission_camera), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_CODE_CALL){
            if (resultCode == RESULT_OK) {
                String queryString = data.getExtras().getString(DATA_CODE);
                tv_Search.setText(queryString);
                tv_Search.setSelection(queryString.length());
                updateList(queryString);
            } else if (resultCode != RESULT_CANCELED) {
                Toast.makeText(getBaseContext(), getString(R.string.fail_open_camera), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void updateList(CharSequence searchString) {
        String search = searchString.toString();
        Cursor cursor;
        if (searchString != null && !search.trim().isEmpty()) {
            cursor = new TravelDAO(conn).getCursorForSearch(search);
        } else {
            cursor = new TravelDAO(conn).getCursor();
        }
        ((TravelAdapter) lv_travels.getAdapter()).changeCursor(cursor);
    }

    private void doLaunchCamera(){
        Intent it = new Intent(this, ImageActivity.class);
        startActivityForResult(it, QR_CODE_CALL);
    }

}
