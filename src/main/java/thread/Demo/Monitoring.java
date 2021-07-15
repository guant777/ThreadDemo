package thread.Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author GuanTao
 * @program: ThreadDemo
 * @description: 线程监控
 * @create: 2021-07-09 16:14
 **/
@Slf4j
public class Monitoring {

    public static void main(String[] args) {

        monitoringMethodByThread();


    }

    /**
     * 某个方法超时监控
     *
     * @return void
     */
    public static void monitoringMethodByThread() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<>(() -> {
            //执行方法
            dealWithThings();
            return "执行完成";
        });
        try {
            executor.execute(future);
            String s = future.get(10, TimeUnit.MILLISECONDS);
            System.out.println("任务成功返回:" + s);
        } catch (Exception e) {
            log.error("监控方法超时，超过{}ms！", 30);

        } finally {
            future.cancel(true);
            executor.shutdown();
            if (future.isCancelled()) {
                log.info("future取消任务成功");
            }
            if (executor.isShutdown()) {
                log.info("executor取消任务成功");
            }
        }
    }

    /**
     * 测试任务
     */
    private static void dealWithThings() {
        long l = System.currentTimeMillis();
        IntStream.range(1, 0)
                .limit(10000)
                .forEach(item -> item++);
        log.info("方法耗时:{}", (System.currentTimeMillis() - l));
    }
}
