package meta.green;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BuildingWithAnOceanViewFollowUp1762 {
    private Stack<Integer> stack;

    public BuildingWithAnOceanViewFollowUp1762() {
        stack = new Stack<>();
    }

    public void addBuilding(int height) {
        while (!stack.isEmpty() && stack.peek() <= height) {
            stack.pop();
        }
        stack.push(height);
    }

    public List<Integer> getBuildingsWithView() {
        List<Integer> buildingsWithView = new ArrayList<>(stack);
        Collections.reverse(buildingsWithView);
        return buildingsWithView;
    }

    public static void main(String[] args) {
        BuildingWithAnOceanViewFollowUp1762 oceanView = new BuildingWithAnOceanViewFollowUp1762();

        // Test data: Heights of buildings as they come in (streaming)
        int[] buildingHeights = { 4, 2, 3, 1, 5, 2, 3, 4 };

        // Process each building
        for (int height : buildingHeights) {
            oceanView.addBuilding(height);
        }

        // Get and display buildings with ocean view
        List<Integer> buildingsWithView = oceanView.getBuildingsWithView();
        System.out.println("Buildings with ocean view (in increasing order): " + buildingsWithView);
    }
}
