package de.viawhs.backend.rest;

import de.viawhs.backend.model.ResultFile;
import de.viawhs.backend.model.Wizard;
import de.viawhs.backend.service.AnalyticsService;
import de.viawhs.backend.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/server/analytics")
public class AnalyticsController {
    private AnalyticsService analyticsService;
    private FileService fileService;
    private Date date;
    public AnalyticsController(AnalyticsService analyticsService, FileService fileService) {
        this.analyticsService = analyticsService;
        this.fileService = fileService;
    }

    @PostMapping("/change-count")
    public String predictChangeCount(@RequestBody Wizard wizard) {
        String result = this.analyticsService.predictChangeCount(wizard);
        date = Calendar.getInstance().getTime();
        ResultFile resultFile = new ResultFile(wizard.getUser(), date.toString(), wizard.getUrl().split("/")[wizard.getUrl().split("/").length-1], result);
        fileService.saveResultFile(resultFile);
        return result;
    }

    @PostMapping("/ordering-issues")
    public String orderingIssues(@RequestBody Wizard wizard) {
        String result = this.analyticsService.orderingIssues(wizard);
        date = Calendar.getInstance().getTime();
        ResultFile resultFile = new ResultFile(wizard.getUser(), date.toString(), wizard.getUrl().split("/")[wizard.getUrl().split("/").length-1], result);
        fileService.saveResultFile(resultFile);
        return result;
    }

}
