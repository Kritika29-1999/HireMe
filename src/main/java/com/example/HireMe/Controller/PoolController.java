package com.example.HireMe.Controller;

import com.example.HireMe.Functions;
import com.example.HireMe.Model.*;
import com.example.HireMe.Repository.HiringPoolsSkillsRepository;
import com.example.HireMe.Service.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pool")
public class PoolController {
    private final SkillsService skillsService;
    private final OrganisationHiringPoolsService organisationHiringPoolsService;
    private final HiringPoolsSkillsService hiringPoolsSkillsService;
    private final HiringPoolsSkillsRepository hiringPoolsSkillsRepository;
    private final HiringPoolsService hiringPoolsService;
    private final JobSkillsService jobSkillsService;
     Functions functions = new Functions();

    public PoolController(SkillsService skillsService, OrganisationHiringPoolsService organisationHiringPoolsService, HiringPoolsSkillsService hiringPoolsSkillsService, HiringPoolsSkillsRepository hiringPoolsSkillsRepository, HiringPoolsService hiringPoolsService, JobSkillsService jobSkillsService) {
        this.skillsService = skillsService;
        this.organisationHiringPoolsService = organisationHiringPoolsService;
        this.hiringPoolsSkillsService = hiringPoolsSkillsService;
        this.hiringPoolsSkillsRepository = hiringPoolsSkillsRepository;
        this.hiringPoolsService = hiringPoolsService;
        this.jobSkillsService = jobSkillsService;
    }

    @GetMapping("/dashboard")
    public String openhiring(Model model, @ModelAttribute("alertMessage") String alertMessage) {
        Organisation organisation = functions.getOrganisation();
        List<OrganisationHiringPools> organisationHiringPools = organisationHiringPoolsService.getOrgList(organisation.getId());
        List<Skills> skills = skillsService.getall();
        List<HiringPoolSkills> listofhiringpoolswithskills = hiringPoolsSkillsRepository.findAll(Sort.by(Sort.Direction.ASC, "hiringpoolid"));
        Map<Integer, List<HiringPoolSkills>> groupedDataMap = listofhiringpoolswithskills.stream()
                .collect(Collectors.groupingBy(record -> record.getHiringpoolid().getPool_id()));
        List<List<HiringPoolSkills>> groupedDataList = new ArrayList<>(groupedDataMap.values());

        model.addAttribute("hiringPoolsList", organisationHiringPools);
        model.addAttribute("allhiringpoolswithskills", groupedDataList);
        model.addAttribute("subscribedpools", organisationHiringPools);
        model.addAttribute("skillList", skills);

        return "hiringpooldashboard";
    }

    @PostMapping("/addtopool" )
    public String addtopool(@RequestParam("selectedSkills") String selectedSkills) {
        List<String> selectedskillslist = List.of(selectedSkills.split(","));
        List<HiringPoolSkills> listofhiringpoolswithskills = hiringPoolsSkillsRepository.findAll(Sort.by(Sort.Direction.ASC, "hiringpoolid"));
        Map<Integer, List<HiringPoolSkills>> groupedDataMap = new HashMap<>();

        for (HiringPoolSkills record : listofhiringpoolswithskills) {
            groupedDataMap.computeIfAbsent(record.getHiringpoolid().getPool_id(), k -> new ArrayList<>()).add(record);
        }
        HiringPools hiringPools = new HiringPools();
        OrganisationHiringPools organisationHiringPools = new OrganisationHiringPools();
        Organisation organisation = new Organisation();
        organisation.setId(functions.getOrganisation().getId());
        String hiringpoolname = "Hiring_Pool_For_"+selectedSkills.replace(",","_");

        List<List<HiringPoolSkills>> groupedDataList = new ArrayList<>(groupedDataMap.values());
        if(!functions.checkIfListAExists(groupedDataList,selectedskillslist)){
            hiringPools.setPool_name("Hiring_Pool_For_"+selectedSkills.replace(",","_"));
            hiringPoolsService.savehiringpool(hiringPools);
            organisationHiringPools.setPoolid(hiringPoolsService.getHiringPoolDetailsByName(hiringPools.getPool_name()));
            OrganisationHiringPoolsID id = new OrganisationHiringPoolsID();
            id.setOrganisationId(organisation.getId());
            id.setPoolId(hiringPoolsService.getHiringPoolDetailsByName(hiringPools.getPool_name()).getPool_id());
            organisationHiringPools.setId(id);
            organisationHiringPools.setOrganisationid(organisation);
            organisationHiringPoolsService.saveorganisationpool(organisationHiringPools);
            HiringPoolSkills hiringPoolSkills = new HiringPoolSkills();
            hiringPoolSkills.setHiringpoolid(organisationHiringPools.getPoolid());
            HiringPoolSkillsID hiringPoolSkillsID = new HiringPoolSkillsID();
            hiringPoolSkillsID.setHiringpoolId(organisationHiringPools.getPoolid().getPool_id());
           for(String skills:selectedskillslist){
              hiringPoolSkills.setSkillid( skillsService.findbyname(skills));
               hiringPoolSkillsID.setSkillId(hiringPoolSkills.getSkillid().getSkill_id());
               hiringPoolSkills.setId(hiringPoolSkillsID);
               hiringPoolsSkillsService.savehiringpoolsskills(hiringPoolSkills);
           }

        }
        else{
            OrganisationHiringPoolsID id = new OrganisationHiringPoolsID();
            id.setOrganisationId(organisation.getId());
            id.setPoolId(hiringPoolsService.getHiringPoolDetailsByName(hiringpoolname).getPool_id());
            organisationHiringPools.setPoolid(hiringPoolsService.getHiringPoolDetailsByName(hiringpoolname));
            organisationHiringPools.setId(id);
            organisationHiringPools.setOrganisationid(organisation);
            organisationHiringPoolsService.saveorganisationpool(organisationHiringPools);

        }

        return "redirect:/pool/dashboard";
    }
    @GetMapping("/referdashboard" )
    public String openreferdashboard(Model model,@RequestParam("applicantId") int applicantid,@RequestParam("jobPostId") int jobPostId) {
    String skill="";
         System.out.println(skill);
    List<OrganisationHiringPools> orgsubscribedpools = organisationHiringPoolsService.getOrgList(functions.getOrganisation().getId());
    List<String> jobSkills = jobSkillsService.findskilllistfromjob(jobPostId);
    List<OrganisationHiringPools> mymatchedpools = new ArrayList<>();
    for(OrganisationHiringPools organisationHiringPools:orgsubscribedpools){
        HiringPools pool_id = organisationHiringPools.getPoolid();
        List<HiringPoolSkills> hiringPoolSkills = hiringPoolsSkillsService.getHiringPoolSkillsBypoolid(pool_id);
        List<String> poolskils = hiringPoolSkills.stream()
                .map(poolskill -> poolskill.getSkillid().getSkillname())
                .collect(Collectors.toList());
        if(poolskils.containsAll(jobSkills)){
            mymatchedpools.add(organisationHiringPools);
        }
    }



        model.addAttribute("orgsubscribedpools",mymatchedpools);
        return "referralpage";
    }
}
