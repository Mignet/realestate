package com.szhome.cq.sqlfileexport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PluginInstallTest {

  public void print(String path) {
    List list = getFileList(path);
    if (list == null) {
      return;
    }

    int length = list.size();
    for (int i = 0; i < length; i++) {
      String result = "";
      String thePath = getFormatPath(getString(list.get(i)));
      File file = new File(thePath);
      if (file.isDirectory()) {
        String fileName = file.getName();
        if (fileName.indexOf("_") < 0) {
          print(thePath);
          continue;
        }
        String[] filenames = fileName.split("_");
        String filename1 = filenames[0];
        String filename2 = filenames[1];
        result = filename1 + "," + filename2 + ",file:/" + path + "\\"
            + fileName + "\\,4,false";
        System.out.println(result);
      }
      else if (file.isFile()) {
        String fileName = file.getName();
        if (fileName.indexOf("_") < 0) {
          continue;
        }
        int last = fileName.lastIndexOf("_");// 最后一个下划线的位置
        String filename1 = fileName.substring(0, last);
        String filename2 = fileName.substring(last + 1, fileName.length() - 4);
        result = filename1 + "," + filename2 + ",file:/" + path + "\\"
            + fileName + ",4,false";
        System.out.println(result);
      }

    }
  }

  public List getFileList(String path) {
    path = getFormatPath(path);
    path = path + "/";
    File filePath = new File(path);
    if (!filePath.isDirectory()) {
      return null;
    }
    String[] filelist = filePath.list();
    List filelistFilter = new ArrayList();

    for (int i = 0; i < filelist.length; i++) {
      String tempfilename = getFormatPath(path + filelist[i]);
      filelistFilter.add(tempfilename);
    }
    return filelistFilter;
  }

  public String getString(Object object) {
    if (object == null) {
      return "";
    }
    return String.valueOf(object);
  }

  public String getFormatPath(String path) {
    path = path.replaceAll("\\\\", "/");
    path = path.replaceAll("//", "/");
    return path;
  }

  public static void main(String[] args) {
    // 插件文件所在目录designer下的目录结构是eclipse/features and plugins的形式
    String plugin = "D:\\ec3.2_plugin\\subclipse1.4.8";
    new PluginInstallTest().print(plugin);
  }
}
