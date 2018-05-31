package com.design.service;

import com.design.model.common.Page;
import com.design.model.common.Pagination;

public interface BaseService<M, P> {
    public Pagination<P> getPagingDatas(P o, int currPage, Integer pageSize, String query, String count, Class<?> cls) throws Exception;

    /**
     * <h2>重载的分页查询，分页信息采用对象传递</h2>
     *
     * @Title: getPagingDatas
     * @Description: 重载的分页查询，分页信息采用对象传递
     * @param @param o
     * @param @param page 封装分页信息（总页数，总记录数，当前页，每页记录数）
     * @param @param count
     * @param @param query
     * @param @throws Exception
     * @return PaginationBean <P> 返回类型
     * @throws Exception
     */
    public Pagination<P> getPagingDatas(P o, Page page, String count, String query) throws Exception;

    public Pagination<P> queryForListByPagination(P o, Page page, String count, String query) throws Exception;
}