package com.itlize.backend.demo.controllers;

import com.itlize.backend.demo.entities.Resource;
import com.itlize.backend.demo.services.ProjectResourceService;
import com.itlize.backend.demo.services.ResourceService;
import com.itlize.backend.demo.utils.dto.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
    private final ResourceService resourceService;
    private final ProjectResourceService projectResourceService;

    @Autowired
    public ResourceController(ResourceService resourceService, ProjectResourceService projectResourceService) {
        this.resourceService = resourceService;
        this.projectResourceService = projectResourceService;
    }

    @GetMapping
    public ResponseEntity<?> allResources() {
        return new ResponseEntity<>(resourceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> resourceById(@PathVariable int id) {
        return new ResponseEntity<>(resourceService.findOneById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> resourceById(@PathVariable String name) {
        return new ResponseEntity<>(resourceService.findAllByName(name), HttpStatus.OK);
    }

    @GetMapping("/project")
    public ResponseEntity<?> resourceByProject(@RequestParam("id") int id) {
        return new ResponseEntity<>(projectResourceService.findResourceByProject(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Resource resource = resourceService.findOneById(id);
        return new ResponseEntity<>(resourceService.deleteOne(resource), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody ResourceDto dto) {
        return new ResponseEntity<>(resourceService.updateOneById(dto), HttpStatus.OK);
    }

    @PostMapping("/create") //OK
    public ResponseEntity<?> createOne(@RequestBody Resource resource) {
        return new ResponseEntity<>(resourceService.createOne(resource), HttpStatus.CREATED);
    }
//    @GetMapping("/create/")
//    public ResponseEntity<?> createOne(
//                                       @RequestParam("name") String name
//                                       ) {
//        Resource r = new Resource();
//        //p.setCreatedTime(new Timestamp(new Date().getTime()));
//        r.setName(name);
//        //r.setProjectCode(projectCode);
//        return new ResponseEntity<>(resourceService.createOne(r), HttpStatus.CREATED);
//    }


    @GetMapping("/addProject/")
    public ResponseEntity<?> addProject(@RequestParam("pid") int pid,
                                        @RequestParam("rid") int rid) {
        return new ResponseEntity<>(projectResourceService.create(pid, rid), HttpStatus.CREATED);
    }


}
