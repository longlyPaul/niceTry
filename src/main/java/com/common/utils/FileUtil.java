package com.common.utils;

import com.sny7.filetiller.FileTiller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2014/12/5.
 * 文件上传
 */
public class FileUtil {

    public static final String FILE_URL = "/storage";
	/**
	 * 文件上传
	 * @param file
	 * @param type
	 * @return
	 */
	public static String fileUpload(MultipartFile file,String type){
		int categoryId = 0;
		if (type.equals("image")) {
			categoryId = 1;
		} else if (type.equals("audio")) {
			categoryId = 2;
		} else if (type.equals("file")) {
			categoryId = 3;
		}
		if (categoryId != 0 && !file.isEmpty()) {
			InputStream is=null;
			try {
				String filename = file.getOriginalFilename();
				String img_type = filename.substring(filename.lastIndexOf(".")+1,filename.length());
				String filePath;
				if(img_type.equalsIgnoreCase("gif")) {
					filePath = FileTiller.getFilePath(categoryId);
					filePath = filePath.replace("jpg","gif");
					file.transferTo(new File(filePath));
				} else {
					is=file.getInputStream();
					filePath = FILE_URL + String.format(FileTiller.saveFile(categoryId, is), "ori");
				}
				return filePath;
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(is!=null)
				{
					try
					{
						is.close();
					}catch (IOException e)
					{
						e.printStackTrace();
					}

				}
			}
		}
		return null;
	}

    public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            path.replace("/", "\\");
            String path1="/ShareDATA/files"+ path;
            File file = new File(path1);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path1));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            //设置response的中文Header
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(new String(filename.getBytes()), "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
