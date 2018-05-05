package com.project.arduino.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "arduino", catalog = "")
public class UsersEntity {
    private int idUser;
    private String login;
    private String password;
    private List<LocksEntity> locksByIdUser;
    private UserStatusEntity status;


    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    public UserStatusEntity getStatus() {
        return status;
    }

    public void setStatus(UserStatusEntity status) {
        this.status = status;
    }

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "login")
    @Email(message = "PLease provide a valid email")
    @NotEmpty(message = "PLease provide an email")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    @Size(min = 3, message = "Your password must have at list 3 characters")
    @NotEmpty(message = "PLease provide an password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idUser != that.idUser) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUser")
    public List<LocksEntity> getLocksByIdUser() {
        return locksByIdUser;
    }

    public void setLocksByIdUser(List<LocksEntity> locksByIdUser) {
        this.locksByIdUser = locksByIdUser;
    }
}
