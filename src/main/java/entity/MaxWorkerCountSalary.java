package entity;

public class MaxWorkerCountSalary {
    private String name;
    private int salary;

    public MaxWorkerCountSalary(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "entity.MaxWorkerCountSalary{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
