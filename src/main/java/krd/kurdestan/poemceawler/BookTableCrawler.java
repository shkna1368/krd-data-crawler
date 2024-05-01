package krd.kurdestan.poemceawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookTableCrawler {

    public List<BookTable> crawl(String url) {



List<BookTable> books = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements bookList = doc.select("div[class=toc],div[id=toc]");
        bookList.forEach(author -> {

           // Elements cardElements = author.select("toc-item minimal");
            author.children().forEach(cardElement -> {
               // var aTags = cardElement.getElementsByClass("book").get(0);
                var link=cardElement.attr("abs:href");
                var id=Long.parseLong(link.split("/")[link.split("/").length-1]);
                var bookContent = cardElement.getElementsByClass("toc-title").get(0);
                var bookDesc = cardElement.getElementsByClass("toc-desc").get(0);

                var title=bookContent.getElementsByTag("h3").get(0).text();
                String form=null;
                String meter=null;


              try {
                  form =bookDesc.getElementsByClass("tdesc form").get(0).text();
                  meter=bookDesc.getElementsByClass("tdesc meter").get(0).text();

              }
              catch (Exception e){}


            //  System.out.println(link+"----"+title+"-bname:-"+descForm+"--bdesc:-"+descMeter);

           books.add(new BookTable(id,title,form,meter,link));

            });
        });
return books;

    }

}
