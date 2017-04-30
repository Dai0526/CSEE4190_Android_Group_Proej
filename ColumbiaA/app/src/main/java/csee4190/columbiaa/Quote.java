package csee4190.columbiaa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.util.Scanner;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Quote {

    public final List<String> readCsv(String FilePath) {
        List<String> quoteList = new ArrayList<String>();

        try {

            String[] line;
            Scanner scanner = new Scanner(new File(FilePath));
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                quoteList.add(scanner.next());
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return quoteList;
    }

    public class quoteResult {
        HashMap<Date,String> author;
        HashMap<Date,String> quote;
    }


    public quoteResult buildHash(){
        HashMap<Date,String> hm_author=new HashMap<Date,String>();
        HashMap<Date,String> hm_quote=new HashMap<Date,String>();
        List<String> quoteList = readCsv("quote.csv");
        for (int i = 0; i < (quoteList.size()/4); i=i+4) {
            String quoteID = quoteList.get(i);
            String category = quoteList.get(i+1);
            String author = quoteList.get(i+2);
            String quote = quoteList.get(i+3);
            Date temp_date = new Date();
            hm_author.put(temp_date,author);
            hm_quote.put(temp_date,quote);
        }
        quoteResult result = new quoteResult();
        result.author = hm_author;
        result.quote = hm_quote;
        return result;
    }



}
