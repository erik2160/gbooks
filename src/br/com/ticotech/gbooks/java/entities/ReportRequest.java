package br.com.ticotech.gbooks.java.entities;

import java.util.Date;

public class ReportRequest {

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ReportRequest(){

    }

    
}
