package krd.kurdestan.poemceawler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoemService {

@Autowired
PoemRepository poemRepository;




public PoemEntity getPoem(Long id) {

  return poemRepository.findFirstByIdGreaterThan(id);







}
  public long count(){

    return poemRepository.count();
  }

}
