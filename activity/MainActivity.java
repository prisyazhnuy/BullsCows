package com.prisyazhnuy.bullscows.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.prisyazhnuy.bullscows.NumberManager;
import com.prisyazhnuy.bullscows.R;
import com.prisyazhnuy.bullscows.fragment.GameFieldFragment;
import com.prisyazhnuy.bullscows.fragment.KeyboardFragment;
import com.prisyazhnuy.bullscows.model.Number;

public class MainActivity extends AppCompatActivity implements NumberManager {

    private static final int SIZE = 4;
    private static final String TAG = "MainActivity";
    private Number mNumber;
    private Fragment mGameFieldFragment;
    private Fragment mKeyboardFragment;
    private Number mAttempt = new Number(SIZE);

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("number", mNumber);
        outState.putParcelable("attempt", mAttempt);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mNumber = savedInstanceState.getParcelable("number");
        mAttempt = savedInstanceState.getParcelable("attempt");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tbGameField = (Toolbar) findViewById(R.id.tbGameField);
        setSupportActionBar(tbGameField);
        mNumber = new Number(SIZE);
        mNumber.generateNumber();
        Log.d(TAG, "onCreate: number = " + mNumber.toString());
        FragmentManager fragmentManager = getFragmentManager();
        mGameFieldFragment = fragmentManager.findFragmentByTag("GAME_FIELD");
        if (mGameFieldFragment == null) {
            mGameFieldFragment = new GameFieldFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.rlGameField, mGameFieldFragment, "GAME_FIELD");
            transaction.commit();
        }

        mKeyboardFragment = fragmentManager.findFragmentByTag("KEYBOARD");
        if (mKeyboardFragment == null) {
            mKeyboardFragment = new KeyboardFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.rlKeyboard, mKeyboardFragment, "KEYBOARD");
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_field_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_restart:
                restartGame();
                break;
            case R.id.menu_history:
                String message = getString(R.string.future_features);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_rules:
                Intent rulesActivity = new Intent(this, RulesActivity.class);
                startActivity(rulesActivity);
                break;
            case R.id.menu_settings:
                break;
            case R.id.menu_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addDigit(int digit) {
        ((GameFieldFragment) mGameFieldFragment).addDigit(digit);
        mAttempt.addDigit(digit);
    }

    @Override
    public void removeDigit() {
        ((GameFieldFragment) mGameFieldFragment).removeDigit();
        mAttempt.remoteLastDigit();
    }

    @Override
    public void checkNumber() {
        int bulls = mNumber.getBulls(mAttempt);
        int cows = mNumber.getCows(mAttempt);
        ((GameFieldFragment) mGameFieldFragment).addAttempt(mAttempt.toString(), cows, bulls);
        mAttempt.clear();
        if (bulls == SIZE) {
            showWinDialog();
        }
    }

    private void restartGame() {
        ((GameFieldFragment) mGameFieldFragment).clearHistory();
        ((GameFieldFragment) mGameFieldFragment).clearNumber();
        ((KeyboardFragment) mKeyboardFragment).clearKeyboard();
        mNumber.generateNumber();
    }

    private void showWinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_win_title)
                .setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        restartGame();
                    }
                })
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.create().show();
    }
}
