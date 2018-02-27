package com.ctrla.tictactoe3.controllers;

/**
 * Created by Aamir on 12/20/2017.
 */

import android.widget.Button;
import android.widget.TextView;

import com.ctrla.tictactoe3.R;
import com.ctrla.tictactoe3.models.TicTacToeLeaderModel;

public class TicTacToeLeaderController {

    private static TicTacToeLeaderController instance;

    private TicTacToeLeaderController() {
    }

    public static synchronized TicTacToeLeaderController getInstance() {
        if (instance == null)
            instance = new TicTacToeLeaderController();
        return instance;
    }

    private static TicTacToeLeaderModel model = TicTacToeLeaderModel.getInstance();

    private Button[] buttons;
    private TextView humanScore;
    private TextView droidScore;


    private void drawButton(final Button btn, int state) {
        if (TicTacToeLeaderModel.NOUGHT == state)
            btn.setBackgroundResource(R.drawable.oo);
        else if (TicTacToeLeaderModel.CROSS == state)
            btn.setBackgroundResource(R.drawable.xx);
        else
            btn.setBackgroundResource(R.drawable.whiteboard);
    }
    public void refreshGame() {
        for (int i = 0; i < buttons.length; i++)
            drawButton(buttons[i], model.getGameField()[i / 4][i % 4]);
        humanScore.setText(model.getHumanScore() + "");
        droidScore.setText(model.getDroidScore() + "");
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    public void setScores(TextView humanScore, TextView droidScore) {
        this.humanScore = humanScore;
        this.droidScore = droidScore;
    }

}