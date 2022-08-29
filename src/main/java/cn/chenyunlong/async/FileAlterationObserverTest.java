package cn.chenyunlong.async;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.FileFilter;


/**
 * 文件同步
 */
public class FileAlterationObserverTest {

    public static void main(String[] args) throws Exception {

        FileAlterationObserverTest fileAlter = new FileAlterationObserverTest();
        fileAlter.test();
        String filePath = "I:\\log\\";
        File file = new File(filePath);
        if (file.exists()) {
            //        FileUtils.deleteDirectory(file);
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                System.out.println("创建目录成功");
            } else {
                System.out.println("创建目录失败");
            }
        }
        String userNames = """
                    我放弃了你的东西了            
                """;
        //File txtFile = new File(filePath+File.separator+System.currentTimeMillis()+".txt");  
        //FileUtils.touch(txtFile);  
//        File txtFile = new File("C:/Users/hadoop/Desktop/test/1401629335839.txt");  
//        txtFile.deleteOnExit();  
//        FileUtils.touch(txtFile);  

//        File newFile = new File(filePath+File.separator+System.currentTimeMillis());  
//        newFile.mkdirs();  

        boolean flag = true;
        while (flag) {
            /***测试文件的变化代码*/
//            File newFileTxt = new File(filePath+File.separator+System.currentTimeMillis()+".txt");  
//            FileUtils.touch(newFileTxt);  
//            Thread.sleep(2000);  
//            FileUtils.write(newFileTxt,"1",true);  
//            Thread.sleep(2000);  
//            newFileTxt.delete();  


            /****测试文件夹的变化代码***/
            File newFile = new File(filePath + File.separator + System.currentTimeMillis());
            newFile.mkdir();
            Thread.sleep(2000);
            File newFileTxt = new File(newFile.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".txt");
            FileUtils.touch(newFileTxt);
        }
    }

    public void test() throws Exception {
        String filePath = "C:/Users/hadoop/Desktop/test/";
        FileFilter filter = FileFilterUtils.and(new MyFileFilter());
        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(filePath, filter);
        fileAlterationObserver.addListener(new FileAlterationListenerAdaptor() {

            @Override
            public void onStart(FileAlterationObserver observer) {
                System.out.println("start on file");
                super.onStart(observer);
            }

            @Override
            public void onDirectoryDelete(File directory) {
                System.out.println("delete file");
                super.onDirectoryDelete(directory);
            }

            @Override
            public void onDirectoryCreate(File directory) {
                System.out.println("create file");
                super.onDirectoryCreate(directory);
            }

            @Override
            public void onDirectoryChange(File directory) {
                System.out.println("change file");
                super.onDirectoryChange(directory);
            }

            @Override
            public void onFileCreate(File file) {
                System.out.println("file create");
                super.onFileCreate(file);
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("file delete");
                super.onFileDelete(file);
            }

            @Override
            public void onFileChange(File file) {
                System.out.println("file change");
                super.onFileChange(file);
            }
        });
        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(1000);
        fileAlterationMonitor.addObserver(fileAlterationObserver);
        fileAlterationMonitor.start();
    }
}

/*** 
 * 自定义的文件过滤器 
 */
class MyFileFilter implements IOFileFilter {

    @Override
    public boolean accept(File file) {
//        String extension = FilenameUtils.getExtension(file.getAbsolutePath());  
//        if(extension!=null&&extension.equals("txt"))  
//           return true;  
//        return false;  
        return true;
    }

    @Override
    public boolean accept(File dir, String name) {
        //System.out.println("dir:"+dir+"----->"+name);  
        return true;
    }
}  