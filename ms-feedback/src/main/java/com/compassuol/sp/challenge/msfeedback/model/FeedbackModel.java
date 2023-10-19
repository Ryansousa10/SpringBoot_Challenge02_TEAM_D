package com.compassuol.sp.challenge.msfeedback.model;

import com.compassuol.sp.challenge.msfeedback.dto.ScaleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback_tb")
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private ScaleEnum scale;
    private String comment;
    @Column(nullable = false)
    private int order_id;
}
