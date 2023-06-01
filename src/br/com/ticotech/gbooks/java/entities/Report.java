package br.com.ticotech.gbooks.java.entities;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<ReportItem> items;
    public List<ReportItem> getItems() {
        return items;
    }
    public void setItems(List<ReportItem> items) {
        this.items = items;
    }
    public void addItem(ReportItem item){
        this.items.add(item);
    }
    public Report(){
        this.items = new ArrayList<ReportItem>();
    }
}
