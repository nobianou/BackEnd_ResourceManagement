package com.itlize.backend.demo.services;

import com.itlize.backend.demo.entities.Project;
import com.itlize.backend.demo.entities.ProjectResource;
import com.itlize.backend.demo.entities.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectResourceService {

    List<Project> findProjectByResource(int id);

    List<ProjectResource> findResourceByProject(int id); //List<Resource>

    ProjectResource create(int p_id, int r_id);

    ProjectResource update(int id, int p_id, int r_id);

    void deleteById(int id);


//    Boolean deleteRelationshipByResource(int id);
//
//    Boolean deleteRelationshipByProject(int id);
}
