package com.gatheringplatform.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.gatheringplatform.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.sql.Timestamp;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Board {
    //게시판 데이터
    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(hidden = true)
    private String author;

    @NotNull(groups = {ValidationGroups.uploadBoard.class})
    private String category;

    @Size(min=1, max=60, groups = {ValidationGroups.uploadBoard.class}, message = "제목은 최소 1글자, 최대 60글자까지 입력가능합니다.")
    @NotNull(groups = {ValidationGroups.uploadBoard.class})
    private String title;

    @Size(max=130, groups = {ValidationGroups.uploadBoard.class}, message = "섬네일 파일이름 또는 주소가 130글자를 초과할 수 없습니다.")
    private String thumbnail;

    //@Size(max=65535, groups = {ValidationGroups.uploadBoard.class}, message="상세설명이 65535 byte 를 초과할 수 없습니다.")
    @NotNull(groups = {ValidationGroups.uploadBoard.class})
    private String content;

    @ApiModelProperty(hidden = true)
    private Timestamp created_at;

    @ApiModelProperty(hidden = true)
    private Timestamp updated_at;

    @ApiModelProperty(hidden = true)
    private Boolean is_deleted;

    //상세 일정
    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])",
            groups = {ValidationGroups.uploadBoard.class}, message = "모임 시작 날짜를 8자리 숫자로 입력해주세요.")
    @NotNull(message = "모임 시작 날짜를 선택해주세요.", groups = {ValidationGroups.uploadBoard.class})
    private String start_at ;

    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])",
            groups = {ValidationGroups.uploadBoard.class}, message = "모임 종료 날짜를 8자리 숫자로 입력해주세요.")
    @NotNull(message = "모임 종료 날짜를 선택해주세요.", groups = {ValidationGroups.uploadBoard.class})
    private String finish_at ;

    @Size(max=10, groups = {ValidationGroups.uploadBoard.class}, message="모임빈도가 10글자를 초과할 수 없습니다.")
    @NotNull(message = "모임빈도를 입력해주세요.", groups = {ValidationGroups.uploadBoard.class})
    private String frequency;

    @Min(value = 1, groups = {ValidationGroups.uploadBoard.class}, message = "인원수를 입력해주세요.")
    private int numOfPeople;

    @Size(max=20, groups = {ValidationGroups.uploadBoard.class}, message="모임장소가 20글자를 초과할 수 없습니다.")
    @NotNull(groups = {ValidationGroups.uploadBoard.class})
    private String place;

    private int fee;

    private int min_age;

    private int max_age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getFinish_at() {
        return finish_at;
    }

    public void setFinish_at(String finish_at) {
        this.finish_at = finish_at;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }
}
