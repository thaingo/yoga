package org.skyscreamer.yoga.demo.controller;

import org.skyscreamer.yoga.demo.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.ParameterizedType;

/**
 * Created by IntelliJ IDEA.
 * User: Carter Page
 */
public abstract class AbstractController<T>
{
	@Autowired GenericDao _genericDao;
	
	Class<T> _entityClass = returnedClass();

	@RequestMapping("/{id}")
    public T get( @PathVariable long id )
    {
        return _genericDao.find( _entityClass, id);
    }

    // http://blog.xebia.com/2009/02/acessing-generic-types-at-runtime-in-java/
    @SuppressWarnings("unchecked")
	private Class<T> returnedClass()
    {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
