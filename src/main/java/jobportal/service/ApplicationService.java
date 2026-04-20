package jobportal.service;

import jobportal.entity.Application;
import jobportal.entity.Job;
import jobportal.entity.User;
import jobportal.repository.ApplicationRepository;
import jobportal.repository.JobRepository;
import jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    public Application applyForJob(Long jobId, String email) {
        User candidate = userRepository.findByEmail(email).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow();

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus("APPLIED");
        application.setAppliedDate(LocalDateTime.now());

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}