package com.ctrla.tictactoe3.activities;

import com.ctrla.tictactoe3.controllers.TicTacToeController;
import com.ctrla.tictactoe3.models.TicTacToeModel;

import com.ctrla.tictactoe3.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity implements OnClickListener {

	private static TicTacToeModel model = TicTacToeModel.getInstance();
	private static TicTacToeController controller = TicTacToeController
			.getInstance();

	private Button[] buttons;
	InterstitialAd mInterstitialAd;
	
	private void initListeners() {
		buttons = new Button[9];
		buttons[0] = (Button) findViewById(R.id.button_11);
		buttons[1] = (Button) findViewById(R.id.button_12);
		buttons[2] = (Button) findViewById(R.id.button_13);
		buttons[3] = (Button) findViewById(R.id.button_21);
		buttons[4] = (Button) findViewById(R.id.button_22);
		buttons[5] = (Button) findViewById(R.id.button_23);
		buttons[6] = (Button) findViewById(R.id.button_31);
		buttons[7] = (Button) findViewById(R.id.button_32);
		buttons[8] = (Button) findViewById(R.id.button_33);

		for (Button btn : buttons) {
			btn.setOnClickListener(this);
		}

		Button reset=(Button) findViewById(R.id.reset);
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				showRestartDialog();
			}
		});
		/*Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Kinderg.ttf");
		reset.setTypeface(typeFace);*/
		
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
			model.doMove(0, 0);
			vibe.vibrate(100);
		//xPlayer.start();
			break;
		case R.id.button_12:

			model.doMove(0, 1);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		case R.id.button_13:

			model.doMove(0, 2);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		case R.id.button_21:
			model.doMove(1, 0);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		case R.id.button_22:

			model.doMove(1, 1);
			vibe.vibrate(200);
			//xPlayer.start();
			break;
		case R.id.button_23:
			model.doMove(1, 2);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		case R.id.button_31:
				model.doMove(2, 0);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		case R.id.button_32:
			//xPlayer.start();
			model.doMove(2, 1);
			vibe.vibrate(100);
			break;
		case R.id.button_33:
			model.doMove(2, 2);
			vibe.vibrate(100);
			//xPlayer.start();
			break;
		}
	}



	private void newRound() {
		model.newRound();
		controller.refreshGame();
	}
	
	private  void restart(){
		showRestartDialog();
	}

	private void newGame() { 
		model.newGame();
		controller.refreshGame();
	}

	private void showAlertDialog(int status) {
		
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
		setContentView(R.layout.game);

		MobileAds.initialize(this, "ca-app-pub-5246243065157193~4449969450");
		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		mInterstitialAd = new InterstitialAd(this);

		// set the ad unit ID
		mInterstitialAd.setAdUnitId("ca-app-pub-5246243065157193/4128150227");
		// Load ads into Interstitial Ads
		mInterstitialAd.loadAd(new AdRequest.Builder().build());

		mInterstitialAd.setAdListener(new AdListener() {
			public void onAdLoaded() {
				showInterstitial();
			}
		});
		initListeners();
		injectionController();
		controller.refreshGame();

	}
	private void showInterstitial() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		} else {
			Log.d("TAG", "The interstitial wasn't loaded yet.");
		}
	}


	
	public void onClick(View v) {
		if (v instanceof Button) {
			doMove((Button) v);
			controller.refreshGame();
			if (model.getState() == TicTacToeModel.STATE_DRAW)
				showAlertDialog(R.string.draw_game);
			else if (model.getState() == TicTacToeModel.STATE_WIN) {
				if (model.getWinner() == TicTacToeModel.NOUGHT)
					showAlertDialog(R.string.nought_win_game);
				else if (model.getWinner() == TicTacToeModel.CROSS)
					showAlertDialog(R.string.cross_win_game);
			}

		}
	}

}
