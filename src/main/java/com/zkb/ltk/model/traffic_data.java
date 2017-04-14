package com.zkb.ltk.model;
//18个
import java.sql.Timestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashley_wsm on 2017/3/1.
 */
@Entity
@Table(name = "traffic_data")
public class traffic_data {
    private String wasteID;
    private String exStation;
    private String paycard;
    private Double lastMoney;
    private Timestamp inTime;
    private String inNetID;
    private String inStationID;
    private String origin;
    private Timestamp outTime;
    private String outNetID;
    private String outStationID;
    private String destination;
    private String weight;
    private String overlimit;
    private String plateColor;
    private String vehiclePlate;
    private String vehicleModel;
    private String province;

    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "wasteID",  nullable = false)
    public String getWasteID() {
        return wasteID;
    }
    public void setWasteID(String wasteID) {
        this.wasteID = wasteID;
    }

    @Column(name = "exStation",nullable = false)
    public String getExStation() {
        return exStation;
    }
    public void setExStation(String exStation) {
        this.exStation = exStation;
    }

    @Column(name = "paycard")
    public String getPaycard() {
        return paycard;
    }
    public void setPaycard(String paycard) {
        this.paycard = paycard;
    }

    @Column(name = "lastMoney")
    public Double getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(Double lastMoney) {
        this.lastMoney = lastMoney;
    }

    @Column(name = "inTime")
    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    @Column(name = "inNetID")
    public String getInNetID() {
        return inNetID;
    }
    public void setInNetID(String inNetID) {
        this.inNetID = inNetID;
    }

    @Column(name = "inStationID")
    public String getInStationID() {
        return inStationID;
    }
    public void setInStationID(String inStationID) {
        this.inStationID = inStationID;
    }

    @Column(name = "origin")
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column(name = "outTime")
    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    @Column(name = "outNetID")
    public String getOutNetID() {
        return outNetID;
    }
    public void setOutNetID(String outNetID) {
        this.outNetID = outNetID;
    }

    @Column(name = "outStationID")
    public String getOutStationID() {
        return outStationID;
    }
    public void setOutStationID(String outStationID) {
        this.outStationID = outStationID;
    }

    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "overlimit")
    public String getOverlimit() {
        return overlimit;
    }

    public void setOverlimit(String overlimit) {
        this.overlimit = overlimit;
    }

    @Column(name = "plateColor")
    public String getPlateColor() {
        return plateColor;
    }
    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    @Column(name = "vehiclePlate")
    public String getVehiclePlate() {
        return vehiclePlate;
    }
    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    @Column(name = "vehicleModel")
    public String getVehicleModel() {
        return vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Column(name = "province",nullable = false)
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
}
