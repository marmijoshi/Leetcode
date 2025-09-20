class Router {
    private int memoryLimit;
    private Queue<int[]> q;
    private Set<String> packetSet;
    private Map<Integer, List<Integer>> destToTimestamps;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.q = new LinkedList<>();
        this.packetSet = new HashSet<>();
        this.destToTimestamps = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;
        if(packetSet.contains(key)) return false;

        if(q.size() >= memoryLimit) {
            int[] old = q.poll();
            String oldKey = old[0] + "#" + old[1] + "#" + old[2];
            packetSet.remove(oldKey);

            List<Integer> list = destToTimestamps.get(old[1]);
            list.remove((Integer)old[2]);
        }

        q.offer(new int[]{source, destination, timestamp});
        packetSet.add(key);

        destToTimestamps.putIfAbsent(destination, new ArrayList<>());
        destToTimestamps.get(destination).add(timestamp);

        return true;
    }
    
    public int[] forwardPacket() {
        if(q.isEmpty()) return new int[]{};

        int[] packet = q.poll();
        String key = packet[0] + "#" + packet[1] + "#" + packet[2];
        packetSet.remove(key);

        List<Integer> list = destToTimestamps.get(packet[1]);
        list.remove((Integer)packet[2]);

        return packet;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> list = destToTimestamps.getOrDefault(destination, new ArrayList<>());
        if(list.isEmpty()) return 0;

        int startIdx = lowerBound(list, startTime);
        int endIdx = upperBound(list, endTime);

        return Math.max(0, endIdx - startIdx);
    }

    private int lowerBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while(l<r) {
            int mid = (l+r)/2;
            if(list.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while(l<r) {
            int mid = (l+r)/2;
            if(list.get(mid) > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */