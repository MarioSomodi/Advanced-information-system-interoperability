package hr.algreba.pi.hardwareapp.dto;

import hr.algreba.pi.hardwareapp.domain.Review;

public class ReviewDTO {

    private String title;
    private String text;
    private Long rating;

    public ReviewDTO(Review review) {
        this.title = review.getTitle();
        this.text = review.getText();
        this.rating = review.getRating();
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
