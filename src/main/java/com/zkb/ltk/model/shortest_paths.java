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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "length")
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    private String id;
    private String length;
    private String path;
    private String province;

}
