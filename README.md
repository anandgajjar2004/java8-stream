Analyzing Virat Kohli’s Centuries Using Java 8 Streams 🚀
Cricket statistics provide fascinating insights into a player's dominance over the years. With the power of Java 8 Streams, we can efficiently process large datasets and extract valuable insights without complex loops.
In this blog, we’ll analyze Virat Kohli’s century records to compute total runs, highest individual scores, and performance breakdowns by format, opponent, stadium, and year using functional programming in Java.

🏏 Key Objectives: Extracting Cricket Insights
Using a CSV file containing Virat Kohli’s centuries, our goal is to compute the following insights:
🏆 Total Runs Scored by All Centuries
🎯 10,624 Runs
🔥 Highest Individual Score
🎯 254 Runs
📊 Total Runs by Format
Format	Total Runs
ODI	6,030
T20	122
Test	4,472
⚔️ Total Runs Against Each Opponent
Opponent	Total Runs
Afghanistan	122
Australia	2,103
Bangladesh	894
England	1,098
New Zealand	1,152
Pakistan	412
South Africa	1,166
Sri Lanka	2,007
West Indies	1,555
Zimbabwe	115
🎯 Matches Played Against Each Opponent
Opponent	Matches Played
New Zealand	9
Afghanistan	1
Bangladesh	7
Pakistan	3
Sri Lanka	15
West Indies	12
England	8
Zimbabwe	1
South Africa	8
Australia	17
🏟️ Stadium with the Most Matches Played
🏟️ Adelaide Oval – 5 Matches
📅 Year with the Most Centuries Scored
📅 2018 – 💯 11 Centuries


Step 1: Loading Data from CSV
First, we'll load the CSV file using BufferedReader and store the data in a list for efficient processing.

private List<MatchRecord> loadMatchRecords(String fileName) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(
            new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8))) {
        return br.lines()
                .skip(1) // Skip header
                .map(this::parseMatchRecord)
                .collect(Collectors.toList());
    } catch (Exception e) {
        logger.error("Error loading CSV file: {}", e.getMessage());
        return List.of();
    }
}

private MatchRecord parseMatchRecord(String line) {
    String[] data = line.split(",");
    MatchRecord match = new MatchRecord();
    match.setFormat(data[1]);  // ODI / T20 / Test
    match.setScore(Integer.parseInt(data[2]));
    match.setOpponent(data[3]);
    match.setStadium(data[4]);
    match.setLocation(data[5]);
    match.setDate(data[7]);
    return match;
}

Step 2: Processing Data Using Java Streams
Once the data is loaded, we utilize Java Streams to compute statistics efficiently.
1. Calculate Total Runs from All Centuries
int totalRuns = records.stream().mapToInt(MatchRecord::getScore).sum();

2. Find the Highest Individual Score
OptionalInt maxRun = records.stream().mapToInt(MatchRecord::getScore).max();

3. Group Total Runs by Format (ODI, T20, Test)
Map<String, Integer> totalByFormat = records.stream()
            .collect(Collectors.groupingBy(MatchRecord::getFormat, Collectors.summingInt(MatchRecord::getScore)));
    totalByFormat.forEach((format, sum) -> logger.info("Format: {}, Total Score: {}", format, sum));

4. Group Total Runs by Opponent
Map<String, Integer> totalByOpponent = records.stream()
            .collect(Collectors.groupingBy(MatchRecord::getOpponent, Collectors.summingInt(MatchRecord::getScore)));
    totalByOpponent.forEach((opponent, totalScore) ->
            logger.info("⚔️ Opponent: {}, 🏏 Total Score: {}", opponent, totalScore));

5. Count Matches Played Against Each Opponent
    Map<String, Long> matchesPlayedByOpponent = records.stream()
            .collect(Collectors.groupingBy(MatchRecord::getOpponent, Collectors.counting()));
    matchesPlayedByOpponent.forEach((opponent, totalMatches) ->
            logger.info("⚔️ Opponent: {}, 🎯 Matches Played: {}", opponent, totalMatches));

6. Find the Stadium with Maximum Matches Played
    records.stream()
            .collect(Collectors.groupingBy(MatchRecord::getStadium, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(entry -> logger.info("🏟️ Stadium: {}, 🎯 Matches Played: {}", entry.getKey(), entry.getValue()));

7. Find the Year with Maximum Centuries Scored
    records.stream()
            .collect(Collectors.groupingBy(match -> match.getDate().split(" ")[2], Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(entry -> logger.info("📅 Year: {}, 💯 Centuries Scored: {}", entry.getKey(), entry.getValue()));
    
Conclusion
With Java 8 Streams, analyzing large cricket datasets becomes both efficient and intuitive. This approach can be extended to other cricket legends and performance metrics. Try it out and uncover fascinating insights! 🏏🚀
