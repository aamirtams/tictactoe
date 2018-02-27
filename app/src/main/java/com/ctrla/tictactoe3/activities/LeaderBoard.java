package com.ctrla.tictactoe3.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctrla.tictactoe3.R;
import com.ctrla.tictactoe3.controllers.TicTacToeController;
import com.ctrla.tictactoe3.controllers.TicTacToeLeaderController;
import com.ctrla.tictactoe3.models.TicTacToeLeaderModel;
import com.ctrla.tictactoe3.models.TicTacToeModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Aamir on 12/20/2017.
 */

public class LeaderBoard extends Activity implements View.OnClickListener {
    TextView txt1,tx2;
    private static TicTacToeLeaderModel model = TicTacToeLeaderModel.getInstance();
    private static TicTacToeLeaderController controller = TicTacToeLeaderController
            .getInstance();

    private Button[] buttons;

    private void initListeners() {
        buttons = new Button[16];
        buttons[0] = (Button) findViewById(R.id.button_11);
        buttons[1] = (Button) findViewById(R.id.button_12);
        buttons[2] = (Button) findViewById(R.id.button_13);
        buttons[3] = (Button) findViewById(R.id.button_14);
        buttons[4] = (Button) findViewById(R.id.button_21);
        buttons[5] = (Button) findViewById(R.id.button_22);
        buttons[6] = (Button) findViewById(R.id.button_23);
        buttons[7] = (Button) findViewById(R.id.button_24);
        buttons[8] = (Button) findViewById(R.id.button_31);
        buttons[9] = (Button) findViewById(R.id.button_32);
        buttons[10] = (Button) findViewById(R.id.button_33);
        buttons[11] = (Button) findViewById(R.id.button_34);
        buttons[12] = (Button) findViewById(R.id.button_41);
        buttons[13] = (Button) findViewById(R.id.button_42);
        buttons[14] = (Button) findViewById(R.id.button_43);
        buttons[15] = (Button) findViewById(R.id.button_44);


        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRestartDialog();
            }
        });
    }
    private void injectionController() {
        TextView humanScore=(TextView)findViewById(R.id.human_score);
        TextView droidScore=(TextView)findViewById(R.id.droid_score);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Kinderg.ttf");
        humanScore.setTypeface(typeFace);
        droidScore.setTypeface(typeFace);
        controller.setButtons(buttons);
        controller.setScores((TextView) findViewById(R.id.human_score),
                (TextView) findViewById(R.id.droid_score));
    }

    private void doMove(Button btn) {
        //MediaPlayer xPlayer = MediaPlayer.create(Game.this, R.raw.soundx);
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        switch (btn.getId()) {
            case R.id.button_11:
                model.doMultiMove(0, 0);
                // xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_12:

                model.doMultiMove(0, 1);
                //  xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_13:

                model.doMultiMove(0, 2);
                //  xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_14:

                model.doMultiMove(0, 3);
                //  xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_21:
                model.doMultiMove(1, 0);
                //  xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_22:

                model.doMultiMove(1, 1);
                // xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_23:
                model.doMultiMove(1, 2);
                // xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_24:
                model.doMultiMove(1, 3);
                // xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_31:
                model.doMultiMove(2, 0);
                // xPlayer.start();
                vibe.vibrate(100);
                break;
            case R.id.button_32:
                // xPlayer.start();
                model.doMultiMove(2, 1);
                vibe.vibrate(100);
                break;
            case R.id.button_33:
                model.doMultiMove(2, 2);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
            case R.id.button_34:
                model.doMultiMove(2, 3);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
            case R.id.button_41:
                model.doMultiMove(3, 0);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
            case R.id.button_42:
                model.doMultiMove(3, 1);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
            case R.id.button_43:
                model.doMultiMove(3, 2);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
            case R.id.button_44:
                model.doMultiMove(3, 3);
                vibe.vibrate(100);
                //   xPlayer.start();
                break;
        }
    }
    private void newRound() {
        model.newRound();
        controller.refreshGame();
    }
    private void newGame() {
        model.newGame();
        controller.refreshGame();
    }

    private void showAlertDialog(String status) {

        new AlertDialog.Builder(this).setTitle(R.string.message_title)
                .setMessage(status).setNeutralButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        newRound();
                    }
                }).show();

    }
    private void showRestartDialog() {

        new AlertDialog.Builder(this).setTitle(R.string.question_title)
                .setMessage(R.string.restart_game).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        newGame();
                    }
                }).setNegativeButton("No", null).show();
		/*final Context context = this;
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.reset);
		dialog.setTitle("Reset");

		TextView text = (TextView) dialog.findViewById(R.id.about_content);
		text.setText("Do you want to reset? ");
		ImageView image = (ImageView) dialog.findViewById(R.id.ImageView01);
		image.setImageResource(R.drawable.ic_launcher);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogbutton);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				newRound();
			}
		});

		dialog.show();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        MobileAds.initialize(this, "ca-app-pub-5246243065157193~4449969450");
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        txt1 = (TextView)findViewById(R.id.player1Name);
        tx2 = (TextView)findViewById(R.id.player2Name);
        txt1.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));
        tx2.setText(getIntent().getStringExtra("EdiTtEXTvALUE2"));
        initListeners();
        injectionController();
        controller.refreshGame();
    }

    public void onClick(View v) {
        String s1 = getIntent().getStringExtra("EdiTtEXTvALUE");
        String s2 = getIntent().getStringExtra("EdiTtEXTvALUE2");

        if (v instanceof Button) {
            doMove((Button) v);
            controller.refreshGame();
            if (model.getState() == TicTacToeModel.STATE_DRAW)
                showAlertDialog("Draw!");
            else if (model.getState() == TicTacToeModel.STATE_WIN) {
                if (model.getWinner() == TicTacToeModel.NOUGHT)
                    showAlertDialog(s2+" "+"Win!");
                else if (model.getWinner() == TicTacToeModel.CROSS)
                    showAlertDialog(s1+" "+"Win!");
            }

        }
    }

}
