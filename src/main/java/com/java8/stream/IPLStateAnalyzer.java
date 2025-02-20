package com.java8.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java8.stream.entity.Ipl;

public class IPLStateAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(IPLStateAnalyzer.class);

    public static void main(String[] args) {
    	
    	IPLStateAnalyzer iplStateAnalyzer = new IPLStateAnalyzer();
       
        logger.info("Loading CSV data into database...");

        List<Ipl> records = iplStateAnalyzer.loadMatchRecords("ball_by_ball_ipl.csv");
        logger.info("CSV data successfully loaded! {} entries found.", records.size());

        logger.info("============================================");
		logger.info("           Calculating IPL Statistics           ");
		logger.info("============================================");


		iplStateAnalyzer.totalRunsScoredByPlayerAndYear(records, "RG Sharma");
		iplStateAnalyzer.totalRunsScoredByPlayerAndYear(records, "V Kohli");
		iplStateAnalyzer.totalRunsScoredByPlayerAndYear(records, "CH Gayle");
		iplStateAnalyzer.totalRunsScoredByPlayerAndYear(records, "MS Dhoni");


    }  
    
    private List<Ipl> loadMatchRecords(String fileName) {
    	List<Ipl> entities = new ArrayList<>();
        try (InputStream inputStream = IPLStateAnalyzer.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); //Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                	Ipl event = new Ipl();
                    event.setMatchId(Long.parseLong(data[1]));
                    event.setMatchDate(LocalDate.parse(data[2])); // Ensure date format matches
                    event.setVenue(data[3]);
                    event.setBatFirst(data[4]);
                    event.setBatSecond(data[5]);
                    event.setInnings(Integer.parseInt(data[6]));
                    event.setOverNumber(Integer.parseInt(data[7]));
                    event.setBallNumber(Integer.parseInt(data[8]));
                    event.setBatter(data[9]);
                    event.setNonStriker(data[10]);
                    event.setBowler(data[11]);
                    event.setBatterRuns(Integer.parseInt(data[12]));
                    event.setExtraRuns(Integer.parseInt(data[13]));
                    event.setRunsFromBall(Integer.parseInt(data[14]));
                    event.setBallRebowled(Boolean.parseBoolean(data[15]));
                    event.setExtraType(data[16]);
                    event.setDismissalMethod(data[18]);
                    event.setPlayerOut(data[19]);
                    event.setWinner(data[25]);
                    entities.add(event);
                }            
        } catch (IOException | NullPointerException e) {
        	logger.error("Error reading CSV file from resources", e);
        }
        return entities;
    }
    
    
    private void totalRunsScoredByPlayerAndYear(List<Ipl> records, String playerName) {
        logger.info("============================================");
        logger.info("Total Runs Scored by {} by Year", playerName);

        Map<Integer, Integer> totalByYear = records.stream()
                .filter(record -> record.getBatter().equalsIgnoreCase(playerName)) // Filter by player
                .collect(Collectors.groupingBy(
                        record -> record.getMatchDate().getYear(), // Group by year
                        TreeMap::new, // Ensures sorting by year
                        Collectors.summingInt(Ipl::getBatterRuns) // Sum runs
                ));

        totalByYear.forEach((year, sum) ->
                logger.info("Year: {}, Total Score: {}", year, sum)
        );
        logger.info("============================================");
    }
}