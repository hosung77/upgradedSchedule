package com.example.upgradedschedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedCommentDto {

    private String updatedCommentContent;

    public UpdatedCommentDto(String comment){
        this.updatedCommentContent = comment;
    }
}
