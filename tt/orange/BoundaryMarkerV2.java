public class BoundaryMarkerV2 {
    // The improved version of the BoundaryMarker
    // improve the time performance by mark the visited cell to '2' so we can avoid to reviist the visited cell
    // change to 2 not 0 is because we want the visited cell to be more visual clarity and not mess up with the water 0
    public void markIslandAges(char[][] grid) {
        if (grid == null || grid.length == 0) return;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfsMarkAge(grid, i, j);
                }
            }
        }
    }

    private void dfsMarkAge(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }

        // Check if the current 1 is on the boundary
        if (isBoundary(grid, i, j)) {
            grid[i][j] = 'X';
        } else {
            grid[i][j] = '2'; // Mark as visited but not boundary
        }

        // Explore adjacent cells
        if (i > 0 && grid[i - 1][j] == '1') dfsMarkAge(grid, i - 1, j);
        if (i < grid.length - 1 && grid[i + 1][j] == '1') dfsMarkAge(grid, i + 1, j);
        if (j > 0 && grid[i][j - 1] == '1') dfsMarkAge(grid, i, j - 1);
        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') dfsMarkAge(grid, i, j + 1);
    }

    private boolean isBoundary(char[][] grid, int i, int j) {
        return i == 0 || i == grid.length - 1 || j == 0 || j == grid[i].length - 1 ||
               grid[i - 1][j] == '0' || grid[i + 1][j] == '0' ||
               grid[i][j - 1] == '0' || grid[i][j + 1] == '0';
    }

    public static void main(String[] args) {
        BoundaryMarkerV2 solution = new BoundaryMarkerV2();
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '1', '0'},
            {'0', '0', '0', '1', '1'}
        };

        solution.markIslandAges(grid);

        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

