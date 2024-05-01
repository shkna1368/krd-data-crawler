package krd.kurdestan.poemceawler.ui;

import krd.kurdestan.poemceawler.PoemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoemTageRepository extends JpaRepository<PoemTagEntity, Long> {

    List<PoemTagEntity> findAllByPoemId(long poemId);
}
