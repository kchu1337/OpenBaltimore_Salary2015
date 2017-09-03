package kchu.datatest.models;

public class DataSet {
    private String label;
    private double value;

    public DataSet() {
    }

    public DataSet(String label, double value) {
        this.label = label;
        this.value = value;
    }
    public DataSet(String label, String value) {
        this.label = label;
        this.value = Double.parseDouble(value);
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

    public void setValue(double value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = (double)value;
    }

    @Override
    public String toString() {
        return "label=" + label + '\'' +
                ", value=" + value +
                '\n';
    }
}
