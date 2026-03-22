package project20280.priorityqueue;

import org.junit.jupiter.api.Test;

public class JobTests {
    private final static int POOL_SIZE = 10;
    private final static int QUEUE_SIZE = 10;
    @Test
    public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() {
        Job job1 = new Job("Job1", JobPriority.LOW, 1);
        Job job2 = new Job("Job2", JobPriority.MEDIUM, 2);
        Job job3 = new Job("Job3", JobPriority.HIGH, 3);
        Job job4 = new Job("Job4", JobPriority.MEDIUM, 4);
        Job job5 = new Job("Job5", JobPriority.LOW, 5);
        Job job6 = new Job("Job6", JobPriority.HIGH, 6);
        PriorityJobScheduler pjs = new PriorityJobScheduler(
                POOL_SIZE, QUEUE_SIZE);
        pjs.scheduleJob(job1);
        pjs.scheduleJob(job2);
        pjs.scheduleJob(job3);
        pjs.scheduleJob(job4);
        pjs.scheduleJob(job5);
        pjs.scheduleJob(job6);
// clean up
//        pjs.outputJobs();
    }
}
