package krd.kurdestan.poemceawler;

import jakarta.persistence.*;

@Entity
@Table(name = "pm_v1")
public class PoemEntity {
    @Id
    @Column(name = "poems_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private    Long id;
    private Long pbId;
    private    String content;
    private String title;
    private String bookName;
    private String authorName;
    private String form;
    private String meter;
    private String link;
    private String instruct;





    @Column(columnDefinition="text")
    private String completeContent;

    @Column(columnDefinition="text")
    private String tag;

    public PoemEntity() {
    }


    public PoemEntity(PoemEntity poemEntity) {

        this.pbId = poemEntity.pbId;
        this.content = poemEntity.content;
        this.title =poemEntity. title;
        this.bookName = poemEntity.bookName;
        this.authorName = poemEntity.authorName;
        this.form = poemEntity.form;
        this.meter = poemEntity.meter;
        this.link = poemEntity.link;
        this.completeContent = poemEntity.completeContent;
        this.tag = poemEntity.tag;
        this.instruct=poemEntity.instruct;
    }

    public PoemEntity(Poem poem) {

        this.pbId = poem.pbId();
        this.content = poem.content();
        this.title = poem.title();
        this.bookName = poem.bookName();
        this.authorName = poem.authorName();
        this.form = poem.form();
        this.meter =poem. meter();
        this.link = poem.link();
        this.completeContent=poem.cmpleteContent()   ;
        this.tag=poem.tag();
        this.instruct=poem.instruct();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCompleteContent() {
        return completeContent;
    }

    public void setCompleteContent(String completeContent) {
        this.completeContent = completeContent;
    }

    public Long getPbId() {
        return pbId;
    }

    public void setPbId(Long pbId) {
        this.pbId = pbId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }


}
