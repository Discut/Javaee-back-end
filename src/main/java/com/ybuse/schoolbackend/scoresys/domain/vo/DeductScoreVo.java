package com.ybuse.schoolbackend.scoresys.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Discut
 */
@NoArgsConstructor
@Data
public class DeductScoreVo {
    private String type;

    private String classId;

    private String studentId;

    private Integer score;

    private String des;
}