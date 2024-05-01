package krd.kurdestan.poemceawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class CrawlerRunner implements CommandLineRunner {
//public class CrawlerRunner  {


    @Autowired
    PoetryAndWriterListCrawler poetryAndWriterListCrawler;


    @Autowired
    BookListCrawler bookListCrawler;


    @Autowired
    BookTableCrawler bookTableCrawler;




    @Autowired
    PoemCrawler poemCrawler;



    @Autowired
    FailRepository failRepository;


    boolean crawl=false;




    @Override
    public void run(String... args) throws Exception {



        if(crawl) {
            System.out.println("Start Crawling");
            List<PoetryWriter> poetryWriters = poetryAndWriterListCrawler.crawl();
      /*      var v1List = List.of(
               "https://books.vejin.net/ck/author/61"
                   *//* "https://books.vejin.net/ck/author/62",
                    "https://books.vejin.net/ck/author/99",
                    "https://books.vejin.net/ck/author/45",
                    "https://books.vejin.net/ck/author/23",
                    "https://books.vejin.net/ck/author/49",
                    "https://books.vejin.net/ck/author/68",
                    "https://books.vejin.net/ck/author/11",
                    "https://books.vejin.net/ck/author/86",
                    "https://books.vejin.net/ck/author/18"*//*



            );


            var filtereddPoetryList = poetryWriters.stream().filter(poetryWriter ->
                    v1List.contains(poetryWriter.link())).toList();*/

            poetryWriters.stream().parallel().forEach(poetryWriter -> {
                try {


                    List<Book> books = bookListCrawler.crawl(poetryWriter.link());
                    books.stream().parallel().forEach(book -> {

                        List<BookTable> bookTable = bookTableCrawler.crawl(book.link());

                        bookTable.stream().parallel().forEach(bookTable1 -> {

                            poemCrawler.crawl(bookTable1.id(), bookTable1.link(), bookTable1.title(), book.name(), poetryWriter.name(), bookTable1.form(), bookTable1.meter());


                        });


                    });

                    System.out.println(poetryWriter.name() + "-Success-" + poetryWriter.id());


                } catch (Exception e) {
                    FailEntity failEntity = new FailEntity();
                    failEntity.setName(poetryWriter.name());
                    failEntity.setUrl(poetryWriter.link());
                    failEntity.setCause(e.toString());
                    failRepository.save(failEntity);
                    System.out.println(poetryWriter.name() + "-Failed-" + poetryWriter.link());


                }
            });
            System.out.println("Finished Crawling");

            // bookTableCrawler.crawl("https://books.vejin.net/ck/book/28");

        }
    }
}
