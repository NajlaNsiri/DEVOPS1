package tn.esprit.devops_project.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.ActivitySectorDTO;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.iservices.IActivitySector;


import java.util.List;

@RestController
@AllArgsConstructor
public class ActivitySectorController {

    IActivitySector activitySectorService;

    @GetMapping("/activitySector")
    List<ActivitySector> retrieveAllActivitySectors(){
        return activitySectorService.retrieveAllActivitySectors();
    }

    @PostMapping("/activitySector")
    public ActivitySector addActivitySector(@RequestBody ActivitySectorDTO activitySectorDTO) {
        ActivitySector activitySector = new ActivitySector();
        activitySector.setCodeSecteurActivite(activitySectorDTO.getCodeSecteurActivite());
        activitySector.setLibelleSecteurActivite(activitySectorDTO.getLibelleSecteurActivite());
        return activitySectorService.addActivitySector(activitySector);
    }
    @DeleteMapping("/activitySector/{id}")
    void deleteActivitySector(@PathVariable Long id){
        activitySectorService.deleteActivitySector(id);
    }

    @PutMapping("/activitySector/{id}")
    public ActivitySector updateActivitySector(@PathVariable Long id, @RequestBody ActivitySectorDTO activitySectorDTO) {
        ActivitySector existingActivitySector = activitySectorService.retrieveActivitySector(id);
        existingActivitySector.setCodeSecteurActivite(activitySectorDTO.getCodeSecteurActivite());
        existingActivitySector.setLibelleSecteurActivite(activitySectorDTO.getLibelleSecteurActivite());
        return activitySectorService.updateActivitySector(existingActivitySector);
    }
    @GetMapping("/activitySector/{id}")
    ActivitySector retrieveActivitySector(@PathVariable Long id){
        return activitySectorService.retrieveActivitySector(id);
    }
}
