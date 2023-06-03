package br.com.ticotech.gbooks.java.controller;

import br.com.ticotech.gbooks.java.entities.Report;
import br.com.ticotech.gbooks.java.entities.ReportRequest;
import br.com.ticotech.gbooks.java.services.ReportService;

public class ReportController {
    private final ReportService service;

    public ReportController(){
        service = new ReportService();
    }
    public Report getSalesReport(ReportRequest reportRequest){
        return service.getSalesReport(reportRequest);
    }
}
