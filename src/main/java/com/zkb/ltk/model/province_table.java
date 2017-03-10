package com.zkb.ltk.model;
//5个
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashley_wsm on 2017/3/1.
 */
@Entity
@Table(name = "province_table")
public class province_table {

    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "provinceID",  nullable = false)
    public String getProvinceID() {
        return provinceID;
    }
    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    @Column(name = "province",  nullable = false)
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "province_simplify",  nullable = false)
    public String getProvince_simplify() {
        return province_simplify;
    }
    public void setProvince_simplify(String province_simplify) {
        this.province_simplify = province_simplify;
    }

    @Column(name = "longitude",  nullable = false)
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude",  nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    private String provinceID;
    private String province;
    private String province_simplify;
    private Double longitude;
    private Double latitude;


}
