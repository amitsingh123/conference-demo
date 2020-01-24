package com.amit.conferencedemo.controllers;

import com.amit.conferencedemo.models.Session;
import com.amit.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController
{
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getSessionList()
    {
        return sessionRepository.findAll();
    }
    @GetMapping("{id}")
    public Session getSession(@PathVariable Long id)
    {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session createSession(@RequestBody Session session)
    {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void deleteSession(@PathVariable Long id)
    {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Session updateSession(@PathVariable Long id,@RequestBody Session session)
    {
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
