package com.itlize.backend.demo.services.impl;

import com.itlize.backend.demo.entities.Project;
import com.itlize.backend.demo.entities.ProjectResource;
import com.itlize.backend.demo.entities.Resource;
import com.itlize.backend.demo.repositories.ProjectRepository;
import com.itlize.backend.demo.repositories.ProjectResourceRepository;
import com.itlize.backend.demo.repositories.ResourceRepository;
import com.itlize.backend.demo.services.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

    private final ProjectRepository projectRepository;
    private final ResourceRepository resourceRepository;
    private final ProjectResourceRepository projectResourceRepository;

    @Autowired
    public ProjectResourceServiceImpl(ProjectRepository projectRepository, ResourceRepository resourceRepository, ProjectResourceRepository projectResourceRepository) {
        this.projectRepository = projectRepository;
        this.resourceRepository = resourceRepository;
        this.projectResourceRepository = projectResourceRepository;
    }

    @Override
    public List<Project> findProjectByResource(int id) {
        List<Integer> ids = projectResourceRepository.findAllProjectIdByResourceId(id);

        return projectRepository.findAllById(ids);

//        Resource resource = resourceRepository.findById(id).orElse(null);
//        assert resource != null;
//        List<ProjectResource> res = resource.getProjectResourceList();
//        List<Resource> r = res.stream().
//                map(ProjectResource::getResource).
//                collect(Collectors.toList());
    }

    @Override //Did not Work out
    public List<ProjectResource> findResourceByProject(int id) { //List<Resource>
        List<Integer> ids = projectResourceRepository.findAllResourceIdByProjectId(id);
        // Need to do something here

        return projectResourceRepository.findAllById(ids);

    }

//    @Override
//    public List<Resource> findResourceByProject(int id) { //List<Resource>
//        List<Integer> ids = projectResourceRepository.findAllResourceIdByProjectId(id);
//        // Need to do something here
//
//        return resourceRepository.findAllById(ids);
//
//    }

    @Override
    @Transactional
    public ProjectResource create(int p_id, int r_id) {
        ProjectResource projectResource = new ProjectResource();
//        if (projectResourceRepository.existsDistinctByProject_IdAndResource_Id(p_id, r_id)) return projectResource;

        Project project = projectRepository.findById(p_id).orElse(null);
        Resource resource = resourceRepository.findById(r_id).orElse(null);
        if (project != null && resource != null) {
            projectResource.setProject(project);
            projectResource.setResource(resource);
            projectResource = projectResourceRepository.save(projectResource);
        }
        return projectResource;
    }

    @Override
    @Transactional
    public ProjectResource update(int id, int p_id, int r_id) {
//        if (projectResourceRepository.existsDistinctByProject_IdAndResource_Id(p_id, r_id)) return null;

        ProjectResource projectResource = projectResourceRepository.findById(id).orElse(null);
        if (projectResource != null) {
            Project project = projectRepository.findById(p_id).orElse(null);
            Resource resource = resourceRepository.findById(r_id).orElse(null);
            if (project != null && resource != null) {
                projectResource.setProject(project);
                projectResource.setResource(resource);
                projectResource = projectResourceRepository.save(projectResource);

            }

        }
        return projectResource;

    }

    @Override
    public void deleteById(int id) {
        projectResourceRepository.deleteById(id);
    }

//    @Override
//    public Boolean deleteRelationshipByResource(int id) {
//        projectResourceRepository.deleteAllByResource_Id(id);
//        return !projectResourceRepository.existsAllByResource_Id(id);
//    }
//
//    @Override
//    public Boolean deleteRelationshipByProject(int id) {
//        projectResourceRepository.deleteAllByProject_Id(id);
//        return !projectResourceRepository.existsAllByProject_Id(id);
//    }


}
