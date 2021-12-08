package com.itlize.backend.demo.controllers;

import com.itlize.backend.demo.entities.Project;
import com.itlize.backend.demo.services.ProjectResourceService;
import com.itlize.backend.demo.services.ProjectService;
import com.itlize.backend.demo.utils.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectResourceService projectResourceService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectResourceService projectResourceService) {
        this.projectService = projectService;
        this.projectResourceService = projectResourceService;
    }

    /**
     * need admin or root role
     */
    @GetMapping
    public ResponseEntity<?> allProjects() {
        List<Project> list = projectService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> allProjectsByUserId(@PathVariable int id) {
        return new ResponseEntity<>(projectService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> projectById(@PathVariable int id) {
        return new ResponseEntity<>(projectService.findOneById(id), HttpStatus.OK);
    }

    /**
     * root admin or userid owns this
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Project project = projectService.findOneById(id);
        return new ResponseEntity<>(projectService.deleteOne(project), HttpStatus.NO_CONTENT);
    }

    //    @GetMapping("/update/")
//    public ResponseEntity<?> updateById(@RequestParam("id") int id,
//                                        @RequestParam(value = "name", required = false) String name,
//                                        @RequestParam(value = "projectCode", required = false) String projectCode) {
//        ProjectDto dto = new ProjectDto();
//        dto.setId(id);
//        dto.setName(name);
//        dto.setProjectCode(projectCode);
//        return new ResponseEntity<>(projectService.updateOneById(dto), HttpStatus.OK);
//    }
    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody ProjectDto dto) {
        return new ResponseEntity<>(projectService.updateOneById(dto), HttpStatus.ACCEPTED);
    }


//    @GetMapping("/create/")
//    public ResponseEntity<?> createOne(@RequestParam("id") int id,
//                                       @RequestParam("name") String name,
//                                       @RequestParam("projectCode") String projectCode) {
//        Project p = new Project();
//        p.setCreatedTime(new Timestamp(new Date().getTime()));
//        p.setName(name);
//        p.setProjectCode(projectCode);
//        return new ResponseEntity<>(projectService.createOne(p, id), HttpStatus.CREATED);
//    }

    @PostMapping("/create") //OK
    public ResponseEntity<?> createOne(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.createOne(project), HttpStatus.CREATED);
    }


    @GetMapping("/resource/{id}") //Add resource to Project
    public ResponseEntity<?> findResourceByProject(@PathVariable int id) {
        return new ResponseEntity<>(projectResourceService.findResourceByProject(id), HttpStatus.OK);
    }

    @PostMapping("/addResource") //Add resource to Project
    public ResponseEntity<?> addResource(@RequestParam("pid") int pid,
                                         @RequestParam("rid") int rid) {
        return new ResponseEntity<>(projectResourceService.create(pid, rid), HttpStatus.CREATED);
    }

    @PutMapping("/updateResource/{id}") //Replace the Resource attached to the project with another resource
    public ResponseEntity<?> addResource(@PathVariable int id, @RequestParam("pid") int pid,
                                         @RequestParam("rid") int rid) {
        return new ResponseEntity<>(projectResourceService.update(id, pid, rid), HttpStatus.ACCEPTED);
    }

    //@DeleteMapping("/resource/{id}")
    @DeleteMapping("/removeResource/{id}")
    public ResponseEntity<Void> deleteResourceById(@PathVariable int id) {
        projectResourceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
