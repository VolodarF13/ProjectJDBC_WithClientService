package entity;

public class PriceOfEachProject {
    private String project;
    private int price;

    public PriceOfEachProject(String project, int price) {
        this.project = project;
        this.price = price;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "entity.PriceOfEachProject{" +
                "project='" + project + '\'' +
                ", price=" + price +
                '}';
    }
}
