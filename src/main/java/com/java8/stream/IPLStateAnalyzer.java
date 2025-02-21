package com.java8.stream;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java8.stream.entity.Ipl;

public class IPLStateAnalyzer {

    public static final String SEPARATOR = "============================================";

    private static final Logger logger = LoggerFactory.getLogger(IPLStateAnalyzer.class);

    public static void main(String[] args) {
        IPLStateAnalyzer iplStateAnalyzer = new IPLStateAnalyzer();

        logger.info("Loading CSV data into database...");

        List<Ipl> records = iplStateAnalyzer.loadMatchRecords("ball_by_ball_ipl.csv");
        logger.info("CSV data successfully loaded! {} entries found.", records.size());

        logger.info(SEPARATOR);
        logger.info("           Calculating IPL Statistics           ");
        logger.info(SEPARATOR);

        iplStateAnalyzer.totalRunsScoredByPlayerPerYear(records, "RG Sharma");
        iplStateAnalyzer.totalRunsScoredByPlayerPerYear(records, "V Kohli");
        iplStateAnalyzer.totalRunsScoredByPlayerPerYear(records, "CH Gayle");
        iplStateAnalyzer.totalRunsScoredByPlayerPerYear(records, "MS Dhoni");

        iplStateAnalyzer.getDismissalCountForPlayer(records, "MS Dhoni");
        iplStateAnalyzer.getDismissalCountForPlayer(records, "CH Gayle");

        iplStateAnalyzer.top10DismissalsbyType(records, "Caught");
        iplStateAnalyzer.top10DismissalsbyType(records, "bowled");
        iplStateAnalyzer.top10DismissalsbyType(records, "lbw");
        iplStateAnalyzer.top10DismissalsbyType(records, "run out");
        iplStateAnalyzer.top10DismissalsbyType(records, "stumped");

        iplStateAnalyzer.top10BowlerwithExtra(records, "['wides']");
        iplStateAnalyzer.top10BowlerwithExtra(records, "['noballs']");
        iplStateAnalyzer.top10BowlerwithExtra(records, "['legbyes']");

    }

    private List<Ipl> loadMatchRecords(String fileName) {
        List<Ipl> entities = new ArrayList<>();
        try (InputStream inputStream = IPLStateAnalyzer.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file not found: " + fileName);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // NOSONAR
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
            }
        } catch (IOException | NullPointerException e) {
            logger.error("Error reading CSV file from resources", e);
        }
        return entities;
    }


    private void totalRunsScoredByPlayerPerYear(List<Ipl> recordList, String playerName) {
        logger.info(SEPARATOR);
        logger.info("Total Runs Scored by {} Per Year", playerName);

        Map<Integer, Integer> totalByYear = recordList.stream()
                .filter(entry -> entry.getBatter().equalsIgnoreCase(playerName)) // Filter by player
                .collect(Collectors.groupingBy(
                        entry -> entry.getMatchDate().getYear(), // Group by year
                        TreeMap::new, // Ensures sorting by year
                        Collectors.summingInt(Ipl::getBatterRuns) // Sum runs
                ));

        totalByYear.forEach((year, sum) ->
                logger.info("Year: {}, Total Score: {}", year, sum)
        );
        logger.info(SEPARATOR);
    }

    private void getDismissalCountForPlayer(List<Ipl> recordList, String playerName) {
        logger.info(SEPARATOR);
        logger.info("Dismissal of {} by Type ", playerName);

        // Counting dismissals for the given player
        Map<String, Long> dismissalCount = recordList.stream()
                .filter(data -> data.getBatter().equals(playerName) && data.getDismissalMethod() != null && !data.getDismissalMethod().isEmpty()) // Filter for specific player
                .collect(Collectors.groupingBy(Ipl::getDismissalMethod, Collectors.counting()));

        // Printing result
        dismissalCount.forEach((method, count) ->
                logger.info("{} :\t{} times",method ,count)
        );

        logger.info(SEPARATOR);
    }

    private void top10DismissalsbyType(List<Ipl> recordList, String type) {
        logger.info(SEPARATOR);
        logger.info("Top 10 Players Dismissal by {} ", type);


        Map<String, Long> result =  recordList.stream()
                    .filter(data -> type.equalsIgnoreCase(data.getDismissalMethod()) && data.getBatter() != null) // Filter Caught dismissals
                    .collect(Collectors.groupingBy(Ipl::getBatter, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10) // Sort by value in descending order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        result.forEach((player, count) ->
                logger.info("{} :\t{} Times", player, count)
        );
    }

    private void top10BowlerwithExtra(List<Ipl> recordList, String type) {
        logger.info(SEPARATOR);
        logger.info("Top 10 Bowler Extra Run by {} ", type);


        Map<String, Long> result =  recordList.stream()
                .filter(data -> type.equalsIgnoreCase(data.getExtraType()) && data.getBowler() != null) // Filter Caught dismissals
                .collect(Collectors.groupingBy(Ipl::getBowler, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10) // Sort by value in descending order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        result.forEach((player, count) ->
                logger.info("{}:\t{} Times", player, count)
        );
    }
}