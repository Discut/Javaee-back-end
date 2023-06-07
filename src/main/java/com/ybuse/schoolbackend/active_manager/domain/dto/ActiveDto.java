package com.ybuse.schoolbackend.active_manager.domain.dto;

import com.ybuse.schoolbackend.active_manager.ActiveStatusEnum;
import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import lombok.Data;
import lombok.val;

import java.time.LocalDate;

@Data
public class ActiveDto {
    private String id;
    private String title;

    private String startTime;

    private String endTime;

    private String content;

    private String endContent;

    private ActiveStatusEnum status;


    public ActiveDto(ActiveManagerPo activeManagerPo) {
        this.id = String.valueOf(activeManagerPo.getId());
        val startTimeString = activeManagerPo.getAmTimeInterval().split("\\|")[0];
        val endTimeString = activeManagerPo.getAmTimeInterval().split("\\|")[1];
        val startTime = LocalDate.parse(startTimeString);
        val endTime = LocalDate.parse(endTimeString);
        this.setId(String.valueOf(activeManagerPo.getId()));
        this.setTitle(activeManagerPo.getAmName());
        this.setContent(activeManagerPo.getAmContent());
        this.setEndContent(activeManagerPo.getAmEndContent());
        this.setStartTime(activeManagerPo.getAmTimeInterval().split("\\|")[0]);
        this.setEndTime(activeManagerPo.getAmTimeInterval().split("\\|")[1]);
        if (endTime.isBefore(startTime)) {
            this.setStatus(ActiveStatusEnum.FINISHED);
        } else if (startTime.isBefore(LocalDate.now()) && endTime.isAfter(LocalDate.now())) {
            this.setStatus(ActiveStatusEnum.ACTING);
        } else if (endTime.isBefore(LocalDate.now())) {
            this.setStatus(ActiveStatusEnum.FINISHED);
        } else {
            this.setStatus(ActiveStatusEnum.RUNNABLE);
        }
    }
}
