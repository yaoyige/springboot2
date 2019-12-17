package com.yyg.task.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MasterTask implements SimpleJob {
   /* @Scheduled(cron="0/5 * *  * * ? ")*/
    @Override
    public void execute(ShardingContext shardingContext){
             System.out.print("这是测试定时任务的哈@！！！！");
    }
}
