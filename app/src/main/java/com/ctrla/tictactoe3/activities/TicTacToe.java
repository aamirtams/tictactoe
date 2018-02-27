package com.ctrla.tictactoe3.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.ctrla.tictactoe3.R;
import com.ctrla.tictactoe3.models.TicTacToeLeaderModel;
import com.ctrla.tictactoe3.models.TicTacToeModel;

import static android.R.attr.data;
import static android.R.attr.radioButtonStyle;

public class TicTacToe extends Activity implements OnClickListener {

    View continueGameButton, newGameButton, optionsButton, multiplayer,leaderboard;
    RadioButton rd2, rd1;
    Point p;
    Intent intent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MobileAds.initialize(this, "ca-app-pub-5246243065157193~4449969450");
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        continueGameButton = findViewById(R.id.continue_button);
        continueGameButton.setOnClickListener(this);
        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);
        optionsButton = findViewById(R.id.options_button);
        optionsButton.setOnClickListener(this);
        multiplayer = findViewById(R.id.multiplayer);
        multiplayer.setOnClickListener(this);
        leaderboard = findViewById(R.id.leader_board);
        leaderboard.setOnClickListener(this);

    }

    private void showRadioButtonDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.options);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);
        RadioButton easy = (RadioButton) dialog.findViewById(R.id.easy_radiobutton);
        RadioButton medium = (RadioButton) dialog.findViewById(R.id.medium_radiobutton);
        RadioButton hard = (RadioButton) dialog.findViewById(R.id.hard_radiobutton);
        dialog.show();
        easy.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.EASY_DIF);
                TicTacToeLeaderModel.getInstance().setDifficulty(TicTacToeLeaderModel.EASY_DIF);
            }
        });
        medium.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.MEDIUM_DIF);
                TicTacToeLeaderModel.getInstance().setDifficulty(TicTacToeLeaderModel.MEDIUM_DIF);
            }
        });
        hard.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.HARD_DIF);
                TicTacToeLeaderModel.getInstance().setDifficulty(TicTacToeLeaderModel.HARD_DIF);
            }
        });
        switch (TicTacToeModel.getInstance().getDifficulty()) {
            case TicTacToeModel.EASY_DIF:
                easy.setChecked(true);
                break;
            case TicTacToeModel.MEDIUM_DIF:
                medium.setChecked(true);
                break;
            case TicTacToeModel.HARD_DIF:
                hard.setChecked(true);
                break;
        }

    }

    public void showPopup(final Activity context, Point p) {

        int popupWidth = -1;
        int popupHieght = 470;

        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.edit_player);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.multi_popup, viewGroup);

        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHieght);
        popup.setFocusable(true);


        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button cancel = (Button) layout.findViewById(R.id.cancel_button);
        Button ok = (Button) layout.findViewById(R.id.ok_button);
        final EditText et1 = (EditText) layout.findViewById(R.id.pl1Name);
        final EditText et2 = (EditText) layout.findViewById(R.id.pl2Name);
        et1.setText("Player 1");
        et2.setText("Player 2");

        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TicTacToeModel.getInstance().newGame();
                intent = new Intent(getApplicationContext(), MultiGame.class);
                intent.putExtra("EdiTtEXTvALUE", et1.getText().toString());
                intent.putExtra("EdiTtEXTvALUE2", et2.getText().toString());
                startActivity(intent);
                popup.dismiss();
            }
        });
    }

    public void showLPopup(final Activity context, Point p) {

        int popupWidth = -1;
        int popupHieght = 570;

        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.edit_player);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.leaderboard_popup, viewGroup);

        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHieght);
        popup.setFocusable(true);


        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final Button cancel = (Button) layout.findViewById(R.id.cancel_button);
        final Button ok = (Button) layout.findViewById(R.id.ok_button);
        final EditText et1 = (EditText) layout.findViewById(R.id.pl1Name);
        final EditText et2 = (EditText) layout.findViewById(R.id.pl2Name);
        et1.setText("Player 1");
        et2.setText("Android");
        et2.setEnabled(false);
        et2.setClickable(false);
        et1.setEnabled(false);
        et1.setClickable(false);
       final RadioGroup rg = (RadioGroup) layout.findViewById(R.id.players_radio_group);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.gameplay_one_player:
                        et2.setText("Android");
                        et2.setEnabled(false);
                        et2.setClickable(false);
                        et1.setEnabled(true);
                        et1.setClickable(true);
                       ok.setOnClickListener(new OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               TicTacToeLeaderModel.getInstance().newGame();
                               intent = new Intent(getApplicationContext(), LeaderSingleBoard.class);
                               intent.putExtra("EdiTtEXTvALUE", et1.getText().toString());
                               intent.putExtra("EdiTtEXTvALUE2", et2.getText().toString());
                               startActivity(intent);
                               popup.dismiss();
                           }
                       });
                        break;
                    case R.id.gameplay_two_player:
                        et2.setEnabled(true);
                        et2.setClickable(true);
                        et1.setEnabled(true);
                        et1.setClickable(true);
                        et2.setText("Player 2");
                        ok.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TicTacToeLeaderModel.getInstance().newGame();
                                intent = new Intent(getApplicationContext(), LeaderBoard.class);
                                intent.putExtra("EdiTtEXTvALUE", et1.getText().toString());
                                intent.putExtra("EdiTtEXTvALUE2", et2.getText().toString());
                                startActivity(intent);
                                popup.dismiss();
                            }
                        });
                        break;
                }
            }
        });

        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select One or Two Player", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        int[] location = new int[2];
        multiplayer.getLocationOnScreen(location);
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_button:
                //music(2);
                startActivity(new Intent(this, Game.class));
                break;
            case R.id.new_game_button:
                //music(2);
                TicTacToeModel.getInstance().newGame();
                startActivity(new Intent(this, Game.class));
                break;
            case R.id.options_button:
                //music(2);
                showRadioButtonDialog();
                break;
            case R.id.multiplayer:
                showPopup(TicTacToe.this, p);
                break;
            case R.id.leader_board:
                showLPopup(TicTacToe.this, p);
                break;
            default:
                break;
        }
    }


}