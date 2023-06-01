package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.ListStock;
import br.com.ticotech.gbooks.java.entities.Report;
import br.com.ticotech.gbooks.java.entities.ReportItem;
import br.com.ticotech.gbooks.java.entities.ReportRequest;

import java.util.Date;

public class ReportRepository {
    private final StockRepository stockRepository;
    public Report getReport(ReportRequest reportRequest){
        Report report = new Report();
        for(ListStock stock: stockRepository.getItems()){
            //TODO Fazer filtro de data de acordo com ReportRequest startDate e endDate
            var item = new ReportItem();
            item.setDate(new Date());//TODO
            item.setBarcode(stock.getCode());
            item.setTitle(stock.getTitle());
            item.setUnits(stock.getQuantity());
            item.setFinalPrice(stock.getPrice());
            item.setTotal(stock.getPrice()*stock.getQuantity());//TODO
            item.setInvoiceCost(20);//TODO
            item.setProfit(stock.getPrice()*stock.getQuantity()/10);//TODO
            report.addItem(item);
        }
        return report;
    }
    public ReportRepository(){
        this.stockRepository = new StockRepository();
    }
}
