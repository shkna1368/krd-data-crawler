package krd.kurdestan.poemceawler.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PoemTagService {


    @Autowired
    PoemTageRepository poemTageRepository;



          public void saveTags(Map<String,String> data){

        Long poemId=      Long.parseLong(data.get("poemId"));
        String rawContent=      data.get("tags");
List<PoemTagEntity> poemTagEntities=new ArrayList<>();

     String[] tags =  rawContent.split("#");

     for (String tag : tags) {

              PoemTagEntity poemTagEntity=new PoemTagEntity();
              poemTagEntity.setTag(tag.trim());
              poemTagEntity.setPoemId(poemId);
              poemTagEntities.add(poemTagEntity);

          }


              poemTageRepository.saveAll(poemTagEntities);

}
public   List<PoemTagEntity> findAllByPoemId(long poemId){

           return    poemTageRepository.findAllByPoemId(poemId);
  }


  public long count(){

              return poemTageRepository.count();
  }

}
