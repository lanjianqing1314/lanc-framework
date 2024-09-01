package com.lanc.business.exec;

import org.springframework.context.ApplicationEvent;

/**
 * DemoEvent
 *
 * @description: 领域事件
 * @author: lanjianqing
 * @create: 2022-11-22 03:55
 */
public class DemoEvent extends ApplicationEvent {
    public DemoEvent(Object source) {
        super(source);
    }
}
