package tt.blue;
import java.util.*;

class MeetingRoomsII253 {
    // 1. sort by start time
    // 2. use minheap to manage the end time
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0 || intervals == null){
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int[] interval: intervals){
            if(!minHeap.isEmpty() && interval[0] >= minHeap.peek()){
                minHeap.poll();
            }
            minHeap.add(interval[1]);
        }
        return minHeap.size();
    }
     // follow-up question "Output time interval for each room" and solution
    //   - 1. creat a data structure class to store the start and end
    //   - 2. assign the new or old room for the time 
    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    // use iteration of current used room to find the available room -> o(n ^ 2)   
    public static List<List<Interval>> assignMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new ArrayList<>();
        }

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<List<Interval>> roomAssignments = new ArrayList<>();
        
        for (int[] intervalArr : intervals) {
            Interval interval = new Interval(intervalArr[0], intervalArr[1]);
            boolean placed = false;

            for (List<Interval> room : roomAssignments) {
                if (room.get(room.size() - 1).end <= interval.start) {
                    room.add(interval);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                List<Interval> newRoom = new ArrayList<>();
                newRoom.add(interval);
                roomAssignments.add(newRoom);
            }
        }

        return roomAssignments;
    }
    // Wrong Sol:
    // use treemap to find the time interval for each room On (log n) but not get the minumum meeting room
    // use TreeMap function -> ceilingEntry(parameter)O(log k): return the key that is larger or equal than the parameter and has the smallest difference from the parameter among all keys    
    // public List<List<Interval>> timeIntervalForEachRoom(int[][] intervals){
    //     if(intervals.length == 0 || intervals == null){
    //         return new ArrayList<>();
    //     }
    //     Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    //     PriorityQueue<Interval> minHeap = new PriorityQueue<>();
    //     TreeMap<Integer, List<Interval>> availableRooms = new TreeMap<>();
    //     for(int[] interval: intervals){
    //         Interval inter = new Interval(interval[0], interval[1]);
    //         Map.Entry<Integer, List<Interval>> availableRoomEntry = availableRooms.ceilingEntry(inter.start);
    //         List<Interval> roomMeetings;
    //         if (availableRoomEntry != null) {
    //             roomMeetings = availableRoomEntry.getValue();
    //             availableRooms.remove(availableRoomEntry.getKey());
    //         } else {
    //             roomMeetings = new ArrayList<>();
    //         }
    //         roomMeetings.add(inter);
    //         availableRooms.put(inter.end, roomMeetings);
    //     }
    //     return new ArrayList<>(availableRooms.values());
   
    // }
    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}, {25, 35}};
        List<List<Interval>> assignments = assignMeetingRooms(intervals);
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println("Room " + (i + 1) + ": " + assignments.get(i));
        }
        // Room 1: [[0, 30]]
        // Room 2: [[5, 10], [15, 20], [25, 35]]
        
        // List<List<Interval>> assignmentsTwo = timeIntervalForEachRoom(intervals);
        // for (int i = 0; i < assignmentsTwo.size(); i++) {
        //     System.out.println("Room " + (i + 1) + ": " + assignmentsTwo.get(i));
        // }        
    }
}
