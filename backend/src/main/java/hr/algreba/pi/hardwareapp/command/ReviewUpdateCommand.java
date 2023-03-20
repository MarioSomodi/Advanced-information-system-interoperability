package hr.algreba.pi.hardwareapp.command;

import hr.algreba.pi.hardwareapp.domain.Type;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ReviewUpdateCommand {
    @NotNull(message = "Id must be provided")
    private Long id;
    @NotBlank(message = "Title must not be empty")
    @Size(max = 200, message = "Title can't have more than 200 characters")
    private String title;

    @NotBlank(message = "Text must not be empty")
    @Size(max = 500, message = "Text can't have more than 500 characters")
    private String text;

    @PositiveOrZero(message = "Rating must be positive or zero")
    @Max(value = 5, message = "Rating can't be above 5")
    private Long rating;

    @NotNull(message = "You need to provide the hardware id for this review")
    private BigInteger hardwareID;

    public ReviewUpdateCommand(Long id, String title, String text, Long rating, BigInteger hardwareID) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.hardwareID = hardwareID;
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
