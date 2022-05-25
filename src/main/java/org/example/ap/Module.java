package org.example.ap;

import eu.ibagroup.easyrpa.engine.annotation.ApModuleEntry;
import eu.ibagroup.easyrpa.engine.apflow.ApModule;
import eu.ibagroup.easyrpa.engine.apflow.TaskOutput;
import eu.ibagroup.easyrpa.engine.apflow.history.Execution;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ResourceDto;
import org.example.tasks.CreateZipTask;
import org.example.tasks.DownloadPageTask;
import org.example.tasks.GetResourceContentTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@ApModuleEntry(name = "Assignment 1", description = "Assignment 1")
public class Module extends ApModule {

    @Override
    public TaskOutput run() throws Exception {
        getInput().set("input_file", "input.json");
        CompletableFuture<TaskOutput> result = execute(getInput(), GetResourceContentTask.class);

        List<ResourceDto> resources = result.get().get("resources", List.class);


        TaskOutput mergedOutput = split(getInput(), resources, (inp, resource) ->{
            inp.set("resource", resource);
            return execute(inp, DownloadPageTask.class);
        }).merge((finalOutput, taskOutputs) ->{
            List<ResourceDto> processedResources = taskOutputs.stream()
                    .map(output ->output.get("resource", ResourceDto.class))
                    .collect((Collectors.toList()));
            finalOutput.set("processed_resources", processedResources);
        }).get();
        List<ResourceDto> processedResources = mergedOutput.get("processed_resources",List.class);
        processedResources.forEach(res -> {
            log.info("############# " + res);
        });

        execute(getInput(), CreateZipTask.class);



//        return execute(mergedOutput, ProcessResultsTask.class).get();

        return doNotCareOfResult();
    }
}