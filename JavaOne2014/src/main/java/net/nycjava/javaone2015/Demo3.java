package net.nycjava.javaone2015;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.LongStream;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

/**
 * Demonstrates:
 * - parallel operations (Streams)
 * - CompletableFuture
 */
public class Demo3 {
    public static void main(String argv[]) throws ExecutionException, InterruptedException {

        long n = new Random(7).
                longs(200, 10000000, 99999999).
                // uncomment the next line to create a stream that uses multiple cores
//                parallel().
                filter(Demo3::isPrime).
                peek(out::println).
                count();

        out.println(format("found %d prime numbers", n));

        sleep(Duration.ofSeconds(5).toMillis());

        CompletableFuture<Long> c1 =
                CompletableFuture.supplyAsync(
                        Demo3::createRandomPrime);
        CompletableFuture<Long> c2 =
                CompletableFuture.supplyAsync(
                        Demo3::createRandomPrime);

        CompletableFuture<Long> c3 =
                c1.thenCombineAsync(c2, (i, j) -> i * j);

        c3.thenApply(productOfTwoPrimes ->
                format("%d is the product of two primes!",
                        productOfTwoPrimes)).
                thenAccept(out::println);

        out.println("now go do something else ...");

        sleep(Duration.ofSeconds(5).toMillis());

        out.println("... done!");
    }

    private static long createRandomPrime() {
        return new Random().
                longs(10000000, 99999999).
                filter(Demo3::isPrime).
                findFirst().
                getAsLong();
    }

    private static boolean isPrime(long candidate) {
        return LongStream.
                rangeClosed(2, candidate - 1).
                noneMatch(n -> candidate % n == 0);
    }
}