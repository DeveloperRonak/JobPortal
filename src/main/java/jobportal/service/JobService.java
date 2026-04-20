package jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jobportal.entity.Job;
import jobportal.entity.User;
import jobportal.repository.JobRepository;
import jobportal.repository.UserRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Job createJob(Job job, String email) {
        User recruiter = userRepository.findByEmail(email).orElseThrow();
        job.setRecruiter(recruiter);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}