package br.com.portozoca;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.portozoca.core.TextUpdateWatcher;
import br.com.portozoca.core.TravelAdapter;
import br.com.portozoca.core.dao.TravelDAO;
import br.com.portozoca.core.db.Connection;
import br.com.portozoca.core.db.ConnectionFactory;

public class TravelActivity extends Activity {

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
        Toast.makeText(this, "Opening Camera...", Toast.LENGTH_SHORT).show();
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

}
