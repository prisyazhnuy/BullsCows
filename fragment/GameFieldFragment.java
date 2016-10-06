package com.prisyazhnuy.bullscows.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prisyazhnuy.bullscows.NumberAdapter;
import com.prisyazhnuy.bullscows.R;
import com.prisyazhnuy.bullscows.model.Attempt;

import java.util.ArrayList;

public class GameFieldFragment extends Fragment {

    private TextView mTvNumber;
    private ListView mLvAttempt;
    private ArrayList<Attempt> mAttempts = new ArrayList<>();
    private ArrayAdapter mAttemptAdapter;
    private int mNumberInputDigits = 0;
    private int mNumberAttempts = 0;
    private String mNumber = "****";

    public GameFieldFragment() {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("number", mNumber);
        outState.putInt("numberInputDigits", mNumberInputDigits);
        outState.putParcelableArrayList("attempts", mAttempts);
        outState.putInt("numberAttempts", mNumberAttempts);
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimatorInflater.loadAnimator(getActivity(), R.animator.slide_in_left);
        } else {
            return AnimatorInflater.loadAnimator(getActivity(), R.animator.slide_in_right);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_game_field, container, false);
        if (savedInstanceState != null) {
            mNumber = savedInstanceState.getString("number");
            mNumberInputDigits = savedInstanceState.getInt("numberInputDigits");
            mAttempts = savedInstanceState.getParcelableArrayList("attempts");
            mNumberAttempts = savedInstanceState.getInt("numberAttempts");
        }

        mTvNumber = (TextView) fragment.findViewById(R.id.tvNumber);
        mTvNumber.setText(mNumber);
        mLvAttempt = (ListView) fragment.findViewById(R.id.lvAttempt);
        mAttemptAdapter = new NumberAdapter(getActivity(), mAttempts);
        mLvAttempt.setAdapter(mAttemptAdapter);

        return fragment;
    }

    private void scrollMyListViewToBottom() {
        mLvAttempt.post(new Runnable() {
            @Override
            public void run() {
                mLvAttempt.setSelection(0);
            }
        });
    }

    public void addDigit(int digit) {
        CharSequence number = mTvNumber.getText();
        mNumber = String.valueOf(number.subSequence(0, mNumberInputDigits)) +
                digit +
                number.subSequence(mNumberInputDigits + 1, number.length());
        mNumberInputDigits++;
        mTvNumber.setText(mNumber);
    }

    public void removeDigit() {
        if (mNumberInputDigits != 0) {
            CharSequence number = mTvNumber.getText();
            mNumber = String.valueOf(number.subSequence(0, mNumberInputDigits - 1)) +
                    "*" +
                    number.subSequence(mNumberInputDigits, number.length());
            mNumberInputDigits--;
            mTvNumber.setText(mNumber);
        }
    }

    public void addAttempt(String number, int cows, int bulls) {
        mNumberAttempts++;
        Attempt attempt = new Attempt(number, cows, bulls, mNumberAttempts);
        mAttempts.add(0, attempt);
        mAttemptAdapter.notifyDataSetChanged();
        mNumber = "****";
        mTvNumber.setText(mNumber);
        mNumberInputDigits = 0;
        scrollMyListViewToBottom();
    }

    public void clearNumber() {
        mTvNumber.setText("****");
        mNumberInputDigits = 0;
    }

    public void clearHistory() {
        mAttempts.clear();
        mAttemptAdapter.clear();
        mNumberAttempts = 0;
    }

}
