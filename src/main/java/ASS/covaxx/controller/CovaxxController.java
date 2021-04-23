package ASS.covaxx.controller;

import ASS.covaxx.model.Covaxx;
import ASS.covaxx.repo.CovaxxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class CovaxxController {

    @Autowired
    private CovaxxRepo CovaxxRepo;

    @GetMapping("/practices")
    public @ResponseBody Collection<Covaxx> getAll(
            @RequestParam(required = false) String practiceName){

        return this.CovaxxRepo.find(practiceName);
    }

    @GetMapping("/practices/{practiceId}")
   public @ResponseBody Covaxx getOne(
           @PathVariable String practiceId)
    {

        Covaxx covaxx = this.CovaxxRepo.getById(practiceId);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no practice with this practice_id");

            return covaxx;
   }

   @PostMapping("/practices")
   public @ResponseBody Covaxx createNew(@RequestBody Covaxx covaxx) {

       if (covaxx.practiceId == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Practice must specify a practice_Id");

       Covaxx existingCovaxx = this.CovaxxRepo.getById(covaxx.practiceId);
       if (existingCovaxx != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This practice_Id is already used");
       }

        this.CovaxxRepo.save(covaxx);

        return covaxx;
   }

   @PatchMapping("/practices/{practiceId}")
   public @ResponseBody Covaxx updateExisting(@PathVariable String practiceId, @RequestBody Covaxx changes) {

        Covaxx existingCovaxx = this.CovaxxRepo.getById(practiceId);

        if(existingCovaxx == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This practice_id does not exist");
        }

        if (changes.practiceName != null)
            existingCovaxx.practiceName = changes.practiceName;

        this.CovaxxRepo.save(existingCovaxx);

        return existingCovaxx;


   }

}
