package project20280.priorityqueue;

public class Job implements Runnable {
    private String jobName;
    private JobPriority jobPriority;
    private int jobDuration;

    public Job(String name, JobPriority priority, int duration) {
        jobName = name;
        jobPriority = priority;
        jobDuration = duration;
    }

    @Override
    public void run() {
        System.out.println(this);
        // do some work
        try {
            Thread.sleep(1000); // to simulate actual execution time
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JobPriority getJobPriority(){
        return jobPriority;
    }

    public int getJobDuration(){
        return jobDuration;
    }

    public String toString(){
        return "Job:" + jobName + " Priority:" + jobPriority + " Duration: " + jobDuration;
    }
// standard setters and getters
}