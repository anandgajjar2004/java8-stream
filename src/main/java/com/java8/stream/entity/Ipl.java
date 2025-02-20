package com.java8.stream.entity;

import java.time.LocalDate;

public class Ipl {

    private Long id;
    private Long matchId;
    private LocalDate matchDate;
    private String venue;
    private String batFirst;
    private String batSecond;
    private Integer innings;
    private Integer overNumber;
    private Integer ballNumber;

    private String batter;
    private String nonStriker;
    private String bowler;

    private Integer batterRuns;
    private Integer extraRuns;
    private Integer runsFromBall;
    private Boolean ballRebowled;

    private String extraType;
    private Boolean wicket;
    private String dismissalMethod;
    private String playerOut;

    private Integer inningsRuns;
    private Integer inningsWickets;
    private Integer targetScore;
    private Integer runsToGet;
    private Integer ballsRemaining;

    private String winner;
    private Boolean chasedSuccessfully;

    private Integer totalBatterRuns;
    private Integer totalNonStrikerRuns;
    private Integer batterBallsFaced;
    private Integer nonStrikerBallsFaced;

    private Integer playerOutRuns;
    private Integer playerOutBallsFaced;
    private Integer bowlerRunsConceded;
    private Boolean validBall;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public LocalDate getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getBatFirst() {
		return batFirst;
	}
	public void setBatFirst(String batFirst) {
		this.batFirst = batFirst;
	}
	public String getBatSecond() {
		return batSecond;
	}
	public void setBatSecond(String batSecond) {
		this.batSecond = batSecond;
	}
	public Integer getInnings() {
		return innings;
	}
	public void setInnings(Integer innings) {
		this.innings = innings;
	}
	public Integer getOverNumber() {
		return overNumber;
	}
	public void setOverNumber(Integer overNumber) {
		this.overNumber = overNumber;
	}
	public Integer getBallNumber() {
		return ballNumber;
	}
	public void setBallNumber(Integer ballNumber) {
		this.ballNumber = ballNumber;
	}
	public String getBatter() {
		return batter;
	}
	public void setBatter(String batter) {
		this.batter = batter;
	}
	public String getNonStriker() {
		return nonStriker;
	}
	public void setNonStriker(String nonStriker) {
		this.nonStriker = nonStriker;
	}
	public String getBowler() {
		return bowler;
	}
	public void setBowler(String bowler) {
		this.bowler = bowler;
	}
	public Integer getBatterRuns() {
		return batterRuns;
	}
	public void setBatterRuns(Integer batterRuns) {
		this.batterRuns = batterRuns;
	}
	public Integer getExtraRuns() {
		return extraRuns;
	}
	public void setExtraRuns(Integer extraRuns) {
		this.extraRuns = extraRuns;
	}
	public Integer getRunsFromBall() {
		return runsFromBall;
	}
	public void setRunsFromBall(Integer runsFromBall) {
		this.runsFromBall = runsFromBall;
	}
	public Boolean getBallRebowled() {
		return ballRebowled;
	}
	public void setBallRebowled(Boolean ballRebowled) {
		this.ballRebowled = ballRebowled;
	}
	public String getExtraType() {
		return extraType;
	}
	public void setExtraType(String extraType) {
		this.extraType = extraType;
	}
	public Boolean getWicket() {
		return wicket;
	}
	public void setWicket(Boolean wicket) {
		this.wicket = wicket;
	}
	public String getDismissalMethod() {
		return dismissalMethod;
	}
	public void setDismissalMethod(String dismissalMethod) {
		this.dismissalMethod = dismissalMethod;
	}
	public String getPlayerOut() {
		return playerOut;
	}
	public void setPlayerOut(String playerOut) {
		this.playerOut = playerOut;
	}
	public Integer getInningsRuns() {
		return inningsRuns;
	}
	public void setInningsRuns(Integer inningsRuns) {
		this.inningsRuns = inningsRuns;
	}
	public Integer getInningsWickets() {
		return inningsWickets;
	}
	public void setInningsWickets(Integer inningsWickets) {
		this.inningsWickets = inningsWickets;
	}
	public Integer getTargetScore() {
		return targetScore;
	}
	public void setTargetScore(Integer targetScore) {
		this.targetScore = targetScore;
	}
	public Integer getRunsToGet() {
		return runsToGet;
	}
	public void setRunsToGet(Integer runsToGet) {
		this.runsToGet = runsToGet;
	}
	public Integer getBallsRemaining() {
		return ballsRemaining;
	}
	public void setBallsRemaining(Integer ballsRemaining) {
		this.ballsRemaining = ballsRemaining;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public Boolean getChasedSuccessfully() {
		return chasedSuccessfully;
	}
	public void setChasedSuccessfully(Boolean chasedSuccessfully) {
		this.chasedSuccessfully = chasedSuccessfully;
	}
	public Integer getTotalBatterRuns() {
		return totalBatterRuns;
	}
	public void setTotalBatterRuns(Integer totalBatterRuns) {
		this.totalBatterRuns = totalBatterRuns;
	}
	public Integer getTotalNonStrikerRuns() {
		return totalNonStrikerRuns;
	}
	public void setTotalNonStrikerRuns(Integer totalNonStrikerRuns) {
		this.totalNonStrikerRuns = totalNonStrikerRuns;
	}
	public Integer getBatterBallsFaced() {
		return batterBallsFaced;
	}
	public void setBatterBallsFaced(Integer batterBallsFaced) {
		this.batterBallsFaced = batterBallsFaced;
	}
	public Integer getNonStrikerBallsFaced() {
		return nonStrikerBallsFaced;
	}
	public void setNonStrikerBallsFaced(Integer nonStrikerBallsFaced) {
		this.nonStrikerBallsFaced = nonStrikerBallsFaced;
	}
	public Integer getPlayerOutRuns() {
		return playerOutRuns;
	}
	public void setPlayerOutRuns(Integer playerOutRuns) {
		this.playerOutRuns = playerOutRuns;
	}
	public Integer getPlayerOutBallsFaced() {
		return playerOutBallsFaced;
	}
	public void setPlayerOutBallsFaced(Integer playerOutBallsFaced) {
		this.playerOutBallsFaced = playerOutBallsFaced;
	}
	public Integer getBowlerRunsConceded() {
		return bowlerRunsConceded;
	}
	public void setBowlerRunsConceded(Integer bowlerRunsConceded) {
		this.bowlerRunsConceded = bowlerRunsConceded;
	}
	public Boolean getValidBall() {
		return validBall;
	}
	public void setValidBall(Boolean validBall) {
		this.validBall = validBall;
	}
    
    
    
}
