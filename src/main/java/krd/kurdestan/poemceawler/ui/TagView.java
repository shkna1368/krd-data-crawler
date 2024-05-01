package krd.kurdestan.poemceawler.ui;



import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import krd.kurdestan.poemceawler.PoemEntity;
import krd.kurdestan.poemceawler.PoemService;

import java.util.HashMap;
import java.util.Map;


@Route("/tags")
public class TagView extends VerticalLayout {

    long currentId=0;
    long currentPoemId=0;
/*
    private    Long id;
    private    String content;
    private String title;
    private String bookName;
    private String authorName;
    private String form;
    private String meter;
    private String link;
    private String completeContent*/




TextArea textAreaTitle;
TextArea textAreaBookName;
TextArea textAreaAuthorName;
    TextArea textAreaForm;
    TextArea textAreaMeter;
Text textAreaLink;
TextArea textAreaCompleteContent;

Button btnPoemUpdate;
Button btnNext;

    TextArea textAreaTags;
    Button btnSaveTags;

    PoemService poemService;
    PoemTagService poemTagService;

    VerticalLayout actions2;
    VerticalLayout actions3;
    HorizontalLayout horizontalLayout;
    UI ui;

    TextArea textAreaCurrentFromTotal;


    public TagView(PoemService poemService,PoemTagService poemTagService,UI ui) {
        this.ui=ui;
        this.poemService=poemService;
        this.poemTagService=poemTagService;
        this.btnPoemUpdate = new Button("Update poem");
        this.btnNext = new Button("Next poem");
        this.textAreaTitle = new TextArea();
        this.textAreaBookName = new TextArea();
        this.textAreaAuthorName = new TextArea();
        this.textAreaForm = new TextArea("");
        this.textAreaMeter = new TextArea("");
        this.textAreaLink = new Text("");
        this.textAreaCurrentFromTotal = new TextArea("");
        this.textAreaCompleteContent = new TextArea("Update poem");

        textAreaCompleteContent.setHeight(500, Unit.PIXELS);
        textAreaCompleteContent.setWidth(500, Unit.PIXELS);


        btnPoemUpdate.addClickListener(buttonClickEvent -> {});
        btnNext.addClickListener(buttonClickEvent -> {

       fillForm();


        });
        VerticalLayout actions1 = new VerticalLayout(textAreaTitle,textAreaBookName,textAreaAuthorName,textAreaForm,textAreaMeter,textAreaLink,textAreaCompleteContent,btnPoemUpdate,btnNext);

        textAreaTags=new TextArea();
        textAreaTags.addThemeName("label-right");

        textAreaTags.setHeight(800, Unit.PIXELS);
        textAreaTags.setWidth(800, Unit.PIXELS);


        btnSaveTags=new Button("Save Tags");;


        btnSaveTags.addClickListener(buttonClickEvent -> {

           saveTags();


        });
         actions2 = new VerticalLayout(textAreaTags,btnSaveTags);
         actions3 = new VerticalLayout(textAreaCurrentFromTotal);

         horizontalLayout = new HorizontalLayout(actions1,actions2,actions3);
        add(horizontalLayout);

        //setContent(content);

        fillForm();




    }





    private void fillForm(){

        PoemEntity poemEntity=poemService.getPoem(currentId);
        textAreaTitle.setValue(poemEntity.getTitle());
        textAreaBookName.setValue(poemEntity.getBookName());
        textAreaAuthorName.setValue(poemEntity.getAuthorName());
        textAreaForm.setValue(   null!=poemEntity.getForm()?poemEntity.getForm():"" );
        textAreaMeter.setValue(null!=poemEntity.getMeter()?poemEntity.getMeter():"" );
        textAreaLink.setText(poemEntity.getLink());
        textAreaCompleteContent.setValue(null!=poemEntity.getCompleteContent()?poemEntity.getCompleteContent():"");

        currentId=poemEntity.getId();
        currentPoemId=poemEntity.getPbId();

        textAreaCurrentFromTotal.setValue(poemEntity.getId()+"/"+poemService.count());

setTags(poemEntity);

           }



           public void setTags(PoemEntity poemEntity){



     String prompt=   setPrompt(poemEntity);




      /*  StringBuilder builder=new StringBuilder();
        builder.append(prompt);

        builder.append(poemEntity.getBookName()+'\n'+"#"+'\n');
        builder.append(poemEntity.getTitle()+'\n'+"#"+'\n');
        builder.append(poemEntity.getAuthorName()+'\n'+"#"+'\n');

               String[] arr = poemEntity.getCompleteContent().split(" ");

               for ( String ss : arr) {

                   if(ss!=null ||!ss.trim().equals("")){
                       if(ss.trim().length()>2){
                   builder.append(ss+'\n'+"#"+'\n');}
                   }
               }


               System.out.println("Tags:"+builder.toString());*/

textAreaTags.setValue(prompt);

           }


public void saveTags(){

        Map<String,String> data=new HashMap<>();

        data.put("poemId",String.valueOf(currentPoemId));
        data.put("tags",textAreaTags.getValue());

        poemTagService.saveTags(data);

    Notification
            .show("Successfully saved tags");




}


public String  setPrompt(PoemEntity poemEntity){
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

             stringBuilder.append(tempKrd1+" " +ss +'\n'+"#"+'\n') ;
             stringBuilder.append(tempKrd5+" " +ss+ " " +tempKrd6+'\n'+"#"+'\n') ;
             stringBuilder.append(tempPrs5+" " +ss+'\n'+"#"+'\n') ;

                stringBuilder.append(tempPrs6+" " +ss+ " " +tempPrs3+'\n'+"#"+'\n') ;

            }
               // builder.append(ss+'\n'+"#"+'\n');}
        }
    }












   String str1=tempKrd1+" "+poemEntity.getTitle()+'\n'+"#"+'\n';
    stringBuilder.append(str1);

    String str2=tempKrd1+" "+poemEntity.getTitle()+" "+tempKrd2+" "+poemEntity.getAuthorName()+'\n'+"#"+'\n';

    stringBuilder.append(str2);
    String str3=tempKrd3+" "+poemEntity.getBookName()+" "+tempKrd4+'\n'+"#"+'\n';
    stringBuilder.append(str3);

    String str4=tempKrd5+" "+poemEntity.getBookName()+"،"+tempKrd6+'\n'+"#"+'\n';
    stringBuilder.append(str4);

    String str5=tempKrd7+" "+poemEntity.getBookName()+'\n'+"#"+'\n';
    stringBuilder.append(str5);

    String str6=tempKrd8+" "+poemEntity.getBookName()+'\n'+"#"+'\n';
    stringBuilder.append(str6);

    String str7=tempKrd9+" "+poemEntity.getAuthorName()+'\n'+"#"+'\n';
    stringBuilder.append(str7);

    String str8=tempPrs1+" "+poemEntity.getTitle()+" "+tempPrs3+'\n'+"#"+'\n';
    stringBuilder.append(str8);

    String str9=tempPrs1+" "+poemEntity.getTitle()+" "+tempPrs4+'\n'+"#"+'\n';
    stringBuilder.append(str9);


    String str10=tempPrs2+" "+poemEntity.getTitle()+" "+tempPrs3+'\n'+"#"+'\n';
    stringBuilder.append(str10);

    String str11=tempPrs2+" "+poemEntity.getTitle()+" "+tempPrs4+'\n'+"#"+'\n';
    stringBuilder.append(str11);


 String str12=tempPrs5+" "+poemEntity.getTitle()+'\n'+"#"+'\n';
    stringBuilder.append(str12);

    String str13=tempPrs6+" "+poemEntity.getTitle()+" "+tempPrs3+'\n'+"#"+'\n';
    stringBuilder.append(str13);


    String str14=tempPrs6+" "+poemEntity.getTitle()+" "+tempPrs4+'\n'+"#"+'\n';
    stringBuilder.append(str14);


    String str15=tempPrs7+" "+poemEntity.getAuthorName()+'\n'+"#"+'\n';
    stringBuilder.append(str15);



return stringBuilder.toString();
}


    }


