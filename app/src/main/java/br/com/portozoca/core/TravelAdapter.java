package br.com.portozoca.core;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import br.com.portozoca.R;
import br.com.portozoca.bean.Travel;
import br.com.portozoca.core.dao.TravelDAO;

public class TravelAdapter extends CursorAdapter {

    public TravelAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.travel_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get current item to be displayed
        Travel currentItem = TravelDAO.toBean(cursor);

        // get the TextView for item name and item description
        TextView tv_Client = view.findViewById(R.id.tv_client);
        tv_Client .setText(currentItem.getClient());
        TextView tv_Ship = view.findViewById(R.id.tv_ship);
        tv_Ship.setText(currentItem.getShip());
        TextView tv_Date = view.findViewById(R.id.tv_date);
        tv_Date.setText(currentItem.getTravelDate().toString());
    }
}
