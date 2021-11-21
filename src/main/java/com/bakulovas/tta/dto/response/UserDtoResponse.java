package com.bakulovas.tta.dto.response;

import java.util.List;

public class UserDtoResponse {

    private long id;
    private String loginName;
    private String email;
    private boolean tempPassword;
    private String firstName;
    private String lastName;
    private boolean active;
    private String office;
    private List<String> roles;

    public UserDtoResponse() {
    }

    public UserDtoResponse(long id, String loginName, String email, boolean tempPassword, String firstName,
                           String lastName, boolean active, String office, List<String> roles) {
        this.id = id;
        this.loginName = loginName;
        this.email = email;
        this.tempPassword = tempPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.office = office;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(boolean tempPassword) {
        this.tempPassword = tempPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
