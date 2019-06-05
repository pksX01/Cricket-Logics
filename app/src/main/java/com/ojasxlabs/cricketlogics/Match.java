package com.ojasxlabs.cricketlogics;

public class Match {
    private String Team1;
    private String Team2;
    private String Winner;
   // private String Toss_winner;
    private String Date;

    public Match() {

    }



    public Match(String date, String team1, String team2, /*String toss_winner,*/ String winner ) {
        Date = date;
        Team1 = team1;
        Team2 = team2;
        //Toss_winner = toss_winner;
        Winner = winner;

    }

    public String getTeam1() {
        return Team1;
    }

    public void setTeam1(String team1) {
        Team1 = team1;
    }

    public String getTeam2() {
        return Team2;
    }

    public void setTeam2(String team2) {
        Team2 = team2;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

   /* public String getToss_winner() {
        return Toss_winner;
    }

    public void setToss_winner(String toss_winner) {
        Toss_winner = toss_winner;
    }*/
}
