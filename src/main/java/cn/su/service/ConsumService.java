package cn.su.service;

import cn.su.pojo.ConsumParam;
import cn.su.pojo.Consumlog;
import cn.su.pojo.PageBean;

public interface ConsumService {
    PageBean<Consumlog> findByPage(ConsumParam consumParam);
}
