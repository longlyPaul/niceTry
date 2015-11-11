package thinking;

import com.common.utils.FileUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by liusonglin on 15/11/8.
 */
public class IOtest {
    public static void main(String[] args) throws IOException {
        File file1=new File(System.getProperty("user.dir"));
        File file2=new File(System.getProperty("user.dir"));
        FileUtils.copyFile(file1,file2);
        FileInputStream fis= new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        FileChannel f1=fis.getChannel();
        FileChannel f2 = fos.getChannel();
        Long size = f1.size();
        Long pos = 0L;

        for(long count = 0L; pos < size; pos += f2.transferFrom(f1, pos, count)) {
            count = size - pos > 31457280L?31457280L:size - pos;
        }

    }
}
