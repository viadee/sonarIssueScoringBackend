package de.viawhs.backend.rest;

import de.viawhs.backend.model.Wizard;
import de.viawhs.backend.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server/analytics")
public class AnalyticsController {
    private AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/change-count")
    public String predictChangeCount(@RequestBody Wizard wizard) {
        return this.analyticsService.predictChangeCount(wizard);
    }

    @PostMapping("/ordering-issues")
    public String orderingIssues(@RequestBody Wizard wizard) {
        return this.analyticsService.orderingIssues(wizard);
    }

}
