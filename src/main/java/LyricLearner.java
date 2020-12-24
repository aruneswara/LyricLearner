import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class LyricLearner {

    public static void main(String[] args) {
        InputStream inputStream = Writer.class.getResourceAsStream("/artists.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> artists = new ArrayList<>();

        try {
            while(reader.ready()) {
                String line = reader.readLine();
                System.out.println(line);
                artists.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        artists.remove(0);

        Random rand = new Random();

        while(true)
        {
            tweetLines(artists.get(rand.nextInt(artists.size())));
            try {
                System.out.println("Sleeping for 1 hour...");
                Thread.sleep(3600000); // every 60 minutes
                // Thread.sleep(10000); // every 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void tweetLines(String artist) {
        String line;
        int i = 0;
        Random rand = new Random();
        try {
            try (
                    InputStream fis = new FileInputStream(artist.toLowerCase() + "OUT.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            ) {
                while ((br.readLine()) != null) {
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        i = rand.nextInt(i);
        int z = 0;
        try {
            try (
                    InputStream fis = new FileInputStream(artist.toLowerCase() + "OUT.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            ) {
                while ((line = br.readLine()) != null) {
                    // Deal with the line
                    z++;
                    if(z==i)
                    {
                        if(line.indexOf('-')!=-1  || line.indexOf('&')!=-1)
                        {
                            i++;
                            continue;
                        }
                        sendTweet(line, artist);
                        System.out.println("Tweeting: " + line + "...");
                        break;
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static void sendTweet(String line, String artist) {
        Twitter twitter = TwitterFactory.getSingleton();
        Status status;
        line = line.replaceAll("bitch", "b****");
        line = line.replaceAll("nigga", "n****");
        line = line.replaceAll("nigger", "n*****");
        line = line.replaceAll("cunt", "c***");
        line = line.replaceAll("shit", "s***");
        line = line.replaceAll("asshole", "a******");
        line = line.replaceAll("ass", "a**");
        line = line.replaceAll("dick", "d***");
        line = line.replaceAll("pussy", "p****");
        line = line.replaceAll("cock", "c***");
        line = line.replaceAll("hoe", "h**");
        line = line.replaceAll("whore", "w****");
        line = line.replaceAll("faggot", "f*****");
        line = line.replaceAll("fag", "f**");
        line = line.replaceAll("slut", "s***");
        line = line.replaceAll("motherfucker", "m***********");
        line = line.replaceAll("fucker", "f*****");
        line = line.replaceAll("fuck", "f***");
        line = line.replaceAll("tit", "t**");
        line = line.replaceAll("titties", "t****es");
        line = line.replaceAll("boob", "b***");
        line = line.replaceAll("cum", "c**");


        try {
            status = twitter.updateStatus(artist + " be like \"" + line + "\"");
            System.out.println(status);
        } catch (TwitterException e) {;
            e.printStackTrace();
        }
    }

}