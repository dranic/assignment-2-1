package org.example.tasks;

import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.annotation.Input;
import eu.ibagroup.easyrpa.engine.annotation.OnError;
import eu.ibagroup.easyrpa.engine.annotation.Output;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import eu.ibagroup.easyrpa.engine.exception.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.example.entity.ResourceDto;

import java.io.File;
import java.net.URL;
import java.util.List;

@ApTaskEntry(name = "Parsing input.json file")
@Slf4j
public class ProccessAbortedRecordsTask extends ApTask {
    @Input("resources")
    private List<ResourceDto> resource;

    @Override
    public void execute() throws Exception {

    }
}