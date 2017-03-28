package com.zkb.ltk.model;
//2个

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zkb on 2017/2/26.
 */

@Entity
@Table(name = "user")
public class user {
    private String user_id;
    private String password;

    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id",  nullable = false)
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id =user_id;
    }

    @Column(name="password",nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
