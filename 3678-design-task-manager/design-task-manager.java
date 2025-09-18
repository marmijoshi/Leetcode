class TaskManager {

    static class Task {
        int userId;
        int taskId;
        int priority;
        boolean removed;

        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
            this.removed = false;
        }
    }

    private PriorityQueue<Task> maxHeap;
    private Map<Integer, Task> taskMap;

    public TaskManager(List<List<Integer>> tasks) {
        this.maxHeap = new PriorityQueue<>((a, b) -> {
            if(a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority); // Higher priority first
            }
            return Integer.compare(b.taskId, a.taskId);
        });

        this.taskMap = new HashMap<>();

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            add(userId, taskId, priority);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        maxHeap.offer(task);
        taskMap.put(taskId, task);
    }
    
    public void edit(int taskId, int newPriority) {
        Task task = taskMap.get(taskId);
        task.removed = true;

        Task newTask = new Task(task.userId, taskId, newPriority);
        maxHeap.offer(newTask);
        taskMap.put(taskId, newTask);
    }
    
    public void rmv(int taskId) {
        Task task = taskMap.get(taskId);
        task.removed = true;
        taskMap.remove(taskId);
    }
    
    public int execTop() {
        while (!maxHeap.isEmpty() && maxHeap.peek().removed) {
            maxHeap.poll();
        }
        
        if (maxHeap.isEmpty()) {
            return -1;
        }

        Task topTask = maxHeap.poll();
        taskMap.remove(topTask.taskId);
        return topTask.userId;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */