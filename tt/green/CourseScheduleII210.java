import java.util.*;

class CourseScheduleII210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // topological sort: a sorting way in the directed graph is uesed to sort the node in the correct order without circle
        // 1. create adj and indegree list(curcial for this problem because we need to use this propery to find all coures that we can take follow the correct rule)
        // add all courses that without pre in the queue
        // 2. use bfs to track the indegree, every iteration remove one indegree, if indegree ==0 which means no pre need to take so add it in the queue, repeat this process, until the queue is empty, we can get all course we can take base on the pre then main rule
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        // adj list for directed graph, we can travel from node a to node b, so node a is the key and value is the b
        for(int i = 0; i < prerequisites.length; i++){
            if(!adj.containsKey(prerequisites[i][1])){
                adj.put(prerequisites[i][1], new ArrayList<>());
            }
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        int c = 0;
        int[] ans = new int[numCourses];
        while(!q.isEmpty()){
            int cur = q.poll();
            ans[c] = cur;
            if(adj.containsKey(cur)){
                for(int m: adj.get(cur)){
                    indegree[m]--;
                    if(indegree[m] == 0){
                        q.add(m);
                    }
                }
            }
            c++;
        }
        if(c == numCourses){
            return ans;
        }else{
            return new int[0];
        }
    }
    public static void main(String[] args){
        CourseScheduleII210 courseSchedule = new CourseScheduleII210();
        int numCourses = 4; 
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] res = courseSchedule.findOrder(numCourses, prerequisites);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
        // [0,1,2,3] or [0,2,1,3] are both correct
    }
}