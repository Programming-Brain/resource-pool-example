package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        VotingService votingService = VotingService.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        log(votingService.getRatings());

        IntStream.rangeClosed(1, 1000000)
                .mapToObj(i -> (Runnable) () -> {
                    log("Voting");
                    votingService.vote("Java");
                })
                .forEach(executorService::submit);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        log(votingService.getRatings());
        votingService.shutdown();
    }

    private static void log(String message) {
        System.out.println(Thread.currentThread().getName() + " - " + message);
    }

}
