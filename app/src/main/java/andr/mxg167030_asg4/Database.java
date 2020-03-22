package andr.mxg167030_asg4;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Database {
    static PriorityQueue<HighScore> highScores;
    private static File scores_file;
    private static String scores_filename = "scores.txt";

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a, zzzz");

    public static void initializeDatabase(Context context){
        readScoresFromFile(context);
    }

    public static void addHighScore(Context context, HighScore hs){
        highScores.add(hs);

        while (highScores.size() > 12){
            highScores.poll();
        }
        System.out.println("*******************7" + highScores.toString());

        writeScoresToFile(context);
    }

    public static ArrayList<HighScore> getScores(Context context){
        readScoresFromFile(context);

        ArrayList list = new ArrayList<>();
        list.addAll(highScores);
        Collections.reverse(list);

        return list;
    }

    private static void writeScoresToFile(Context context)
    {
        System.out.println("***************" + highScores.toString());
        try
        {
            context.deleteFile(scores_filename);
            FileOutputStream score_file = context.openFileOutput(scores_filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(score_file);

            for(HighScore hs : highScores)
            {
                writer.write(hs.getName() + "\t");
                writer.write(dateFormat.format(hs.getDate()) + "\t");
                writer.write(hs.getScore() + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            //TODO: something something handle
        }
    }

    private static void readScoresFromFile(Context context){
        highScores = new PriorityQueue<>();

        try {
            FileInputStream inputStream = new FileInputStream(scores_filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try
        {
            scores_file = new File(context.getFilesDir(), scores_filename);
            Scanner scores = new Scanner(scores_file);
            String entry = null;
            String[] data = null;
            String name = null;
            Date date = null;
            int score = 0;
            while (scores.hasNext()){
                entry = scores.nextLine();
                data = entry.split("\t");
                name = data[0];
                date = dateFormat.parse(data[1]);
                score = Integer.parseInt(data[2]);
                highScores.add(new HighScore(name, date, score));
            }
            while (highScores.size() > 12){
                highScores.poll();
            }
        }
        catch (Exception ex)
        {
            //TODO: Handle the error
        }
    }





}
