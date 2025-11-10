package models;

import java.time.LocalDate;

public class Report {
    private String reportID, managerID, staffID;
    private int orderQuantity;
    private int revenue, receivables;
    private LocalDate startDate, endDate;
    
    public Report() {};
    
    public Report(String reportID, String managerID, String staffID, int orderQuantity,
                int revenue, int receivables, LocalDate startDate, LocalDate endDate) {
        this.reportID = reportID;
        this.managerID = managerID;
        this.staffID = staffID;
        this.orderQuantity = orderQuantity;
        this.revenue = revenue;
        this.receivables = receivables;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getReportID() {
        return reportID;
    }
    
    public void setReportID(String reportID) {
        this.reportID = reportID;
    }
    
    public String getManagerID() {
        return managerID;
    }
    
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
    
    public String getStaffID() {
        return staffID;
    }
    
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    
    public int getOrderQuantity() {
        return orderQuantity;
    }
    
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    
    public int getRevenue() {
        return revenue;
    }
    
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    
    public int getReceivables() {
        return receivables;
    }
    
    public void setReceivables(int receivables) {
        this.receivables = receivables;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}