package br.com.ticotech.gbooks.java.services;

import br.com.ticotech.gbooks.java.entities.Report;
import br.com.ticotech.gbooks.java.entities.ReportRequest;
import br.com.ticotech.gbooks.java.repository.ReportRepository;

public class ReportService {
    private final ReportRepository repository;

    public ReportService(){
        repository = new ReportRepository();
    }
    public Report getSalesReport(ReportRequest reportRequest){
        return repository.getReport(reportRequest);

    }
}
