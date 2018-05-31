package com.design.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.design.model.common.Page;
import com.design.model.common.Pagination;

public class BaseServiceImpl<M, P> implements BaseService<M, P> {

	
    @Autowired
    protected SqlSessionFactoryBean sqlSessionFactory;

    @Override
    public Pagination<P> getPagingDatas(P o, int currPage, Integer pageSize, String countMethod, String queryMethod, Class<?> cls) throws Exception {
        Page page = new Page();
        if (currPage == 0) {
            currPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);

        String mapperString = cls.getName();

        String count = mapperString + "." + countMethod;
        String query = mapperString + "." + queryMethod;

        return queryForListByPagination(o, page, count, query);
    }

    /**
     * <h2>重载的分页查询，分页信息采用对象传递</h2>
     *
     * @Title: getPagingDatas
     * @Description: 重载的分页查询，分页信息采用对象传递
     * @param o
     * @param page 封装分页信息（总页数，总记录数，当前页，每页记录数）
     * @param count
     * @param query
     * @return Pagination <P> 返回类型
     * @throws Exception
     */
    @Override
    public Pagination<P> getPagingDatas(P o, Page page, String count, String query) throws Exception {
        if (page.getCurrPage() == 0) {
            page.setCurrPage(1);
        }
        if (page.getPageSize() == 0) {
            page.setPageSize(10);//每页的数量
        }
        return queryForListByPagination(o, page, count, query);
    }

    @Override
    public Pagination<P> queryForListByPagination(P o, Page page, String count, String query) throws Exception {
        Pagination<P> pageHolder = null;
        SqlSession session = null;
        try {
            SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
            if (session == null) {
                session = SqlSessionUtils.getSqlSession(sessionFactory);
            }
            int totalRecords = (Integer) session.selectOne(count, o);
            pageHolder = new Pagination<P>(page, totalRecords);

            List<P> resultList = queryForList(session, o, (pageHolder.getCurrentPage() - 1) * pageHolder.getPageSize(), pageHolder.getPageSize(), query);

            pageHolder.setPageList(resultList);
        } finally {
            session.close();
        }
        return pageHolder;
    }
    
    private List<P> queryForList(SqlSession session, P o, int offSet, int maxRow, String query) {
        return session.selectList(query, o, new RowBounds(offSet, maxRow));
    }
}
