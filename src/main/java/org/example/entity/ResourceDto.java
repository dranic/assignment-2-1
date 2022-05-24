package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class ResourceDto {
    private int id;
    private String url;
    private String name;
    private String filePath;

}
