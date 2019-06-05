package com.ojasxlabs.cricketlogics;

public class ListItemForLiveScore {
private String team1;
private String team2;
private String date;

private String matchStarted;
private String match_type;
//private String uniqueId;

    public ListItemForLiveScore( /*String uniqueId,*/ String date, /*String squad,*/ String team2, String team1, String match_type, String matchStarted) {
        //this.uniqueId = uniqueId;
        this.date = date;
        //this.squad = squad;
        this.team2 = team2;
        this.team1 = team1;
        this.match_type= match_type;
        this.matchStarted = matchStarted;

    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getDate() {
        return date;
    }

   /* public String getSquad() {
        return squad;
    }*/

    public String getMatchStarted() {
        return matchStarted;
    }

  /*public String getUniqueId() {
        return uniqueId;
    }*/

    public String getMatch_type() {
        return match_type;
    }
}
