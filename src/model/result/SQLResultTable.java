package model.result;

import java.util.ArrayList;
import java.util.Map;
import javafx.collections.ObservableList;

public class SQLResultTable {
    public String name = null;
    public ArrayList<String> columns = null;
    public ObservableList<Map<String, String>> content = null;
    public SQLResultTable() {}
    public SQLResultTable(String name) {
        this.name = name;
    }
}
