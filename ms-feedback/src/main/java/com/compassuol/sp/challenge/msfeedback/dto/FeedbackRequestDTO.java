package com.compassuol.sp.challenge.msfeedback.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackRequestDTO {
    @Enumerated(EnumType.STRING)
    @NotNull(message = "scale field cannot be null")
    private ScaleEnum scale;
    @NotEmpty(message = "comment field cannot be null or empty")
    private String comment;
    @NotNull(message = "order_id field cannot be null")
    private Long order_id;
}
