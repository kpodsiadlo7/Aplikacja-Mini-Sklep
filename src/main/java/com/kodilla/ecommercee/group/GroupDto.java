package com.kodilla.ecommercee.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupDto {

    private Long groupId;
    private String name;
    private List<Long> productsId;
}
