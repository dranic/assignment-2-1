package org.example.tasks;

import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import lombok.extern.slf4j.Slf4j;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

@ApTaskEntry(name = "Create Zip Task")
@Slf4j
public class CreateZipTask extends ApTask {


    @Override
    public void execute() throws Exception {
        ZipUtil.pack(new File("temp"), new File("temp.zip"));
    }
}