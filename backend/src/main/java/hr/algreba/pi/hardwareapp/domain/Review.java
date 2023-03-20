package hr.algreba.pi.hardwareapp.domain;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.ReviewCommand;
import hr.algreba.pi.hardwareapp.command.ReviewUpdateCommand;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private Long rating;

    public Review(ReviewCommand reviewCommand) {
        this.title = reviewCommand.getTitle();
        this.text = reviewCommand.getText();
        this.rating = reviewCommand.getRating();
    }

    public Review(ReviewUpdateCommand reviewCommand) {
        this.id = reviewCommand.getId();
        this.title = reviewCommand.getTitle();
        this.text = reviewCommand.getText();
        this.rating = reviewCommand.getRating();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Hardware hardware;

    public Review() {

    }

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
