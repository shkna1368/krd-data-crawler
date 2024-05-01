package krd.kurdestan.poemceawler;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PoemRepository extends JpaRepository<PoemEntity, Long> {

    PoemEntity findFirstByIdGreaterThan(Long id);



}
