package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.entities.BookReport;
import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Sale;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportTableModel extends DefaultTableModel {

    private List<Sale> saleList;
    StockRepository stockRepository;

    private final String[] columns = {"DATE","CODE","TITLE","UNITS","SELL PRICE","INVOICE PRICE","TOTAL PRICE","PROFIT"};

    private List<BookReport> reportList;
    private SaleRepository saleRepository;

    public ReportTableModel(SaleRepository saleRepository, StockRepository stockRepository){
        this.saleRepository = saleRepository;
        this.saleList = saleRepository.getSaleList();
        this.stockRepository = stockRepository;
        List<BookReport> bookReportList = new ArrayList<>();
        for(Sale sale: saleList) {
            for (CartBook book : sale.getBookList()) {
                double invoicePrice = stockRepository.getBook(book.getCode()).getInvoicePrice();
                BookReport bookReport = new BookReport(
                        sale.getDate(),
                        book.getCode(),
                        book.getTitle(),
                        book.getUnits(),
                        book.getUnitPrice(),
                        invoicePrice,
                        book.getTotalPrice(),
                        book.getTotalPrice()-(book.getUnits()*invoicePrice)
                );
                bookReportList.add(bookReport);
            }
        }
        reportList = bookReportList;
    }

    public void updateList(){
        List<BookReport> bookReportList = new ArrayList<>();
        for(Sale sale: saleRepository.getSaleList()) {
            for (CartBook book : sale.getBookList()) {
                double invoicePrice = stockRepository.getBook(book.getCode()).getInvoicePrice();
                BookReport bookReport = new BookReport(
                        sale.getDate(),
                        book.getCode(),
                        book.getTitle(),
                        book.getUnits(),
                        book.getUnitPrice(),
                        invoicePrice,
                        book.getTotalPrice(),
                        book.getTotalPrice()-(book.getUnits()*invoicePrice)
                );
                bookReportList.add(bookReport);
            }
        }
        reportList = bookReportList;
    }

    public String[] getColumns() {
        return columns;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        if(this.reportList == null){
            return 0;
        }
        return this.reportList.size();
    }

    @Override
    public int getColumnCount() {
        return getColumns().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return getColumns()[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> this.reportList.get(rowIndex).getDate();
            case 1 -> this.reportList.get(rowIndex).getBarcode();
            case 2 -> this.reportList.get(rowIndex).getTitle();
            case 3 -> this.reportList.get(rowIndex).getUnits();
            case 4 -> this.reportList.get(rowIndex).getSellPrice();
            case 5 -> this.reportList.get(rowIndex).getInvoicePrice();
            case 6 -> this.reportList.get(rowIndex).getTotalPrice();
            case 7 -> this.reportList.get(rowIndex).getProfit();
            default -> 0;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.reportList.get(rowIndex).setDate((String) aValue);
            case 1 -> this.reportList.get(rowIndex).setBarcode((String) aValue);
            case 2 -> this.reportList.get(rowIndex).setTitle((String) aValue);
            case 3 -> this.reportList.get(rowIndex).setUnits((Integer) aValue);
            case 4 -> this.reportList.get(rowIndex).setSellPrice((Double) aValue);
            case 5 -> this.reportList.get(rowIndex).setInvoicePrice((Double) aValue);
            case 6 -> this.reportList.get(rowIndex).setTotalPrice((Double) aValue);
            case 7 -> this.reportList.get(rowIndex).setProfit((Double) aValue);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}

}
