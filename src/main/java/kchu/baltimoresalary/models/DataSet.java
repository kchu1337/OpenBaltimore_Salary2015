package kchu.baltimoresalary.models;

public class DataSet {
    private String label;
    private int value;

    public DataSet() {
    }

    public DataSet(String label, int value) {
        this.label = label;
        this.value = value;
    }
    public DataSet(String label, String value) {
        this.label = label;
        this.value = Integer.parseInt(value);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "label=" + label + '\'' +
                ", value=" + value +
                '\n';
    }
}
