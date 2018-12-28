package br.com.portozoca.core.db;

import android.database.sqlite.SQLiteDatabase;

public class QueryConnection implements Connection {

    private final AppDatabase appDb;

    private final SQLiteDatabase db;

    public QueryConnection(AppDatabase appDb) {
        this.appDb = appDb;
        db = appDb.getReadableDatabase();
    }

    @Override
    public void close(){
        db.close();
    }

    @Override
    public void commit(){
        throw new UnsupportedOperationException("Cannot commit on query connection");
    }
    @Override
    public void execute(String sql){
        throw new UnsupportedOperationException("Cannot execute SQL on query connection");
    }
    @Override
    public void execute(String sql, Object[] bindings){
        throw new UnsupportedOperationException("Cannot execute SQL on query connection");
    }

    @Override
    public SQLiteDatabase getDb() {
        return db;
    }

}
