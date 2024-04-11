package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Resume;
import com.example.resumemicroservice.modelservice.ResumeService;
import com.example.resumemicroservice.repo.ResumeRepo;
import com.fasterxml.jackson.core.ObjectCodec;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepo resumeRepo;

    public ResumeServiceImpl(final ResumeRepo resumeRepo) {
        this.resumeRepo = resumeRepo;
    }

    @Override
    public Resume save(Resume resume) {
        Resume savedResume = update(resume);
        if(!Objects.nonNull(savedResume)){
            savedResume =  resumeRepo.save(resume);
        }
        return savedResume;
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

    @Override
    public Resume update(Resume resume) {
        final Optional<Resume>resumeOptional =resumeRepo
                .findByResumeIdAndUserId(resume.getResumeId(),resume.getUserId());
        Resume updatedResume =null;
        if(resumeOptional.isPresent()){
            updatedResume =resumeRepo.save(resume);
        }
        return updatedResume;
    }
}
