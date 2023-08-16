package com.example.HireMe.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.example.HireMe.FileConfig.FileService;
import com.example.HireMe.Functions;
import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.ApplicantJobHistory;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.Skills;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import com.example.HireMe.Service.ApplicantJobHistoryService;
import com.example.HireMe.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 * A REST Controller that exposes upload, download,
 * list, and delete file operations on Google Cloud Storage.
 */
@Controller
@RequestMapping("/api/v1/files")
public class FileController {
    Functions functions = new Functions();

    @Autowired
    FileService fileService;
    @Autowired
    ApplicantJobHistoryService applicantJobHistoryService;
    @Autowired
    ApplicantJobHistoryRepository applicantJobHistoryRepository;
    @Autowired
    JobService jobService;

    //List all file name
    @GetMapping
    public ResponseEntity<List<String>> listOfFiles() {

        List<String> files = fileService.listOfFiles();

        return ResponseEntity.ok(files);
    }

    //Upload file

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("resume") MultipartFile file, @RequestParam("jobpost")int jobPost
            ) throws IOException {

        boolean checkifdataexists = applicantJobHistoryService.findbyappidandjobid(jobPost,functions.getApplicant().getId());
        if(!checkifdataexists) {
            JobPost jobPost1 = new JobPost();
            jobPost1.setId(jobPost);
            jobService.update(jobPost, jobService.getjobPostbyId(jobPost).getTotalapplicant() + 1);


            String url = fileService.uploadFile(file);
            ApplicantJobHistory applicantJobHistory = new ApplicantJobHistory();
            applicantJobHistory.setApplicant_id(functions.getApplicant());
            applicantJobHistory.setJob_id(jobPost1);
            applicantJobHistory.setDate(functions.getdate());
            applicantJobHistory.setStatus("Open");
            applicantJobHistory.setUrl(url);

            applicantJobHistoryService.insert(applicantJobHistory);
            return ResponseEntity.ok(" File uploaded successfully");
        }
        else {
            return ResponseEntity.ok(" You have already applied for this job" );

        }


    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(
            @RequestParam String fileName) {

        fileService.deleteFile(fileName);

        return ResponseEntity.ok(" File deleted successfully");
    }
    @GetMapping("/download/{apphistoryid}")
    public RedirectView downloadFile(
            @PathVariable int apphistoryid)  {
        ApplicantJobHistory applicantJobHistory1 = applicantJobHistoryRepository.getApplicantJobHistoryById(apphistoryid);

        return new RedirectView(applicantJobHistory1.getUrl());

    }


}
