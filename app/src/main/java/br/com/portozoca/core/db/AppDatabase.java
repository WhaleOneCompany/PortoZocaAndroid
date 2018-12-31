package br.com.portozoca.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.portozoca.core.utils.Files;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DB_SCHEMA = "db_schema.sql";
    private static final String DB_INIT = "db_init.sql";

    private Context context;

    public AppDatabase(Context context, String name) {
        super(context, name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("*** Created database " + super.getDatabaseName());
        db.execSQL(Files.fromAssets(context, DB_SCHEMA));
        db.execSQL(Files.fromAssets(context,DB_INIT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*** Upgraded database " + super.getDatabaseName());
    }

}
