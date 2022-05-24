package tastks;

import eu.ibagroup.easyrpa.engine.annotation.OnError;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadReport {

    @Test
    public void url2File() throws IOException {
        File html = new File( "amazon.html");
//        String siteContent = new URL("https://amazon.com/").t
        FileUtils.copyURLToFile(new URL("https://amazon.com/"), html);
    }


}
