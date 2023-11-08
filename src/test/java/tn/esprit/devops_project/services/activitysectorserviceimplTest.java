package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.controllers.ActivitySectorController;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class activitysectorserviceimplTest {

    @InjectMocks
    private ActivitySectorController activitySectorController;

    @Mock
    private IActivitySector activitySectorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetActivitySectors() {
        List<ActivitySector> mockActivitySectors = new ArrayList<>();
        mockActivitySectors.add(new ActivitySector(1L, "Code1", "Sector1"));
        mockActivitySectors.add(new ActivitySector(2L, "Code2", "Sector2"));

        Mockito.when(activitySectorService.retrieveAllActivitySectors()).thenReturn(mockActivitySectors);

        List<ActivitySector> response = activitySectorController.retrieveAllActivitySectors();

        assertEquals(mockActivitySectors, response);
    }
}
