package com.gatheringplatform.domain;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.gatheringplatform.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.sql.Timestamp;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @ApiModelProperty(hidden = true)
    private long no;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", groups = {ValidationGroups.signUp.class}, message = "id에 특수문자를 사용할 수 없습니다.")
    @Size(min=5, max=15, groups = {ValidationGroups.signUp.class, ValidationGroups.logIn.class}, message = "id는 최소 5글자, 최대 15글자 입니다.")
    @NotBlank(message = "사용 하실 id를 입력해주세요",groups = {ValidationGroups.signUp.class})
    private String id;

    //영문자,숫자,특수문자 최소 1개씩 사용해야함
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9$@$!%*#?&]{8,15}$", groups = {ValidationGroups.signUp.class,
    ValidationGroups.logIn.class},
            message = "패스워드는 영문자,숫자,특수문자를 최소 1개씩 포함해야합니다.")
    @Size(min=8, max=15, groups = {ValidationGroups.signUp.class, ValidationGroups.logIn.class}, message="비밀번호는 최소 8글자, 최대 15글자입니다.")
    @NotBlank(message = "비밀번호를 입력해주세요.",groups = {ValidationGroups.signUp.class})
    private String pw;

    @Pattern(regexp = "^[a-zA-Z가-힇0-9]{1,15}$", groups = {ValidationGroups.signUp.class}, message="닉네임으로 특수문자와 초성을 사용하실 수 없습니다.")
    @Size(min=1, max=15, groups = {ValidationGroups.signUp.class}, message = "닉네임은 최소 1글자, 최대 15글자입니다.")
    @NotBlank(message = "사용 하실 닉네임을 입력해주세요",groups = {ValidationGroups.signUp.class})
    private String nickname;

    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])",
            groups = {ValidationGroups.signUp.class}, message = "생년월일을 8자리로 입력해주세요.")
    @NotBlank(message = "생년월일을 입력해주세요.(8자리)",groups = {ValidationGroups.signUp.class})
    private String born;

    @NotBlank(message = "이메일을 입력해주세요.",groups = {ValidationGroups.signUp.class})
    @Email(message = "이메일 형식이 올바르지 않습니다.",groups = {ValidationGroups.signUp.class})
    private String email;

    @ApiModelProperty(hidden = true)
    private String salt;
    @ApiModelProperty(hidden = true)
    private Boolean is_deleted;
    @ApiModelProperty(hidden = true)
    private Timestamp created_at;
    @ApiModelProperty(hidden = true)
    private Timestamp updated_at;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
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
}
