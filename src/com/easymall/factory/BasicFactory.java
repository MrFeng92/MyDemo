package com.easymall.factory;

import com.easymall.annotation.Trans;
import com.easymall.dao.Dao;
import com.easymall.service.Service;
import com.easymall.utils.PropUtils;
import com.easymall.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BasicFactory
{
    private static BasicFactory factory = new BasicFactory();

    private BasicFactory()
    {
    }

    public static BasicFactory getFactory()
    {
        return factory;
    }

    /**
     * 读取配置文件获取配置的具体的实现类的全路径名 生成该类的对象返回
     * @return
     */
    @SuppressWarnings(
            {"unchecked", "rawtypes"})
    public <T> T getInstance(Class<T> infC)
    {
        try
        {
            if (Service.class.isAssignableFrom(infC))
            {
                // 判断当前获取的是否是service
                // 生成service的动态代理返回
                String clzName = PropUtils.getProp(infC.getSimpleName());
                Class clz = Class.forName(clzName);

                final T t = (T) clz.newInstance();// 匿名内部类调用外部参数，参数必须是final修饰的
                T proxy = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler()
                {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {

                        if (method.isAnnotationPresent(Trans.class))
                        {
                            try
                            {

                                // 判断当前方法是否有 @Trans注解，表示这个方法需要事务管理
                                // 记录日志
                                // 细粒度控制权限
                                // 控制事务
                                TransactionManager.startTransaction();// 开启事务
                                Object returnObj = method.invoke(t, args);// ?
                                TransactionManager.submitTransaction();// 提交事务
                                return returnObj;
                            } catch (InvocationTargetException e)
                            {
                                TransactionManager.rollbackTransaction();// 回滚事务
                                e.getTargetException().printStackTrace();
                                throw e.getTargetException();
                            } catch (Exception e)
                            {
                                TransactionManager.rollbackTransaction();// 回滚事务
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            } finally
                            {
                                TransactionManager.release();
                            }
                        } else
                        {
                            // 当前方法不需要事务处理
                            try
                            {
                                return method.invoke(t, args);
                            } catch (InvocationTargetException e)
                            {
                                e.getTargetException().printStackTrace();
                                throw e.getTargetException();
                            } catch (Exception e)
                            {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                return proxy;
            } else if (Dao.class.isAssignableFrom(infC))
            {
                // --当前获取的是Dao
                String clzName = PropUtils.getProp(infC.getSimpleName());
                Class clz = Class.forName(clzName);
                return (T) clz.newInstance();
            } else
            {
                // --当前获取的既不是Service，也不是Dao
                throw new RuntimeException("别乱来。。只能获取Service或者Dao。。。");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
