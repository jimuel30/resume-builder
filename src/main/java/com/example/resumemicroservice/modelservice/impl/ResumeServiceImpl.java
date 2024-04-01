package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Resume;
import com.example.resumemicroservice.modelservice.ResumeService;
import com.example.resumemicroservice.repo.ResumeRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepo resumeRepo;

    public ResumeServiceImpl(final ResumeRepo resumeRepo) {
        this.resumeRepo = resumeRepo;
    }

    @Override
    public Resume save(Resume resume) {
        return resumeRepo.save(resume);
    }

    @Override
    public Resume delete(final long resumeId, final long userId) {
        final Optional<Resume> resumeOptional = resumeRepo.findByResumeIdAndUserId(resumeId,userId);
        Resume deletedResume =null;
        if(resumeOptional.isPresent()){
            deletedResume =  resumeOptional.get();
            resumeRepo.delete(deletedResume);
        }
        return deletedResume;
    }
}
