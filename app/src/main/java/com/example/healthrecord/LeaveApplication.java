package com.example.healthrecord;

public class LeaveApplication {

    public static final String collectionName = "LeaveApplication";

    private String sID;
    private String fID;
    private String dID;
    private String startDate;
    private String endDate;
    private boolean isApprovedByDoc;
    private boolean isApprovedByFac;

    public LeaveApplication() {
    }

    public String getsID() {
        return sID;
    }

    public String getfID() {
        return fID;
    }

    public String getdID() {
        return dID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isApprovedByDoc() {
        return isApprovedByDoc;
    }

    public void setApprovedByDoc(boolean approvedByDoc) {
        isApprovedByDoc = approvedByDoc;
    }

    public boolean isApprovedByFac() {
        return isApprovedByFac;
    }

    public void setApprovedByFac(boolean approvedByFac) {
        isApprovedByFac = approvedByFac;
    }
}
