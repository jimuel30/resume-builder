package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Resume;

public interface ResumeService {
    Resume save(Resume resume);

    Resume delete(long resumeId, long userId);

}
