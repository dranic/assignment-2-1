package org.example.ap;

import eu.ibagroup.easyrpa.engine.annotation.ApModuleEntry;
import eu.ibagroup.easyrpa.engine.apflow.ApModule;
import eu.ibagroup.easyrpa.engine.apflow.TaskOutput;
import eu.ibagroup.easyrpa.engine.boot.ApModuleRunner;
import eu.ibagroup.easyrpa.engine.boot.configuration.DevelopmentConfigurationModule;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ResourceDto;
import org.example.tasks.CreateZipTask;
import org.example.tasks.DownloadPageTask;
import org.example.tasks.GetResourceContentTask;
import org.example.tasks.ProccessAbortedRecordsTask;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@ApModuleEntry(name = "Assignment 1", description = "Assignment 1")
public class Module extends ApModule {

    @Override
    public TaskOutput run() throws Exception {
        getInput().set("input_file", "input.json");
        TaskOutput result = execute(getInput(), GetResourceContentTask.class).get();

        List<ResourceDto> resources = result.get("resources", List.class);

        BiConsumer<TaskOutput, List<TaskOutput>> mergeStrategy =
                (resOut, outputs) -> {
                    List<ResourceDto> success = new ArrayList<>();
                    List<ResourceDto> errors = new ArrayList<>();
                    ResourceDto res;
                    for (TaskOutput out : outputs) {
                        res = out.get("resource", ResourceDto.class);
                        if (res.isErrorOccurred()) {
                            errors.add(res);
                        } else {
                            success.add(res);
                        }
                    }
                    resOut.set("resources", success);
                    resOut.set("errors", errors);
                };


        TaskOutput mergedOutput = split(getInput(), resources, (inp, resource) ->{
            inp.set("resource", resource);
            return execute(inp, DownloadPageTask.class);
        }).merge(mergeStrategy).get();

        execute(mergedOutput, CreateZipTask.class);
        execute(mergedOutput, ProccessAbortedRecordsTask.class);

        return doNotCareOfResult();
    }
        public static void main(String[] arg) {
            ApModuleRunner.localLaunch(Module.class, new DevelopmentConfigurationModule());
        }
}