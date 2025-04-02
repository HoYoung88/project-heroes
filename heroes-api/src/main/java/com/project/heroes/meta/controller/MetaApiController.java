package com.project.heroes.meta.controller;

import com.project.heroes.meta.dto.MetaDto;
import com.project.heroes.meta.service.MetaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/meta-data")
@RequiredArgsConstructor
public class MetaApiController {

    private final MetaService metaService;

    @GetMapping(value = "")
    public MetaDto getMeta() {
        return metaService.getMeta();
    }

}
