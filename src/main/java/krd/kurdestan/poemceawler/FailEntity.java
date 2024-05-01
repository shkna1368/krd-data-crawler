package krd.kurdestan.poemceawler;

import jakarta.persistence.*;

@Entity

public class FailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private    Long id;
    private    String name;
    private String url;

    @Column(columnDefinition="text")
    private String cause;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
