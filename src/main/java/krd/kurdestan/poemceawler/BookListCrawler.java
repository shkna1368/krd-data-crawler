package krd.kurdestan.poemceawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookListCrawler {

    public List<Book>  crawl(String url) {
List<Book> books = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements bookList = doc.select("div#booksList");
        bookList.forEach(author -> {

            Elements cardElements = author.getElementsByClass("book");
            cardElements.forEach(cardElement -> {
                var aTags = cardElement.getElementsByClass("book").get(0);
                var link=aTags.attr("abs:href");
                var id=Integer.parseInt(link.split("/")[link.split("/").length-1]);
                var bookContent = aTags.getElementsByClass("book-content").get(0);

                var bookName=bookContent.getElementsByTag("h3").get(0).text();
                var bookDesc=bookContent.getElementsByClass("book-desc").get(0).text();
             //   System.out.println(link+"----"+bookContent.text()+"-bname:-"+bookName+"--bdesc:-"+bookDesc);

                books.add(new Book(id,bookName,bookDesc,link));

            });
        });

return books;
    }

}
