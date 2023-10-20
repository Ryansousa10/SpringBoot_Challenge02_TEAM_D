package com.compassuol.sp.challenge.msfeedback.model;

import com.compassuol.sp.challenge.msfeedback.dto.ScaleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback_tb")
@NoArgsConstructor
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ScaleEnum scale;
    private String comment;
    @Column(nullable = false)
    private Long order_id;

    public FeedbackModel(ScaleEnum scale, String comment, Long order_id) {
        this.scale = scale;
        this.comment = comment;
        this.order_id = order_id;
    }
}
