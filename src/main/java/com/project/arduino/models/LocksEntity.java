package com.project.arduino.models;

import javax.persistence.*;

@Entity
@Table(name = "locks", schema = "arduino", catalog = "")
public class LocksEntity {
    private int idLock;
    private String title;
    private String password;
    private UsersEntity usersByUser;

    @Id
    @Column(name = "id_lock")
    public int getIdLock() {
        return idLock;
    }

    public void setIdLock(int idLock) {
        this.idLock = idLock;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "password")
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

        LocksEntity that = (LocksEntity) o;

        if (idLock != that.idLock) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLock;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    public UsersEntity getUsersByUser() {
        return usersByUser;
    }

    public void setUsersByUser(UsersEntity usersByUser) {
        this.usersByUser = usersByUser;
    }
}
