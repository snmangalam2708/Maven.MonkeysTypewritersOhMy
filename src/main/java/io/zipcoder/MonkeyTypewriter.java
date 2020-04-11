package io.zipcoder;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MonkeyTypewriter {
    public static void main(String[] args) {
        String introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.


        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.

        SafeCopier safe = new SafeCopier(introduction);
        UnsafeCopier unsafe = new UnsafeCopier(introduction);

        List<Thread> safeThread = new ArrayList<>();
        List<Thread> unsafeThread = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            safeThread.add(new Thread(safe));
        }

        for (int i = 0; i < 5; i++) {

            unsafeThread.add(new Thread(unsafe));
        }

        for(Thread t : safeThread){

            t.start();
        }

        for(Thread t : unsafeThread){

            t.start();
        }

        try {
            Thread.sleep(200);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

        // Print out the copied versions here.

        System.out.println("\n Safe threads: " + "\n" + safe.copied);
        System.out.println("\n");
        System.out.println("\n Unsafe threads: " + "\n" + unsafe.copied);
    }
}