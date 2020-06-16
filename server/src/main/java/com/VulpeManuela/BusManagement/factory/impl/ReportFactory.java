package com.VulpeManuela.BusManagement.factory.impl;

import com.VulpeManuela.BusManagement.factory.Report;

import java.util.List;

public class ReportFactory {
    public Report getReport(String reportType) {
        if (reportType == null) {
            return null;
        }

        if(reportType.equalsIgnoreCase("CSV")){
            return new ReportCsv();

        } else if(reportType.equalsIgnoreCase("TXT")){
            return new ReportTxt();

        }

        return null;
    }
}
