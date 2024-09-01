package com.lanc.business.exec;

import com.lanc.domain.dos.DemoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import java.util.function.Consumer;

/**
 * DemoListener
 *
 * @description: 领域监听
 * @author: lanjianqing
 * @create: 2022-11-22 03:57
 */
public class DemoListener implements ApplicationListener<DemoEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoListener.class);

    private Consumer<DemoDO> boConsumer;

    public DemoListener(Consumer<DemoDO> boConsumer) {
        this.boConsumer = boConsumer;
    }

    @Override
    public void onApplicationEvent(DemoEvent event) {
        try {
            accept(event);
        } catch (Exception e) {
            LOGGER.info("event异常：【{}】", e.getMessage());
        }
    }

    private void accept(DemoEvent event) {
        DemoDO demoDO = (DemoDO) event.getSource();
        boConsumer.accept(demoDO);
        LOGGER.info("event成功：demoBO【{}】", demoDO);
    }
}
