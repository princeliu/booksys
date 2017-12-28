/**
 * 
 */
package com.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @date 2017年7月1日 下午5:56:43
 */
public class SpringQuartzTest extends QuartzJobBean
{

    /*业务实现*/
    public void work() {
        System.out.println("执行调度任务："+new Date());
    }

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        this.work();
    }
}