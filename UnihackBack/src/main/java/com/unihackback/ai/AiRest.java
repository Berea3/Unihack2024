package com.unihackback.ai;

import com.unihackback.entity.Report;
import com.unihackback.repository.CaseRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AiRest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private CaseRepository caseRepository;

    @GetMapping("/ai/summarize-case/{id}/{date1}/{date2}")
    public String aiSummarizeCaseWithDates(@PathVariable String id, @PathVariable LocalDate date1, @PathVariable LocalDate date2)
    {
        String message;
        message="Summarize this text:";
        List<Report> caseReports=caseRepository.findById(id).get().getReports();
        ArrayList<Report> reports=new ArrayList<>();
        if (date1==null && date2==null)
        {
            for (int i=0;i<caseReports.size();i++)
            {
                Report report=caseReports.get(i);
                if (report.getReportDate().isAfter(date1) && report.getReportDate().isBefore(date2)) reports.add(report);
            }
        }
        else
        {
            reports=new ArrayList<>(reports);
        }
        for (int i=0;i< reports.size();i++)
        {
            message=message+reports.get(i).getReportDescription();
        }
        return chatModel.call(message);
    }

    @GetMapping("/ai/summarize-case/{id}")
    public String aiSummarizeCase(@PathVariable String id)
    {
        String message;
        message="Summarize this text:";
        ArrayList<Report> reports=new ArrayList<>(caseRepository.findById(id).get().getReports());
        for (int i=0;i< reports.size();i++)
        {
            message=message+reports.get(i).getReportDescription();
        }
        return chatModel.call(message);
    }

    @PostMapping("/ai/write-prescription")
    public String aiWritePrescription(@RequestBody String summary)
    {
        String message="Write a medical prescription for these symptoms:"+ summary;
        return chatModel.call(message);
    }

    @GetMapping("/ai")
    public String aiSummarizeCase(@RequestBody Message message)
    {
        return chatModel.call(message.message);
    }
}
