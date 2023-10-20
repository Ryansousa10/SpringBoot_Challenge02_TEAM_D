package com.compassuol.sp.challenge.msfeedback.repository;

import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
}
