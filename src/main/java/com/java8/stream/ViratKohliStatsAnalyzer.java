package com.java8.stream;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java8.stream.entity.MatchRecord;

public class ViratKohliStatsAnalyzer {

	private static final Logger logger = LoggerFactory.getLogger(ViratKohliStatsAnalyzer.class);

	public static void main(String[] args) {
		
		logger.info("Loading CSV data into database...");

		ViratKohliStatsAnalyzer viratKohliStatsAnalyzer =  new ViratKohliStatsAnalyzer();
		
		List<MatchRecord> records = viratKohliStatsAnalyzer.loadMatchRecords("virat_kohli.csv");
		logger.info("CSV data successfully loaded! {} entries found.", records.size());


		logger.info("============================================");
		logger.info("           Calculating Statistics           ");
		logger.info("============================================");
		viratKohliStatsAnalyzer.sumofAllCenturies(records);
		viratKohliStatsAnalyzer.maximumScorefromSCenturies(records);
		viratKohliStatsAnalyzer.groupAndLogTotalByFormat(records);
		viratKohliStatsAnalyzer.groupByOpponentAndSummingTotalScores(records);
		viratKohliStatsAnalyzer.groupByOpponentAndCountingMatchesPlayed(records);
		viratKohliStatsAnalyzer.findStadiumWithMaxMatches(records);
		viratKohliStatsAnalyzer.findYearWithMaxCenturies(records);

	}
	
	 private List<MatchRecord> loadMatchRecords(String fileName) {
	    	List<MatchRecord> entities = new ArrayList<>();
	        try (InputStream inputStream = IPLStateAnalyzer.class.getClassLoader().getResourceAsStream(fileName);
	             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	            String line;
	            br.readLine(); //Skip header
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                MatchRecord match = new MatchRecord();
	        		match.setFormat(data[1]);  // ODI / T20
	        		match.setScore(Integer.parseInt(data[2]));
	        		match.setOpponent(data[3]);
	        		match.setStadium(data[4]);
	        		match.setLocation(data[5]);
	        		match.setDate(data[7]);
	                entities.add(match);
	                }            
	        } catch (IOException | NullPointerException e) {
	        	logger.error("Error reading CSV file from resources", e);
	        }
	        return entities;
	 }

	private void sumofAllCenturies(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Calculating Total Runs Scored");
		int totalRuns = records.stream().mapToInt(MatchRecord::getScore).sum();
		logger.info("🏏 Total Runs Scored: {}", totalRuns);
		logger.info("--------------------------------------------");
	}

	private void maximumScorefromSCenturies(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Fetching Maximum Score from All Centuries");
		OptionalInt maxRun = records.stream().mapToInt(MatchRecord::getScore).max();
		logger.info("🔥 Highest Individual Score: {}", maxRun.isPresent() ? maxRun.getAsInt() : "N/A");
		logger.info("--------------------------------------------");

	}

	private void groupAndLogTotalByFormat(List<MatchRecord> records) {
		logger.info("Grouping Sum of Score by Format");
		Map<String, Integer> totalByFormat = records.stream()
				.collect(Collectors.groupingBy(MatchRecord::getFormat, TreeMap::new, Collectors.summingInt(MatchRecord::getScore)));
		totalByFormat.forEach((format, sum) -> logger.info("Format: {}, Total Score: {}", format, sum));
	}

	private void groupByOpponentAndSummingTotalScores(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Grouping Total Scores by Opponent");
		Map<String, Integer> totalByOpponent = records.stream()
				.collect(Collectors.groupingBy(MatchRecord::getOpponent, TreeMap::new, Collectors.summingInt(MatchRecord::getScore)));
		totalByOpponent.forEach((opponent, totalScore) -> logger.info("⚔️ Opponent: {}, 🏏 Total Score: {}", opponent, totalScore));
		logger.info("--------------------------------------------");
	}

	private void groupByOpponentAndCountingMatchesPlayed(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Grouping Matches Played by Opponent");
		Map<String, Long> matchesPlayedByOpponent = records.stream()
				.collect(Collectors.groupingBy(MatchRecord::getOpponent, Collectors.counting()));
		matchesPlayedByOpponent.forEach((opponent, totalMatches) ->
				logger.info("⚔️ Opponent: {}, 🎯 Matches Played: {}", opponent, totalMatches));
		logger.info("--------------------------------------------");
	}

	private void findStadiumWithMaxMatches(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Finding the Stadium where Maximum Matches were Played");

		Map<String, Long> matchesByStadium = records.stream()
				.collect(Collectors.groupingBy(MatchRecord::getStadium, Collectors.counting()));

		matchesByStadium.entrySet().stream()
				.max(Map.Entry.comparingByValue()) // Get the stadium with max matches
				.ifPresent(entry -> logger.info("🏟️ Stadium: {}, 🎯 Matches Played: {}", entry.getKey(), entry.getValue()));

		logger.info("--------------------------------------------");
	}

	private void findYearWithMaxCenturies(List<MatchRecord> records) {
		logger.info("--------------------------------------------");
		logger.info("Finding the Year with Maximum Centuries");

		Map<String, Long> centuriesByYear = records.stream()
				.collect(Collectors.groupingBy(
						match -> match.getDate().split(" ")[2], // Extract year from date
						Collectors.counting()));

		centuriesByYear.entrySet().stream()
				.max(Map.Entry.comparingByValue()) // Get the year with max centuries
				.ifPresent(entry -> logger.info("📅 Year: {}, 💯 Centuries Scored: {}", entry.getKey(), entry.getValue()));
		logger.info("--------------------------------------------");
	}
}