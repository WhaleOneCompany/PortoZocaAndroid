package br.com.portozoca.core.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.portozoca.bean.Travel;
import br.com.portozoca.core.db.Connection;

public class TravelDAO {

    public static final String TABLE_NAME = "Travel";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SHIP = "Ship";
    public static final String COLUMN_CUSTOMER = "Customer";
    public static final String COLUMN_TRAVEL_DATE = "Travel_Date";

    private final Connection conn;

    public TravelDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Travel bean){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bean.getName());
        values.put(COLUMN_SHIP, bean.getShip());
        values.put(COLUMN_CUSTOMER, bean.getClient());
        values.put(COLUMN_TRAVEL_DATE, fmtDate(bean.getTravelDate()));
        conn.getDb().insert(TABLE_NAME, null, values);
    }

    public void update(Travel bean){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bean.getName());
        values.put(COLUMN_CUSTOMER, bean.getClient());
        values.put(COLUMN_TRAVEL_DATE, fmtDate(bean.getTravelDate()));
        values.put(COLUMN_SHIP, bean.getShip());
        conn.getDb().update(TABLE_NAME, values, COLUMN_ID.concat(" = ? "), new String[]{bean.getId().toString()});
    }

    public void delete(Long id){
        conn.getDb().delete(TABLE_NAME, COLUMN_ID.concat(" = ? "), new String[]{id.toString()});
    }

    public Cursor getCursorForSearch(String queryString){
        String filter = new StringBuilder()
                .append(COLUMN_NAME).append(" LIKE ? OR ")
                .append(COLUMN_SHIP).append(" LIKE ? OR ")
                .append(COLUMN_CUSTOMER).append(" LIKE ? OR ")
                .append(COLUMN_TRAVEL_DATE).append(" LIKE ? ")
                .toString();

        String qs = new StringBuilder().append("%").append(queryString.toUpperCase()).append("%").toString();
        return getCursor(filter, new String[]{qs, qs, qs, qs});
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String filter, String[] filterArgs){
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_SHIP, COLUMN_CUSTOMER, COLUMN_TRAVEL_DATE};
        String order = new StringBuilder().append(COLUMN_TRAVEL_DATE).append(" DESC").toString();
        return conn.getDb().query(TABLE_NAME, columns, filter,filterArgs,null,null, order);
    }

    public List<Travel> select(){
        return select(null, null);
    }

    public List<Travel> select(String filter, String[] filterArgs){
        List<Travel> list = new ArrayList<>();
        Cursor cursor = getCursor(filter, filterArgs);
        // Iterates the query result
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                Travel bean = toBean(cursor);
                list.add(bean);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public static final Travel toBean(Cursor cursor){
        Travel bean = new Travel();
        bean.setId((cursor.getLong(cursor.getColumnIndex(COLUMN_ID))));
        bean.setName((cursor.getString(cursor.getColumnIndex(COLUMN_NAME))));
        bean.setShip((cursor.getString(cursor.getColumnIndex(COLUMN_SHIP))));
        bean.setClient((cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER))));
        bean.setTravelDate((toDate(cursor.getString(cursor.getColumnIndex(COLUMN_TRAVEL_DATE)))));
        return bean;
    }

    private static Date toDate(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String fmtDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
