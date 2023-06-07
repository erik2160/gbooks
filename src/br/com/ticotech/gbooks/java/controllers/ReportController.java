package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.report.ReportTableModel;

public class ReportController {
    private StockRepository stockRepository;
    private SaleRepository saleRepository;
    private ReportTableModel reportTableModel;

    public ReportTableModel getReportTableModel() {
        return reportTableModel;
    }

    public ReportController(StockRepository stockRepository, SaleRepository saleRepository){
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
        this.reportTableModel = new ReportTableModel(saleRepository, stockRepository);
    }

}
