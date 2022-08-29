package cn.chenyunlong.async;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.File;

public class TailerTest {

    public static void main(String[] args) throws Exception {
        TailerTest tailerTest = new TailerTest();
        tailerTest.test();
        boolean flag = true;
        File file = new File("C:/Users/hadoop/Desktop/test/1.txt");

        while (flag) {
            Thread.sleep(1000);
            FileUtils.write(file, "" + System.currentTimeMillis() + IOUtils.LINE_SEPARATOR, true);
        }

    }

    public void test() throws Exception {
        File file = new File("C:/Users/hadoop/Desktop/test/1.txt");
        FileUtils.touch(file);

        Tailer tailer = new Tailer(file, new TailerListenerAdapter() {

            @Override
            public void fileNotFound() {  //文件没有找到  
                System.out.println("文件没有找到");
                super.fileNotFound();
            }

            @Override
            public void fileRotated() {  //文件被外部的输入流改变  
                System.out.println("文件rotated");
                super.fileRotated();
            }

            @Override
            public void handle(String line) { //增加的文件的内容  
                System.out.println("文件line:" + line);
                super.handle(line);
            }

            @Override
            public void handle(Exception ex) {
                ex.printStackTrace();
                super.handle(ex);
            }

        }, 4000, true);
        new Thread(tailer).start();
    }
}  