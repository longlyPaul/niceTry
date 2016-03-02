package thinking;

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
        File file1=new File("C:\\Users\\VI-H23\\Desktop\\项目文档\\外汇\\(CFETShelpdesk)系统DB设计书-oracle-1.0.1.xls");
        File file2=new File("C:\\Users\\VI-H23\\Desktop\\text.xls");
        FileUtils.copyFile(file1,file2);
        FileInputStream fis= new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        FileChannel f1=fis.getChannel();
        FileChannel f2 = fos.getChannel();
        Long size = f1.size();
        Long pos = 0L;
        long count;
        while (pos < size) {
            count = size - pos > 31457280L ? 31457280L : size - pos;
            pos += f2.transferFrom(f1, pos, count);
        }
    }
}
