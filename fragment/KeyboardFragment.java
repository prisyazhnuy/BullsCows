package com.prisyazhnuy.bullscows.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prisyazhnuy.bullscows.NumberManager;
import com.prisyazhnuy.bullscows.R;
import com.prisyazhnuy.bullscows.ViewList;
import com.prisyazhnuy.bullscows.model.Number;

import java.util.List;

public class KeyboardFragment extends Fragment implements View.OnClickListener {

    private NumberManager mNumberManager;
    private List<Button> mPressedButtons = new ViewList<>();
    private Number mAttempt;


    public KeyboardFragment() {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("attempt", mAttempt);
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimatorInflater.loadAnimator(getActivity(), R.animator.slide_in_right);
        } else {
            return AnimatorInflater.loadAnimator(getActivity(), R.animator.slide_in_left);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_keyboard, container, false);
        if (savedInstanceState != null) {
            mAttempt = savedInstanceState.getParcelable("attempt");
        } else {
            mAttempt = new Number(4);
        }
        mNumberManager = (NumberManager) getActivity();
        Button btnOne = (Button) fragment.findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this);
        Button btnTwo = (Button) fragment.findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this);
        Button btnThree = (Button) fragment.findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this);
        Button btnFour = (Button) fragment.findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this);
        Button btnFive = (Button) fragment.findViewById(R.id.btnFive);
        btnFive.setOnClickListener(this);
        Button btnSix = (Button) fragment.findViewById(R.id.btnSix);
        btnSix.setOnClickListener(this);
        Button btnSeven = (Button) fragment.findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(this);
        Button btnEight = (Button) fragment.findViewById(R.id.btnEight);
        btnEight.setOnClickListener(this);
        Button btnNine = (Button) fragment.findViewById(R.id.btnNine);
        btnNine.setOnClickListener(this);
        Button btnZero = (Button) fragment.findViewById(R.id.btnZero);
        btnZero.setOnClickListener(this);
        Button btnBackspace = (Button) fragment.findViewById(R.id.btnBackspace);
        btnBackspace.setOnClickListener(this);
        Button btnEnter = (Button) fragment.findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
        if (mAttempt != null) {
            for (Integer digit : mAttempt.getDigits()) {
                switch (digit) {
                    case 0:
                        mPressedButtons.add(btnZero);
                        break;
                    case 1:
                        mPressedButtons.add(btnOne);
                        break;
                    case 2:
                        mPressedButtons.add(btnTwo);
                        break;
                    case 3:
                        mPressedButtons.add(btnThree);
                        break;
                    case 4:
                        mPressedButtons.add(btnFour);
                        break;
                    case 5:
                        mPressedButtons.add(btnFive);
                        break;
                    case 6:
                        mPressedButtons.add(btnSix);
                        break;
                    case 7:
                        mPressedButtons.add(btnSeven);
                        break;
                    case 8:
                        mPressedButtons.add(btnEight);
                        break;
                    case 9:
                        mPressedButtons.add(btnNine);
                        break;
                }
            }
        }
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBackspace:
                mPressedButtons.remove(mPressedButtons.size() - 1);
                mAttempt.remoteLastDigit();
                mNumberManager.removeDigit();
                break;
            case R.id.btnEnter:
                if (mAttempt.size() == 4) {
                    mNumberManager.checkNumber();
                    mAttempt.clear();
                    mPressedButtons.clear();
                }
                break;
        }
        if (mAttempt.size() != 4) {
            switch (view.getId()) {
                case R.id.btnZero:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(0);
                    mNumberManager.addDigit(0);
                    break;
                case R.id.btnOne:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(1);
                    mNumberManager.addDigit(1);
                    break;
                case R.id.btnTwo:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(2);
                    mNumberManager.addDigit(2);
                    break;
                case R.id.btnThree:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(3);
                    mNumberManager.addDigit(3);
                    break;
                case R.id.btnFour:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(4);
                    mNumberManager.addDigit(4);
                    break;
                case R.id.btnFive:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(5);
                    mNumberManager.addDigit(5);
                    break;
                case R.id.btnSix:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(6);
                    mNumberManager.addDigit(6);
                    break;
                case R.id.btnSeven:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(7);
                    mNumberManager.addDigit(7);
                    break;
                case R.id.btnEight:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(8);
                    mNumberManager.addDigit(8);
                    break;
                case R.id.btnNine:
                    mPressedButtons.add((Button) view);
                    mAttempt.addDigit(9);
                    mNumberManager.addDigit(9);
                    break;
            }
        }
    }

    public void clearKeyboard() {
        mPressedButtons.clear();
        mAttempt.clear();
    }

}
