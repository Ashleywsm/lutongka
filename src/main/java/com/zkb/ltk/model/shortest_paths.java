package com.zkb.ltk.model;
//5个
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.soap.Text;

/**
 * Created by ashley_wsm on 2017/3/1.
 */
@Entity
@Table(name = "shortest_paths")
public class shortest_paths {

    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "id",  nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "origin",nullable = false)
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column(name = "destination",nullable = false)
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "length")
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    private Integer id;
    private String origin;
    private String destination;
    private Double length;
    private String path;

}
