package com.amit.conferencedemo.controllers;

import com.amit.conferencedemo.models.Speaker;
import com.amit.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController
{
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> getSepakersList()
    {
        return speakerRepository.findAll();
    }

    @GetMapping("{id}")
    public Speaker getSpeaker(@PathVariable Long id)
    {
       return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker createSpeaker(@RequestBody Speaker speaker)
    {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void deleteSpeaker(@PathVariable Long id)
    {
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Speaker updateSpeaker(@PathVariable Long id,@RequestBody Speaker speaker)
    {
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(speaker);
    }
}
