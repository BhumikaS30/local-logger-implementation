import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import static java.util.Objects.nonNull;

public class LoggerImplementation implements Logger{

    private final HashMap<String, Process> processes;
    private final TreeMap<Long, Process> queue;

    public LoggerImplementation() {
        this.processes = new HashMap<>();
        this.queue = new TreeMap<>(Comparator.comparingLong(startTime -> startTime));
    }

    @Override
    public void start(String processId) {
        long now = System.currentTimeMillis();
        final Process process = new Process(processId, now);
        processes.put(processId, process);
        queue.put(now,process);
    }

    @Override
    public void end(String processId) {
        long now = System.currentTimeMillis();
        processes.get(processId).setEndTime(now);
    }

    @Override
    public String poll() {
        if (queue.isEmpty()) {
            System.out.println("Queue is Empty");
            return null;
        }
        Process process = queue.firstEntry().getValue();
        if (!queue.isEmpty() && nonNull(process.getEndTime())) {
            System.out.println(process.getProcessId() + " Started at : " + process.getStartTime() + " and Ended at : " + process.getEndTime());
            processes.remove(process.getProcessId());
            queue.pollFirstEntry();
        } else {
            System.out.println("No completed tasks in Queue " + queue.size());
        }
        return null;
    }

    public static void main(String[] args) {
        Logger logger = new LoggerImplementation();
        logger.start("1");
        logger.poll();
        logger.start("3");
        logger.poll();
        logger.end("1");
        logger.poll();
        logger.start("2");
        logger.poll();
        logger.end("2");
        logger.poll();
        logger.end("3");
        logger.poll();
        logger.poll();
        logger.poll();
    }
}
