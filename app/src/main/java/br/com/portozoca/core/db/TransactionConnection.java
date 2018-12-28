package br.com.portozoca.core.db;

import android.database.sqlite.SQLiteDatabase;

public class TransactionConnection implements Connection  {

    private final AppDatabase appDb;
    private final SQLiteDatabase db;

    private boolean transactionRunning = false;

    public TransactionConnection(AppDatabase appDb) {
        this.appDb = appDb;
        this.db = appDb.getWritableDatabase();
        beginTransactionIfNeeded();
    }

    @Override
    public void commit(){
        db.setTransactionSuccessful();
    }

    @Override
    public void close(){
        db.endTransaction();
        db.close();
    }

    @Override
    public void execute(String sql){
        db.execSQL(sql);
    }
    @Override
    public void execute(String sql, Object[] bindings){
        db.execSQL(sql, bindings);
    }

    @Override
    public SQLiteDatabase getDb() {
        return db;
    }

    private void beginTransactionIfNeeded(){
        if (!transactionRunning){
            transactionRunning = true;
            db.beginTransaction();
        }
    }

}
