package org.example.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.annotation.Input;
import eu.ibagroup.easyrpa.engine.annotation.OnError;
import eu.ibagroup.easyrpa.engine.annotation.Output;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.example.entity.ResourceDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ApTaskEntry(name = "Download url content to file")
@Slf4j
public class GetResourceContentTask extends ApTask {
    @Output("resources")
    private List<ResourceDto> resources;

    @Input("input_file")
    private String jsonFileName ;


    @Override
    public void execute() throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(jsonFileName);
        String json = IOUtils.toString(is, Charsets.UTF_8);

        resources = new Gson().fromJson(json, new TypeToken<List<ResourceDto>>() {}.getType());
    }

    @OnError
    public void onError(Throwable throwable) {
        log.info("@@@@@@@@@@@@ on ERROR: {}, cause: {}" + throwable.getMessage(), throwable.getCause());
    }
}