package cn.su.service.imp;

import cn.su.dao.ConsumlogMapper;
import cn.su.pojo.ConsumParam;
import cn.su.pojo.Consumlog;
import cn.su.pojo.PageBean;
import cn.su.service.ConsumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("consumService")
public class ConsumServiceImp implements ConsumService {
    @Resource
    private ConsumlogMapper consumlogMapper;
    @Override
    public PageBean<Consumlog> findByPage(ConsumParam consumParam){

        PageBean<Consumlog> pageBean = new PageBean<Consumlog>();


        //封装总记录数
        int totalCount = consumlogMapper.selectCount(consumParam);
        pageBean.setTotalCount(totalCount);

        //每页显示的数据
        int pageSize=10;

        if(pageSize>totalCount){
            pageBean.setPageSize(totalCount);
            pageSize=totalCount;
        }else{
            pageBean.setPageSize(pageSize);
        }

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        //处理当前页溢出的情况
        if((consumParam.getCurrPage()-1)<=0){
            pageBean.setPreviouspage(1);
            consumParam.setCurrPage(1);
        }else{
            pageBean.setPreviouspage(consumParam.getCurrPage()-1);
        }

        if ((consumParam.getCurrPage()+1)>pageBean.getTotalPage()){
            pageBean.setNextpage(pageBean.getTotalPage());
            consumParam.setCurrPage(pageBean.getTotalPage());
        }else{
            pageBean.setNextpage(consumParam.getCurrPage()+1);
        }

        //封装当前页数
        int currentPage = consumParam.getCurrPage();
        pageBean.setCurrPage(currentPage);


        consumParam.setStart((currentPage-1)*pageSize);
        consumParam.setSize(pageBean.getPageSize());

        //封装每页显示的数据
        List<Consumlog> lists = consumlogMapper.findConsumlogByPage(consumParam);
        pageBean.setLists(lists);

        pageBean.setConsumParam(consumParam);

        return pageBean;

    }


}
