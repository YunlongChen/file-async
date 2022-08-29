package cn.chenyunlong.async;


import java.nio.file.*;

/**
 * 文件监控
 */
public class FileAsync {

    public static void main(String[] args) throws Exception {
        //第一步：取得WatchService
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //第二步：确定要监控的路径
        Path path = Paths.get("I:\\图片\\");
        //第三步：为本路径绑定WatchService，并确定监控的事件
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        WatchKey key;
        //第四步：当有事件时，开始触发
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("事件" + event.kind() + "发生了，文件是：" + event.context());
            }
            key.reset();
        }
    }

}
