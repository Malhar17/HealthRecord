package com.example.healthrecord;

public class LabTests {

    public static final String collectionName = "LabTests";

    private String dID;
    private String pID;
    private String testName;
    private boolean result;

    public LabTests() {
    }

    public String getdID() {
        return dID;
    }

    public String getpID() {
        return pID;
    }

    public String getTestName() {
        return testName;
    }

    public boolean isResult() {
        return result;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
