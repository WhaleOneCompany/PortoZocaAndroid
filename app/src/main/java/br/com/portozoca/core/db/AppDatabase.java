package br.com.portozoca.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    public AppDatabase(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("*** Created database " + super.getDatabaseName());
        db.execSQL(SQL_CREATE_DATABASE);
        db.execSQL(SQL_INIT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*** Upgraded database " + super.getDatabaseName());
        db.execSQL("DROP TABLE Travel");
        onCreate(db);
    }

    public static final String SQL_CREATE_DATABASE = new StringBuilder()
            .append("CREATE TABLE Travel (")
            .append(" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
            .append(" Name TEXT NOT NULL COLLATE NOCASE, ")
            .append(" Ship TEXT NOT NULL COLLATE NOCASE, ")
            .append(" Customer TEXT NOT NULL COLLATE NOCASE, ")
            .append(" Travel_Date DATETIME NOT NULL")
            .append(")")
            .toString();

    public static final String SQL_INIT_DB = new StringBuilder()
            .append("INSERT INTO Travel ")
            .append(" (Name, Ship, Customer, Travel_Date) ")
            .append("VALUES ")
            .append(" ('NH p SC', 'NAVIO 001', 'PERIN' , '2018-12-22 13:02:00'),")
            .append(" ('Oie', 'NAVIO 002', 'SPANIOL' , '2018-12-25 16:02:00'),")
            .append(" ('Sei la', 'NAVIO 003', 'JONAS' , '2018-12-28 15:39:00'),")
            .append(" ('SC p NH', 'NAVIO 004', 'PERIN' , '2018-12-31 21:12:04')")
            .append(";")
            .toString();
}
