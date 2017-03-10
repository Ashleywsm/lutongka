package com.zkb.ltk.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashley_wsm on 2017/3/1.
 */
@Entity
@Table(name = "stations")
public class stations {
    private String poi_id;
    private String station_name;
    private Double longitude;
    private Double latitude;
    private String direction;
    private String roadname;
    private String province;
    private String roadlink;
    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "poi_id",  nullable = false)
    public String getPoi_id() {
        return poi_id;
    }
    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

    @Column(name = "station_name")
    public String getStation_name() {
        return station_name;
    }
    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "direction")
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Column(name = "roadname")
    public String getRoadname() {
        return roadname;
    }
    public void setRoadname(String roadname) {
        this.roadname = roadname;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "roadlink")
    public String getRoadlink() {
        return roadlink;
    }
    public void setRoadlink(String roadlink) {
        this.roadlink = roadlink;
    }

}
