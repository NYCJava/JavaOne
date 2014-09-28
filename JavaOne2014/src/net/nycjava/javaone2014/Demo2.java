package net.nycjava.javaone2014;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.LocalDate.now;
import static java.time.Month.SEPTEMBER;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Stream.iterate;

/**
 * Demonstrates:
 * - Java Date
 * - Streams
 * - collecting
 * - method references
 * - Optional
 */
public class Demo2 {
    private final static EnumSet<DayOfWeek> WEEKEND = EnumSet.of(SATURDAY, SUNDAY);

    public static void main(String argv[]) throws IOException {

        LocalDate birthday =
                now().
                withMonth(SEPTEMBER.getValue()).
                withDayOfMonth(16);

        Map<DayOfWeek, Long> distributionOfBirthdaysByDayOfWeek =
                iterate(birthday, d -> d.plusYears(1)).
                limit(100).
                filter(date -> !WEEKEND.contains(date.getDayOfWeek())).
                peek(out::println).
                collect(
                        groupingBy(LocalDate::getDayOfWeek,
                                counting()));

        out.println(format(
                "number of Mondays is %d",
                distributionOfBirthdaysByDayOfWeek.get(MONDAY)));

        Optional<DayOfWeek> leastFrequentDayOptional =
                distributionOfBirthdaysByDayOfWeek.
                entrySet().
                stream().
                sorted(Entry.comparingByValue()).
                map(Entry::getKey).
                findFirst();

        leastFrequentDayOptional.ifPresent(
                leastFrequentDay -> out.println(
                        format(
                                "least frequent day is %s",
                                leastFrequentDay))
        );
    }
}