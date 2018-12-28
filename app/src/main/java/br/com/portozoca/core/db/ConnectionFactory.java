package br.com.portozoca.core.db;

import android.content.Context;

public class ConnectionFactory {

    private static AppDatabase db = null;

    private ConnectionFactory(){
        throw new IllegalStateException("You can't instantiate ".concat(ConnectionFactory.class.getSimpleName()).concat("!"));
    }

    public static Connection getReadConnection(Context ctx){
        if (db == null){
            connectDB(ctx);
        }
        return new QueryConnection(db);
    }
    public static Connection getTransactionConnection(Context ctx){
        if (db == null){
            connectDB(ctx);
        }
        return new TransactionConnection(db);
    }

    private static synchronized void connectDB(Context ctx){
        // Double checked singleton access
        if (db == null){
            db = new AppDatabase(ctx, "app_db.db");
        }
    }

}
