import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TestTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Age"};

    private final List<Person> list;

    public TestTableModel(List<Person> list){
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
        return switch (columnIndex) {
            case 0 -> this.list.get(rowIndex).getName();
            case 1 -> this.list.get(rowIndex).getAge();
            default -> 0;
        };
    }
}
