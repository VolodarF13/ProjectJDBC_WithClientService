import database.DatabaseQueryService;
import entity.*;

import java.util.List;

public class TestDateBase {
    public static void main(String[] args) {

        List<MaxProjectCountClient> maxProjectCountClient = new DatabaseQueryService().findMaxProjectClient();
        System.out.println("maxProjectCountClient = " + maxProjectCountClient.toString());

        List<LongestProject> longestProjects = new DatabaseQueryService().longestProjects();
        System.out.println("longestProjects.toString() = " + longestProjects.toString());

        List<MaxWorkerCountSalary> maxWorkerCountSalaries = new DatabaseQueryService().findMaxSalaryWorker();
        System.out.println("maxWorkerCountSalaries.toString() = " + maxWorkerCountSalaries.toString());

        List<YoungestEldestWorker> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorker();
        System.out.println("youngestEldestWorkers.toString() = " + youngestEldestWorkers.toString());

        List<PriceOfEachProject> priceOfEachProjects = new DatabaseQueryService().priceOfEachProjects();
        System.out.println("priceOfEachProjects.toString() = " + priceOfEachProjects.toString());

    }
}
