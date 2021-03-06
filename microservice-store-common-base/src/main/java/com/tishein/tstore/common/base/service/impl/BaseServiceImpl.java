package com.tishein.tstore.common.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.tishein.tstore.common.base.domain.BaseDomain;
import com.tishein.tstore.common.base.manager.BaseManager;
import com.tishein.tstore.common.base.service.BaseService;
import com.tishein.tstore.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description 基础业务逻辑处理层实现
 * @Author Stishein
 * @Date 2018/8/15 0015 17:57
 */
@Slf4j
public class BaseServiceImpl<T extends BaseDomain, ID> implements BaseService<T, ID> {

    @Autowired
    protected BaseManager<T, ID> manager;


    /**
     * @return java.util.List<com.tishein.tstore.domain.BaseDomain>
     * @Author
     * @Description Stishein
     * @Date 17:45 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<List<T>> list(T domain) {

        return Result.success(manager.list(domain));
    }

    /**
     * @return com.github.pagehelper.PageInfo<T>
     * @Author Stishein
     * @Description 通过参数条件分页查询数据，返回列表
     * @Date 9:57 2018/8/16 0016
     * @Param [domain, pageNum, pageSize]
     **/
    @Override
    public Result<PageInfo<T>> list(T domain,
            @PathVariable("pageNum")    Integer pageNum,
            @PathVariable("pageSize")   Integer pageSize
    ) {

        PageInfo<T> pageInfo = manager.list(domain, pageNum, pageSize);

        return Result.success(pageInfo);
    }

    /**
     * @return com.tishein.tstore.domain.BaseDomain
     * @Author Stishein
     * @Description 通过id查询数据，返回一个结果
     * @Date 17:26 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<T> get(@PathVariable("id") ID id) {

        return Result.success(manager.get(id));
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 插入数据
     * @Date 17:12 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<T> insert(@RequestBody T domain) {

        domain.setCreateDate(new Date());
        manager.insert(domain);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 更新数据
     * @Date 17:15 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<T> update(@RequestBody T domain) {

        domain.setModifyDate(new Date());
        manager.update(domain);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 逻辑删除数据
     * @Date 17:25 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<T> delete(@PathVariable("id") ID id) {

        manager.delete(id);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 逻辑删除数据
     * @Date 17:25 2018/8/15 0015
     * @Param [domain]
     **/
    @Override
    public Result<T> deleteLogic(@PathVariable("id") ID id) {

        manager.deleteLogic(id);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 批量插入数据
     * @Date 17:32 2018/8/15 0015
     * @Param [list]
     **/
    @Override
    public Result<T> insertBatch(@RequestBody List<T> domains) {

        Date now = new Date();
        domains.forEach((domain) -> domain.setCreateDate(now));
        manager.insertBatch(domains);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 批量更新数据
     * @Date 17:33 2018/8/15 0015
     * @Param [domains]
     **/
    @Override
    public Result<T> updateBatch(@RequestBody List<T> domains) {

        Date now = new Date();
        domains.forEach((domain) -> domain.setModifyDate(now));
        manager.updateBatch(domains);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 批量逻辑删除数据，返回数据列表
     * @Date 17:42 2018/8/15 0015
     * @Param [domains]
     **/
    @Override
    public Result<T> deleteBatch(@RequestBody List<T> domains) {

        manager.deleteBatch(domains);

        return Result.success();
    }

    /**
     * @return int
     * @Author Stishein
     * @Description 批量逻辑删除数据，返回数据列表
     * @Date 17:42 2018/8/15 0015
     * @Param [domains]
     **/
    @Override
    public Result<T> deleteLogicBatch(@RequestBody List<T> domains) {

        manager.deleteLogicBatch(domains);

        return Result.success();
    }
}
