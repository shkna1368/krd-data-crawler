package krd.kurdestan.poemceawler;

import krd.kurdestan.poemceawler.ui.PoemTagService;
import krd.kurdestan.poemceawler.ui.PoemTageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PoemCrawler {
@Autowired
PoemRepository poemRepository;

@Autowired
PoemTagService poemTagService;


    public void crawl(Long id,String url,String title,String bookName,String authorName,String form,String meter) {
List<PoemEntity> poems = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        String mainTitle=doc.select("div.text-header").first().children().select("h1.title").text();

        Elements bookList = doc.select("div.textcontent");

        StringBuilder stringBuilder=new StringBuilder();
        bookList.forEach(author -> {

            Elements cardElements = author.select("div.H.R,div.H.L");

            String type="Beyt";
                if (cardElements.size()==0) {
                   cardElements = author.select("div.textcontent.rtl");
                     type="No";
                           }




            Poem p1=  new Poem(id,null,mainTitle,bookName,authorName,form,meter,url,null,null,null);
            PoemEntity  poemEntity=new PoemEntity(p1);
            String finalType = type;
            cardElements.forEach(cardElement -> {
                String  content=null;
if (finalType.equals("Beyt")) {
    content = cardElement.text();
    stringBuilder.append(content+"\n");
    poems.add(poemEntity);

}
else if (finalType.equals("No")) {

    StringBuilder builder1=new StringBuilder();

    cardElement.childNodes().forEach(node ->{


String value=node.toString();
        //if(node.toString().contains("<br>"))


         value=  value.replace("\n","").replace("<br>","").replace("<div> &nbsp;</div>","").replace("<div></div>","");

                String plainText = Jsoup.parse(value).text();


                builder1.append(plainText+"\n");}

    );


/*    for (int i=0;i<cardElement.childNodeSize();i++){

        builder1.append(cardElement.childNodes(i)+"\n");
    }*/
   /* cardElement.children().forEach(element -> {
        builder1.append(element.text()+"\n");

    });*/

    stringBuilder.append(builder1.toString()+"\n");
    poems.add(poemEntity);


}




             //  System.out.println(content);
               // public record Poem(String content,String title,String bookName,String authorName,String form,String meter,String link) {





            });
//poems.forEach(poemEntity -> poemEntity.setCompleteContent(stringBuilder.toString()));
            String limitter='\n'+"************"+'\n';
String poemTitle="شیعر:"+poemEntity.getTitle()+'\n';
String poemBook="لە کتێبی:"+poemEntity.getBookName()+'\n';
String fromBook="بەرهەمی:"+poemEntity.getAuthorName()+'\n';

stringBuilder.append(limitter);
stringBuilder.append(poemTitle);
stringBuilder.append(poemBook);
stringBuilder.append(fromBook);
stringBuilder.append(limitter);





           var cmpContent= stringBuilder.toString();
            if (cmpContent != null && !cmpContent.trim() .equals("") ) {
                poemEntity.setCompleteContent(cmpContent);

                String tags=saveTags(poemEntity);
                poemEntity.setTag(tags);
 /*             List<PoemEntity> poemEntities=new ArrayList<>();
                 tags.forEach(tag -> {
                     poemEntity.setTag(tag);
                     PoemEntity p=new PoemEntity(poemEntity);
                     poemEntities.add(p);

                 });
*/
        //     poemRepository.save(poemEntity);

              /*  String tempKrd3="دەتوانی شیعرێک لە کتێبی" ;
                String tempKrd4="بڵێیت" ;

                String s1=tempKrd3+" "+poemEntity.getBookName()+" "+tempKrd4;*/



         /*       String tempKrd1="دەتوانی شیعرێکم بۆ بنووسیت دەربارەی" ;
                String tempKrd1="دەتوانی شیعرێکم بۆ بنووسیت دەربارەی" ;*/
                String tempKrd2="دەتوانی بە شێوازی" ;
                String tempKrd6="شیعرێکم بۆ بنووسیت" ;
                String tempKrd7="شیعری" ;
                String tempKrd8="لە کتێبی";

                String str=tempKrd2+" "+poemEntity.getAuthorName()+" "+tempKrd7+" "+poemEntity.getTitle() +" "+tempKrd8+" "+poemEntity.getBookName()+" "+tempKrd6;

                poemEntity.setInstruct(str);
                PoemEntity p2=new PoemEntity(poemEntity);


                poemRepository.save(p2);


/*
                String s2=tempKrd2+" "+poemEntity.getAuthorName()+" "+tempKrd6;
                poemEntity.setInstruct(s2);
                PoemEntity p3=new PoemEntity(poemEntity);

                poemRepository.save(p3);


                String tempKrd7="لە کتێبی";
                String s3=    tempKrd2+" "+poemEntity.getAuthorName() +" "+tempKrd7+" "+poemEntity.getBookName()+" "+tempKrd6;;

                poemEntity.setInstruct(s3);
                PoemEntity p4=new PoemEntity(poemEntity);
                poemRepository.save(p4);*/






                //  poemRepository.save(poemEntity);
         //    saveTags(poemEntity);
            }


        });


    }



    public String saveTags(PoemEntity poemEntity){


      //  List<String> stringList=new ArrayList<>();
        String tempKrd1="دەتوانی شیعرێکم بۆ بنووسیت دەربارەی" ;
        String tempKrd2="بە شێوازی" ;
        String tempKrd3="دەتوانی شیعرێک لە کتێبی" ;
        String tempKrd4="بڵێیت" ;
        String tempKrd5="دەتوانی بە" ;
        String tempKrd6="شیعرێک بنووسیت" ;
        String tempKrd7="شیعرێک دەربارەی" ;
        String tempKrd8="شیعر لە کتێبی" ;
        String tempKrd9="شیعرێکی" ;
        String tempPrs1="میشه برای من شعری در مورد";
        String tempPrs2=" میتونی برای من شعری در مورد";
        String tempPrs3="شعر بنویسی";
        String tempPrs4="شعر بگی";
        String tempPrs5="شعر در مورد";
        String tempPrs6="میتونی با";
        String tempPrs7="شعری از";



        StringBuilder stringBuilder=new StringBuilder();
        String[] arr = poemEntity.getCompleteContent().split(" ");

        for ( String ss : arr) {

            if(ss!=null ||!ss.trim().equals("")){
                if(ss.trim().length()>2){

stringBuilder.append(ss.trim()+"،");
/*
                  stringList.add(tempKrd1+" " +ss ) ;
                    stringList.add(tempKrd5+" " +ss+ " " +tempKrd6) ;
                    stringList.add(tempPrs5+" " +ss) ;

                    stringList.add(tempPrs6+" " +ss+ " " +tempPrs3) ;*/

                }
                // builder.append(ss+'\n'+"#"+'\n');}
            }
        }












      /*  String str1=tempKrd1+" "+poemEntity.getTitle();
        stringList.add(str1);

        String str2=tempKrd1+" "+poemEntity.getTitle()+" "+tempKrd2+" "+poemEntity.getAuthorName();

        stringList.add(str2);
        String str3=tempKrd3+" "+poemEntity.getBookName()+" "+tempKrd4;
        stringList.add(str3);

        String str4=tempKrd5+" "+poemEntity.getBookName()+"،"+tempKrd6;
        stringList.add(str4);

        String str5=tempKrd7+" "+poemEntity.getBookName();
        stringList.add(str5);

        String str6=tempKrd8+" "+poemEntity.getBookName();
        stringList.add(str6);

        String str7=tempKrd9+" "+poemEntity.getAuthorName();
        stringList.add(str7);

        String str8=tempPrs1+" "+poemEntity.getTitle()+" "+tempPrs3;
        stringList.add(str8);

        String str9=tempPrs1+" "+poemEntity.getTitle()+" "+tempPrs4;
        stringList.add(str9);


        String str10=tempPrs2+" "+poemEntity.getTitle()+" "+tempPrs3;
        stringList.add(str10);

        String str11=tempPrs2+" "+poemEntity.getTitle()+" "+tempPrs4;
        stringList.add(str11);


        String str12=tempPrs5+" "+poemEntity.getTitle();
        stringList.add(str12);

        String str13=tempPrs6+" "+poemEntity.getTitle()+" "+tempPrs3;
        stringList.add(str13);


        String str14=tempPrs6+" "+poemEntity.getTitle()+" "+tempPrs4;
        stringList.add(str14);


        String str15=tempPrs7+" "+poemEntity.getAuthorName();
        stringList.add(str15);
*/


/*
        Map<String,String> data=new HashMap<>();

                data.put("poemId",String.valueOf(poemEntity.getPbId()));
                data.put("tags",stringBuilder.toString());

                poemTagService.saveTags(data);*/

        return stringBuilder.toString();

            }










}
