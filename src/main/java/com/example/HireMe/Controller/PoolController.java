package com.example.HireMe.Controller;

import com.example.HireMe.Functions;
import com.example.HireMe.Model.*;
import com.example.HireMe.Repository.HiringPoolsSkillsRepository;
import com.example.HireMe.Service.HiringPoolsService;
import com.example.HireMe.Service.HiringPoolsSkillsService;
import com.example.HireMe.Service.OrganisationHiringPoolsService;
import com.example.HireMe.Service.SkillsService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pool")
public class PoolController {
    private final SkillsService skillsService;
    private final OrganisationHiringPoolsService organisationHiringPoolsService;
    private final HiringPoolsSkillsService hiringPoolsSkillsService;
    private final HiringPoolsSkillsRepository hiringPoolsSkillsRepository;
    private final HiringPoolsService hiringPoolsService;
     Functions functions = new Functions();

    public PoolController(SkillsService skillsService, OrganisationHiringPoolsService organisationHiringPoolsService, HiringPoolsSkillsService hiringPoolsSkillsService, HiringPoolsSkillsRepository hiringPoolsSkillsRepository, HiringPoolsService hiringPoolsService) {
        this.skillsService = skillsService;
        this.organisationHiringPoolsService = organisationHiringPoolsService;
        this.hiringPoolsSkillsService = hiringPoolsSkillsService;
        this.hiringPoolsSkillsRepository = hiringPoolsSkillsRepository;
        this.hiringPoolsService = hiringPoolsService;
    }

    @GetMapping("/dashboard")
    public String openhiring( Model model) {
        List<OrganisationHiringPools> organisationHiringPools = organisationHiringPoolsService.getOrgList(functions.getOrganisation().getId());
        List<Skills> skills = skillsService.getall();
        List<OrganisationHiringPools> orghiringpools = organisationHiringPoolsService.getOrgList(functions.getOrganisation().getId());
        List<HiringPoolSkills> listofhiringpoolswithskills = hiringPoolsSkillsRepository.findAll(Sort.by(Sort.Direction.ASC, "hiringpoolid"));
        Map<Integer, List<HiringPoolSkills>> groupedDataMap = new HashMap<>();

        for (HiringPoolSkills record : listofhiringpoolswithskills) {
            groupedDataMap.computeIfAbsent(record.getHiringpoolid().getPool_id(), k -> new ArrayList<>()).add(record);
        }

        List<List<HiringPoolSkills>> groupedDataList = new ArrayList<>(groupedDataMap.values());
        List<String> listA = Arrays.asList("java");
        model.addAttribute("hiringPoolsList",organisationHiringPools);
        model.addAttribute("allhiringpoolswithskills",groupedDataList);
        model.addAttribute("subscribedpools",orghiringpools);
        model.addAttribute("skillList",skills);

        return "hiringpooldashboard";
    }
    @PostMapping("/addtopool" )
    public String openhiring(@RequestParam("selectedSkills") String selectedSkills, Model model) {
        List<String> selectedskillslist = List.of(selectedSkills.split(","));
        List<HiringPoolSkills> listofhiringpoolswithskills = hiringPoolsSkillsRepository.findAll(Sort.by(Sort.Direction.ASC, "hiringpoolid"));
        Map<Integer, List<HiringPoolSkills>> groupedDataMap = new HashMap<>();
        List<OrganisationHiringPools> hiringPoolsList = organisationHiringPoolsService.getOrgList(functions.getOrganisation().getId());


        for (HiringPoolSkills record : listofhiringpoolswithskills) {
            groupedDataMap.computeIfAbsent(record.getHiringpoolid().getPool_id(), k -> new ArrayList<>()).add(record);
        }
        List<List<HiringPoolSkills>> groupedDataList = new ArrayList<>(groupedDataMap.values());
        if(!functions.checkIfListAExists(groupedDataList,selectedskillslist)){
            HiringPools hiringPools = new HiringPools();
            OrganisationHiringPools organisationHiringPools = new OrganisationHiringPools();
            hiringPools.setPool_name("Hiring_Pool_For_"+selectedSkills.replace(",","_"));
            hiringPoolsService.savehiringpool(hiringPools);
            organisationHiringPools.setPoolid(hiringPoolsService.getHiringPoolDetailsByName(hiringPools.getPool_name()));
            Organisation organisation = new Organisation();
            OrganisationHiringPoolsID id = new OrganisationHiringPoolsID();
            id.setOrganisationId(organisation.getId());
            id.setPoolId(hiringPoolsService.getHiringPoolDetailsByName(hiringPools.getPool_name()).getPool_id());
            organisationHiringPools.setId(id);
            organisation.setId(functions.getOrganisation().getId());
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

        return "redirect:/pool/dashboard";
    }
}
