package krd.kurdestan.poemceawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PoetryAndWriterListCrawler {



    public  List<PoetryWriter> crawl() {
        List<PoetryWriter> poetryWriters = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect("https://books.vejin.net/ck/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements authorList = doc.select("div#authorsList-year");

        authorList.forEach(author -> {

            Elements cardElements=author.getElementsByClass("card");
        cardElements.forEach(cardElement -> {
       var aTags=  cardElement.getElementsByClass("card").get(0);

       var link=aTags.attr("abs:href");
     var name=  aTags.getElementsByClass("header").get(0).text();
     var year=  aTags.getElementsByClass("meta").get(0).text();
      var id=Integer.parseInt(link.split("/")[link.split("/").length-1]);


    // System.out.println("id="+id+"---name"+name+"-year="+year+"---link="+link);

     poetryWriters.add(new PoetryWriter(id,name,year,link));

           // System.out.println("me="+cardElements.attr("a[href]"));
          //  System.out.println("me="+cardElement);

        });

            //System.out.println(cardElements);
            //authorsList-year
        });
return poetryWriters;
    }
}
