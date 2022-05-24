package org.example.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.annotation.Input;
import eu.ibagroup.easyrpa.engine.annotation.Output;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.example.entity.ResourceDto;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@ApTaskEntry(name = "Parsing input.json file")
@Slf4j
public class DownloadPageTask extends ApTask {
    @Input("resource")
    @Output("resource")
    private ResourceDto resource;

    @Override
    public void execute() throws Exception {
        File html = new File(resource.getName() + ".html");
        FileUtils.copyURLToFile(new URL(resource.getUrl()), html);
        resource.setFilePath(html.getPath());
    }
}