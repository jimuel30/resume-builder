package com.example.resumemicroservice.util;

import com.example.resumemicroservice.model.Resume;
import com.example.resumemicroservice.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResumeUtil {
    public static Resume resumeChecker(final User user, long resumeId){
        final List<Resume> resumeList = Objects.nonNull(user.getResumeList())?user.getResumeList():new ArrayList<>();
        Resume myResume = null;
        if(Objects.nonNull(resumeId)){
            for (Resume resume:resumeList) {
                if(resume.getResumeId() == resumeId){
                    myResume = resume;
                    break;
                }
            }
        }
        return myResume;
    }
}
