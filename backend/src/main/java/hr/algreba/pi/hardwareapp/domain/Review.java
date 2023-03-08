package hr.algreba.pi.hardwareapp.domain;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hardware hardware;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getRating() {
        return rating;
    }
}
