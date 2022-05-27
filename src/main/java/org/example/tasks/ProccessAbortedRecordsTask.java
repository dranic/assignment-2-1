package org.example.tasks;

import java.nio.file.Files;
import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.annotation.Input;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ResourceDto;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ApTaskEntry(name = "Parsing input.json file")
@Slf4j
public class ProccessAbortedRecordsTask extends ApTask {
    @Input("errors")
    private List<ResourceDto> resources;

    @Override
    public void execute() throws Exception {
        List<String> fileContent = new ArrayList();
        resources.forEach(resource -> {fileContent.add(resource.getName() + " - " + resource.getUrl());});
        Path file = Paths.get("errors.txt");
        Files.write(file, fileContent, StandardCharsets.UTF_8);
    }
}