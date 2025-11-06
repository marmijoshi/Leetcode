class Solution {
    private int[] parent;
    private TreeSet<Integer>[] onlineStations; // TreeSet to maintain sorted online stations per component
    
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        // Initialize Union-Find
        parent = new int[c + 1];
        onlineStations = new TreeSet[c + 1];
        
        for (int i = 1; i <= c; i++) {
            parent[i] = i;
            onlineStations[i] = new TreeSet<>();
            onlineStations[i].add(i); // Initially all stations are online
        }
        
        // Build the graph using Union-Find
        for (int[] conn : connections) {
            union(conn[0], conn[1]);
        }
        
        // Track which stations are online
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);
        
        List<Integer> results = new ArrayList<>();
        
        for (int[] query : queries) {
            int type = query[0];
            int station = query[1];
            
            if (type == 1) {
                // Maintenance check query
                if (online[station]) {
                    // If station is online, it resolves the check itself
                    results.add(station);
                } else {
                    // If station is offline, find smallest online station in same grid
                    int root = find(station);
                    TreeSet<Integer> onlineSet = onlineStations[root];
                    
                    if (onlineSet.isEmpty()) {
                        results.add(-1);
                    } else {
                        results.add(onlineSet.first()); // Get smallest online station
                    }
                }
            } else {
                // Type 2: Station goes offline
                online[station] = false;
                int root = find(station);
                onlineStations[root].remove(station);
            }
        }
        
        // Convert List to array
        int[] answer = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i);
        }
        
        return answer;
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
    
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            // Merge smaller set into larger set
            if (onlineStations[rootX].size() < onlineStations[rootY].size()) {
                parent[rootX] = rootY;
                onlineStations[rootY].addAll(onlineStations[rootX]);
                onlineStations[rootX].clear();
            } else {
                parent[rootY] = rootX;
                onlineStations[rootX].addAll(onlineStations[rootY]);
                onlineStations[rootY].clear();
            }
        }
    }
}