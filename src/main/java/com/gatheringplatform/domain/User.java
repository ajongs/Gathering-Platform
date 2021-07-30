package com.gatheringplatform.domain;



import com.gatheringplatform.annotation.ValidationGroups;

import javax.validation.constraints.*;
import java.sql.Timestamp;

public class User {
    private long no;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$")
    @Size(min=5, max=15, groups = {ValidationGroups.signIn.class})
    @NotBlank(message = "사용 하실 id를 입력해주세요",groups = {ValidationGroups.signIn.class})
    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9$@$!%*#?&]{8,15}$")
    @Size(min=8, max=15, groups = {ValidationGroups.signIn.class})
    @NotBlank(message = "비밀번호를 입력해주세요.",groups = {ValidationGroups.signIn.class})
    private String pw;

    @Pattern(regexp = "^[a-zA-Z가-힇0-9]{5,15}$")
    @Size(min=5, max=15, groups = {ValidationGroups.signIn.class})
    @NotBlank(message = "사용 하실 닉네임을 입력해주세요",groups = {ValidationGroups.signIn.class})
    private String nickname;

    @Max(value=90, message = "90세 이하만 가입가능합니다.")
    @Min(value = 14, message = "14세 이상만 가입가능합니다.")
    @NotBlank(message = "나이를 입력해주세요.",groups = {ValidationGroups.signIn.class})
    private int age;

    @NotBlank(message = "이메일을 입력해주세요.",groups = {ValidationGroups.signIn.class})
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String salt;
    private Boolean is_deleted;
    private Timestamp created_at;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
