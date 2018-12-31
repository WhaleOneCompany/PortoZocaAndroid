package br.com.portozoca;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import br.com.portozoca.core.ActivityConstants;
import br.com.portozoca.core.DataConstants;
import br.com.portozoca.core.PermissionConstants;

public class ImageActivity extends Activity implements ActivityConstants, DataConstants {

    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final Activity activity = this;

        scannerView = findViewById(R.id.scanner_view);

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> activity.runOnUiThread(() -> returnQrCode(result)));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void returnQrCode(Result result){
        Intent intent = new Intent();
        intent.putExtra(DATA_CODE, result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

}
