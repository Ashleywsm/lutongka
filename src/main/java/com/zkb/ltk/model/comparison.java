package com.zkb.ltk.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashley_wsm on 2017/3/13.
 */
@Entity
@Table(name = "comparison")
public class comparison {
    private String id;
    private String provinceID;
    private String netID;
    private String stationID;
    private String station;
    private String stationType;
    private String poi_id;
    private String station_name;
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
    @Column(name = "provinceID")
    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }
    @Column(name = "netID")
    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }
    @Column(name = "stationID")
    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }
    @Column(name = "station")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
    @Column(name = "stationType")
    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }
    @Column(name = "poi_id")
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
}
