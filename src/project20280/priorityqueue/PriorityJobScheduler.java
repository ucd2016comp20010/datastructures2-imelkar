package project20280.priorityqueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityJobScheduler {
    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler
            = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<Job> priorityQueue;
    public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(
                queueSize,
                Comparator.comparing(Job::getJobPriority).thenComparing(Job::getJobDuration));
        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                } catch (InterruptedException e) {
// exception needs special handling
                    System.out.println("Error occurred");
                    break;
                }
            }
        });
    }
    public void scheduleJob(Job job) {
        priorityQueue.add(job);
    }

    public void outputJobs(){
        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}