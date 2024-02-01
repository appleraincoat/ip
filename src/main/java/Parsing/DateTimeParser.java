package Parsing;

import Exceptions.YpxmmException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    protected DateTimeFormatter formatter;
    public DateTimeParser() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }
    public LocalDateTime parseDateTime(String timeString) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(timeString, this.formatter);
        return parsedDateTime;
    }
}
