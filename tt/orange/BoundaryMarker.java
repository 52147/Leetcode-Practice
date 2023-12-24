public class BoundaryMarker {
    public void markBoundary(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return;

        int m = grid.length;
        int n = grid[0].length;
      
        // Iterate over the matrix to find boundary 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // A cell is a boundary if it is a 1 and on the edge or next to a 0
                boolean isBoundary = grid[i][j] == '1' && 
                    (i == 0 || i == m-1 || j == 0 || j == n-1 || 
                    grid[i-1][j] == '0' || grid[i+1][j] == '0' ||
                    grid[i][j-1] == '0' || grid[i][j+1] == '0');

                if (isBoundary) {
                    grid[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        BoundaryMarker bm = new BoundaryMarker();
        char[][] image = {
            {'0', '1', '1', '1', '1'},
            {'0', '1', '1', '1', '1'},
            {'0', '1', '1', '1', '0'}
        };

        bm.markBoundary(image);

        for (char[] row : image) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

