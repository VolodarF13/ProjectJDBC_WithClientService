package entity;

public class LongestProject {
    private String name;
    private int month;

    public LongestProject(String name, int month) {
        this.name = name;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "entity.LongestProject{" +
                "name='" + name + '\'' +
                ", month=" + month +
                '}';
    }
}
