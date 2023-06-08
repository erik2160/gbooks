package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.BookReport;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.report.ReportTableModel;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReportController {
    private StockRepository stockRepository;
    private SaleRepository saleRepository;
    private final ReportTableModel reportTableModel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ReportTableModel getReportTableModel() {
        return reportTableModel;
    }

    public ReportController(StockRepository stockRepository, SaleRepository saleRepository){
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
        this.reportTableModel = new ReportTableModel(saleRepository, stockRepository);
    }

    public void doSearch(String firstDate, String secondDate){
        List<BookReport> newReportList = new ArrayList<>();

        if (!Objects.equals(firstDate, "   DD/MM/YYYY")&& Objects.equals(secondDate, "   DD/MM/YYYY")){
            for (BookReport report: reportTableModel.getReportList()){
                try {
                    Date initialDate = dateFormat.parse(firstDate);
                    Date reportDate = dateFormat.parse(report.getDate());
                    if (reportDate.after(initialDate)){
                        newReportList.add(report);
                    }
                }catch (ParseException e) {
                    new Popups("Date format invalid.", 1);
                }
            }
            reportTableModel.setReportList(newReportList);
        } else if (Objects.equals(firstDate, "   DD/MM/YYYY")&& !Objects.equals(secondDate, "   DD/MM/YYYY")) {
            for (BookReport report : reportTableModel.getReportList()) {
                try {
                    Date finalDate = dateFormat.parse(secondDate);
                    Date reportDate = dateFormat.parse(report.getDate());
                    if (reportDate.before(finalDate)) {
                        newReportList.add(report);
                    }
                } catch (ParseException e) {
                    new Popups("Date format invalid.", 1);
                }
            }
            reportTableModel.setReportList(newReportList);
        } else if (!Objects.equals(secondDate, "   DD/MM/YYYY")&&!Objects.equals(firstDate, "   DD/MM/YYYY")) {
            for (BookReport report : reportTableModel.getReportList()) {
                try {
                    Date initialDate = dateFormat.parse(firstDate);
                    Date finalDate = dateFormat.parse(secondDate);
                    Date reportDate = dateFormat.parse(report.getDate());
                    if (reportDate.after(initialDate) && reportDate.before(finalDate)) {
                        newReportList.add(report);
                    }
                } catch (ParseException e) {
                    new Popups("Date format invalid.", 1);
                }
            }
            reportTableModel.setReportList(newReportList);
        }
    }

}
