package krd.kurdestan.poemceawler.ui;

import jakarta.persistence.*;
import krd.kurdestan.poemceawler.Poem;

@Entity
@Table(name = "poem_tag_v1")
public class PoemTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poem_tags_id")
    private    Long id;
    private Long poemId;

   private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoemId() {
        return poemId;
    }

    public void setPoemId(Long poemId) {
        this.poemId = poemId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
