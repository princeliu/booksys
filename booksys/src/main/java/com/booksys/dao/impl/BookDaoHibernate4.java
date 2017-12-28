package com.booksys.dao.impl;

import com.booksys.dao.BookDao;
import com.booksys.domain.Book;
import com.common.dao.impl.BaseDaoHibernate4;
import org.springframework.stereotype.Service;

/**
 * @date 2017年6月30日 上午8:51:44
 */
@Service(value="bookDao")
public class BookDaoHibernate4 extends BaseDaoHibernate4<Book>
	implements BookDao
{
}
