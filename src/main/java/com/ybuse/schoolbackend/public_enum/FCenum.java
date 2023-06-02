package com.ybuse.schoolbackend.public_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FCenum {
    D("1", "徳"),
    Z("2", "值"),
    T("3", "体"),
    M("4", "美"),
    L("5", "劳"),
    ;

    private final String type;
    private final String name;


}
