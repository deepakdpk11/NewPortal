package com.MCPortal.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.MCPortal.Model.BatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="batchinfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BatchInfo{
    @Id
    private int id;
    private String batch_name;
    private String batch_time;
    private String batch_days;
    private String batch_fee_Amt;
    private String batch_status;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatch_name() {
		return batch_name;
	}
	public void setBatch_name(String batch_name) {
		this.batch_name = batch_name;
	}
	public String getBatch_time() {
		return batch_time;
	}
	public void setBatch_time(String batch_time) {
		this.batch_time = batch_time;
	}
	public String getBatch_days() {
		return batch_days;
	}
	public void setBatch_days(String batch_days) {
		this.batch_days = batch_days;
	}
	public String getBatch_fee_Amt() {
		return batch_fee_Amt;
	}
	public void setBatch_fee_Amt(String batch_fee_Amt) {
		this.batch_fee_Amt = batch_fee_Amt;
	}
	public String getBatch_status() {
		return batch_status;
	}
	public void setBatch_status(String batch_status) {
		this.batch_status = batch_status;
	}

}