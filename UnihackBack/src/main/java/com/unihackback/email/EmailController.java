package com.unihackback.email;

import com.unihackback.entity.Case;
import com.unihackback.repository.CaseRepository;
import com.unihackback.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/email")
public class EmailController {

    @Autowired
    CaseRepository caseRepository;

    private final Email email;

    public EmailController(Email email) {
        this.email = email;
    }

    @PostMapping(path="/send/{id}")
    public String sendRequest(@PathVariable String id, @RequestBody String summary)
    {
        String[] splited = summary.split("%%");
        Case pacientCase=caseRepository.findById(id).get();
        ArrayList<User> users=new ArrayList<>(pacientCase.getUsers());
        String body="Dear sir/madam\n This is the summary of your case:\n"+splited[0]+'\n' + splited[1] + '\n' + splited[2];
        for (int i=0;i<users.size();i++)
        {
            if (Objects.equals(users.get(i).getRoles(), "PATIENT"))
            {
                email.send(users.get(i).getEmail(),"Case summary",body);
                break;
            }
        }
        return "request done";
    }

}
