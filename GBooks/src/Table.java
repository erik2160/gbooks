import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Table extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Age"};

    private final List<Person> list;

    public Table(List<Person> list){
        this.list = list;
    }

    public String getColumnName(int index){
        return columnNames[index];
    }


    @Override
    public int getRowCount() {
        if(this.list == null){
            return 0;
        }
        return this.list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return this.list.get(rowIndex).getName();
            case 1:
                return this.list.get(rowIndex).getAge();
            default:
                return 0;
        }
    }
}
