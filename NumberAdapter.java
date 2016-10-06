package com.prisyazhnuy.bullscows;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prisyazhnuy.bullscows.model.Attempt;

import java.util.List;


public class NumberAdapter extends ArrayAdapter<Attempt> {

    private static LayoutInflater inflater = null;

    public NumberAdapter(Context context, List<Attempt> objects) {
        super(context, R.layout.attempt_item, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View attempt = convertView;
        if (attempt == null)
            attempt = inflater.inflate(R.layout.attempt_item, null, true);
            TextView tvNumAttempt = (TextView) attempt.findViewById(R.id.tvNumberAttempt);
            TextView tvNumber = (TextView) attempt.findViewById(R.id.tvNumber);
            TextView tvCows = (TextView) attempt.findViewById(R.id.tvCows);
            TextView tvBulls = (TextView) attempt.findViewById(R.id.tvBulls);
            Attempt number = getItem(position);
            if (number != null) {
                tvNumAttempt.setText(String.valueOf(number.getNumAttempt()));
                tvNumber.setText(number.getNumber());
                tvCows.setText(String.valueOf(number.getCows()));
                tvBulls.setText(String.valueOf(number.getBulls()));
            }
        return attempt;
    }
}
