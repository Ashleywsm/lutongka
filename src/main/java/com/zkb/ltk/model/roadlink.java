package com.zkb.ltk.model;
//21个
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashley_wsm on 2017/3/1.
 */
@Entity
@Table(name = "roadlink")
public class roadlink {
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
    @Column(name = "next_id")
    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }
    @Column(name = "pre_id")
    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }
    @Column(name = "length")
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
    @Column(name = "is_ramp")
    public String getIs_ramp() {
        return is_ramp;
    }

    public void setIs_ramp(String is_ramp) {
        this.is_ramp = is_ramp;
    }
    @Column(name = "attribute")
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    @Column(name = "highway_id")
    public String getHighway_id() {
        return highway_id;
    }

    public void setHighway_id(String highway_id) {
        this.highway_id = highway_id;
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
    @Column(name = "lane")
    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    @Column(name = "speed_downbound")
    public Integer getSpeed_downbound() {
        return speed_downbound;
    }

    public void setSpeed_downbound(Integer speed_downbound) {
        this.speed_downbound = speed_downbound;
    }
    @Column(name = "speed_upbound")
    public Integer getSpeed_upbound() {
        return speed_upbound;
    }

    public void setSpeed_upbound(Integer speed_upbound) {
        this.speed_upbound = speed_upbound;
    }
    @Column(name = "line")
    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
    @Column(name = "tachometer_id")
    public String getTachometer_id() {
        return tachometer_id;
    }

    public void setTachometer_id(String tachometer_id) {
        this.tachometer_id = tachometer_id;
    }
    @Column(name = "tachometer_stake")
    public Double getTachometer_stake() {
        return tachometer_stake;
    }

    public void setTachometer_stake(Double tachometer_stake) {
        this.tachometer_stake = tachometer_stake;
    }
    @Column(name = "station_id")
    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }
    @Column(name = "station_stake")
    public Double getStation_stake() {
        return station_stake;
    }

    public void setStation_stake(Double station_stake) {
        this.station_stake = station_stake;
    }
    @Column(name = "stake_start")
    public String getStake_start() {
        return stake_start;
    }

    public void setStake_start(String stake_start) {
        this.stake_start = stake_start;
    }
    @Column(name = "stake_end")
    public String getStake_end() {
        return stake_end;
    }

    public void setStake_end(String stake_end) {
        this.stake_end = stake_end;
    }
    @Column(name = "direction")
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private  String id;
    private  String next_id;
    private  String pre_id;
    private  Double length;
    private  String is_ramp;
    private  String attribute;
    private  String highway_id;
    private  Double longitude;
    private  Double latitude;
    private  Integer lane;
    private  Integer speed_downbound;
    private  Integer speed_upbound;
    private  Integer line;
    private  String tachometer_id;
    private  Double tachometer_stake;
    private  String station_id;
    private  Double station_stake;
    private  String stake_start;
    private  String stake_end;
    private  Integer direction;
    private  String province;


}
