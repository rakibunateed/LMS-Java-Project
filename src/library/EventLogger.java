package library;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventLogger {
    private static final String EVENTS_FILE = "data/events.txt";
    private final List<String> events;

    public EventLogger() {
        events = new ArrayList<>();
        loadEvents();
    }

    public void logEvent(String event) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        events.add(timestamp + " - " + event);
        saveEvents();
    }

    public List<String> getEvents() {
        return events;
    }

    private void loadEvents() {
        try (BufferedReader br = new BufferedReader(new FileReader(EVENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                events.add(line);
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private void saveEvents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EVENTS_FILE))) {
            for (String event : events) {
                bw.write(event);
                bw.newLine();
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}

