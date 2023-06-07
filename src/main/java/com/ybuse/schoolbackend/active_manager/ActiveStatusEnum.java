package com.ybuse.schoolbackend.active_manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author Discut
 */
@Getter
@AllArgsConstructor
public enum ActiveStatusEnum {
    ACTING("正在进行"),
    FINISHED("结束"),
    RUNNABLE("等待开始")
    ;
    private final String des;


    @Override
    public String toString() {
        return this.name();
    }
}
