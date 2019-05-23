package com.ojasxlabs.cricketlogics;

public class ListItemForLiveScore {
private String team1;
private String team2;
private String date;
//private String squad;
private String matchStarted;
//private String uniqueId;

    public ListItemForLiveScore( /*String uniqueId,*/ String date, /*String squad,*/ String team2, String team1, String matchStarted) {
        //this.uniqueId = uniqueId;
        this.date = date;
        //this.squad = squad;
        this.team2 = team2;
        this.team1 = team1;
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

   /* public String getUniqueId() {
        return uniqueId;
    }*/
}
