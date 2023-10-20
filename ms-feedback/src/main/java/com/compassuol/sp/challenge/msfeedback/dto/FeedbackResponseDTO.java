package com.compassuol.sp.challenge.msfeedback.dto;

import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {
    private Long id;
    private ScaleEnum scale;
    private String comment;
    private Long order_id;

    public FeedbackResponseDTO(FeedbackModel model) {
        this.id = model.getId();
        this.scale = model.getScale();
        this.comment = model.getComment();
        this.order_id = model.getOrder_id();
    }
}
