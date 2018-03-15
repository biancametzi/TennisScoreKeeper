package com.example.android.tennisscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.tennisscorekeeper.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("player1CurrentGameScore", player1CurrentGameScore);
        savedInstanceState.putString("player2CurrentGameScore", player2CurrentGameScore);
        savedInstanceState.putInt("player1Set1", player1Set1);
        savedInstanceState.putInt("player2Set1", player2Set1);
        savedInstanceState.putInt("player1Set2", player1Set2);
        savedInstanceState.putInt("player2Set2", player2Set2);
        savedInstanceState.putInt("player1Set3", player1Set3);
        savedInstanceState.putInt("player2Set3", player2Set3);
        savedInstanceState.putInt("currentSetId", currentSetId);
        savedInstanceState.putInt("numberOfChallengesLeftPlayer1", numberOfChallengesLeftPlayer1);
        savedInstanceState.putInt("numberOfChallengesLeftPlayer2", numberOfChallengesLeftPlayer2);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1CurrentGameScore = savedInstanceState.getString("player1CurrentGameScore");
        player2CurrentGameScore = savedInstanceState.getString("player2CurrentGameScore");
        player1Set1 = savedInstanceState.getInt("player1Set1");
        player2Set1 = savedInstanceState.getInt("player2Set1");
        player1Set2 = savedInstanceState.getInt("player1Set2");
        player2Set2 = savedInstanceState.getInt("player2Set2");
        player1Set3 = savedInstanceState.getInt("player1Set3");
        player2Set3 = savedInstanceState.getInt("player2Set3");
        currentSetId = savedInstanceState.getInt("currentSetId");
        numberOfChallengesLeftPlayer1 = savedInstanceState.getInt("numberOfChallengesLeftPlayer1");
        numberOfChallengesLeftPlayer2 = savedInstanceState.getInt("numberOfChallengesLeftPlayer2");

        updateScoreOnUI();
        updateChallengeOnUI();
    }

    int player1Set1 = 0;
    int player2Set1 = 0;
    int player1Set2 = 0;
    int player2Set2 = 0;
    int player1Set3 = 0;
    int player2Set3 = 0;
    int currentSetId = 1;
    int numberOfChallengesLeftPlayer1 = 3;
    int numberOfChallengesLeftPlayer2 = 3;

    String player1CurrentGameScore = "0";    // -> 0, 15, 30, 40, AD
    String player2CurrentGameScore = "0";    // -> 0, 15, 30, 40, AD

    boolean isMatchFinished = false;

    /**
     * This method is called when + button for player1 is clicked (it won the point)
     */
    public void addPointToPlayer1(View view) {

        if (isMatchFinished) {
            return;
        }

        // update score for player 1
        if (player1CurrentGameScore.equals("0")) {
            player1CurrentGameScore = "15";
        } else if (player1CurrentGameScore.equals("15")) {
            player1CurrentGameScore = "30";
        } else if (player1CurrentGameScore.equals("30")) {
            player1CurrentGameScore = "40";
        } else if (player1CurrentGameScore.equals("40")) {
            if (player2CurrentGameScore.equals("AD")) {
                player1CurrentGameScore = "40";
                player2CurrentGameScore = "40";
            } else if (player2CurrentGameScore.equals("40")) {
                player1CurrentGameScore = "AD";
            } else {
                // increase current set score
                if (currentSetId == 1) {
                    player1Set1 += 1;
                    if (player1Set1 == 6) {
                        currentSetId = 2;
                    }
                } else if (currentSetId == 2) {
                    player1Set2 += 1;
                    if (player1Set2 == 6) {
                        currentSetId = 3;
                    }
                } else {
                    player1Set3 += 1;
                    if (player1Set3 == 6) {
                        isMatchFinished = true;
                    }
                }

                // reset current set score
                player1CurrentGameScore = "0";
                player2CurrentGameScore = "0";
            }
        } else {
            // increase current set score
            if (currentSetId == 1) {
                player1Set1 += 1;
                if (player1Set1 == 6) {
                    currentSetId = 2;
                }
            } else if (currentSetId == 2) {
                player1Set2 += 1;
                if (player1Set2 == 6) {
                    currentSetId = 3;
                }
            } else {
                player1Set3 += 1;
                if (player1Set3 == 6) {
                    isMatchFinished = true;
                }
            }

            // reset current set score
            player1CurrentGameScore = "0";
            player2CurrentGameScore = "0";
        }

        // display updated score
        updateScoreOnUI();
    }

    //decreases number of challenges for player 1
    public void decreaseChallengesPlayer1(View view) {

        if (numberOfChallengesLeftPlayer1 > 0)
            numberOfChallengesLeftPlayer1 = numberOfChallengesLeftPlayer1 - 1;
        else
            numberOfChallengesLeftPlayer1 = 0;

        updateChallengeOnUI();
    }

    /**
     * This method is called when + button for player2 is clicked (it won the point)
     */
    public void addPointToPlayer2(View view) {

        if (isMatchFinished) {
            return;
        }

        // update score for player 2
        if (player2CurrentGameScore.equals("0")) {
            player2CurrentGameScore = "15";
        } else if (player2CurrentGameScore.equals("15")) {
            player2CurrentGameScore = "30";
        } else if (player2CurrentGameScore.equals("30")) {
            player2CurrentGameScore = "40";
        } else if (player2CurrentGameScore.equals("40")) {
            if (player1CurrentGameScore.equals("AD")) {
                player2CurrentGameScore = "40";
                player1CurrentGameScore = "40";
            } else if (player1CurrentGameScore.equals("40")) {
                player2CurrentGameScore = "AD";
            } else {
                // increase current set score
                if (currentSetId == 1) {
                    player2Set1 += 1;
                    if (player2Set1 == 6) {
                        currentSetId = 2;
                    }
                } else if (currentSetId == 2) {
                    player2Set2 += 1;
                    if (player2Set2 == 6) {
                        currentSetId = 3;
                    }
                } else {
                    player2Set3 += 1;
                    if (player2Set3 == 6) {
                        isMatchFinished = true;
                    }
                }

                // reset current set score
                player1CurrentGameScore = "0";
                player2CurrentGameScore = "0";
            }
        } else {
            // increase current set score
            if (currentSetId == 1) {
                player2Set1 += 1;
                if (player2Set1 == 6) {
                    currentSetId = 2;
                }
            } else if (currentSetId == 2) {
                player2Set2 += 1;
                if (player2Set2 == 6) {
                    currentSetId = 3;
                }
            } else {
                player2Set3 += 1;
                if (player2Set3 == 6) {
                    isMatchFinished = true;
                }
            }

            // reset current set score
            player1CurrentGameScore = "0";
            player2CurrentGameScore = "0";
        }

        // display updated score
        updateScoreOnUI();
    }

    //decreases number of challenges for player 2
    public void decreaseChallengesPlayer2(View view) {

        if (numberOfChallengesLeftPlayer2 > 0)
            numberOfChallengesLeftPlayer2 = numberOfChallengesLeftPlayer2 - 1;
        else
            numberOfChallengesLeftPlayer2 = 0;

            updateChallengeOnUI();
    }

    private void updateScoreOnUI() {
        TextView player1CurrentGameScoreView = (TextView) findViewById(R.id.pointsPlayer1);
        player1CurrentGameScoreView.setText(String.valueOf(player1CurrentGameScore));
        TextView player2CurrentGameScoreView = (TextView) findViewById(R.id.pointsPlayer2);
        player2CurrentGameScoreView.setText(String.valueOf(player2CurrentGameScore));

        TextView currentSetScoreViewFirstSetForPlayer1 = (TextView) findViewById(R.id.firstSetPlayer1);
        currentSetScoreViewFirstSetForPlayer1.setText(String.valueOf(player1Set1));
        TextView currentSetScoreViewSecondSetForPlayer1 = (TextView) findViewById(R.id.secondSetPlayer1);
        currentSetScoreViewSecondSetForPlayer1.setText(String.valueOf(player1Set2));
        TextView currentSetScoreViewThirdSetForPlayer1 = (TextView) findViewById(R.id.thirdSetPlayer1);
        currentSetScoreViewThirdSetForPlayer1.setText(String.valueOf(player1Set3));

        TextView currentSetScoreViewFirstSetForPlayer2 = (TextView) findViewById(R.id.firstSetPlayer2);
        currentSetScoreViewFirstSetForPlayer2.setText(String.valueOf(player2Set1));
        TextView currentSetScoreViewSecondSetForPlayer2 = (TextView) findViewById(R.id.secondSetPlayer2);
        currentSetScoreViewSecondSetForPlayer2.setText(String.valueOf(player2Set2));
        TextView currentSetScoreViewThirdSetForPlayer2 = (TextView) findViewById(R.id.thirdSetPlayer2);
        currentSetScoreViewThirdSetForPlayer2.setText(String.valueOf(player2Set3));
    }

    //updated number of challenges
    public void updateChallengeOnUI() {
        TextView challengesLeftPlayer1 = (TextView) findViewById(R.id.numberOfChallengesLeftPlayer1);
        challengesLeftPlayer1.setText(String.valueOf(numberOfChallengesLeftPlayer1));
        TextView challengesLeftPlayer2 = (TextView) findViewById(R.id.numberOfChallengesLeftPlayer2);
        challengesLeftPlayer2.setText(String.valueOf(numberOfChallengesLeftPlayer2));
    }

    public void resetScore(View view) {
        player1Set1 = 0;
        player1Set2 = 0;
        player1Set3 = 0;
        player2Set1 = 0;
        player2Set2 = 0;
        player2Set3 = 0;
        player1CurrentGameScore = "0";
        player2CurrentGameScore = "0";
        numberOfChallengesLeftPlayer1 = 3;
        numberOfChallengesLeftPlayer2 = 3;
        updateChallengeOnUI();
        updateScoreOnUI();
        currentSetId = 1;
    }

}