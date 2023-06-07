package com.ybuse.schoolbackend.active_manager.domain.dto;

import com.ybuse.schoolbackend.active_manager.ActiveStatusEnum;
import lombok.Data;

@Data
public class ActiveDto {
    private String id;
    private String title;

    private String startTime;

    private String endTime;

    private String content;

    private String endContent;

    private ActiveStatusEnum status;


}
