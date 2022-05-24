package tastks;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.example.entity.ResourceDto;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReadInput {

    @Test
    public void read() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input.json");
        String json = IOUtils.toString(is, Charsets.UTF_8);
        List<ResourceDto> resources = new Gson().fromJson(json, new TypeToken<List<ResourceDto>>(){}.getType());
    }
}
