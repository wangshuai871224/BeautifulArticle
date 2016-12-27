package com.example.dllo.hodgepodge.tools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.dllo.hodgepodge.pictorial.CollectBean;
import com.example.dllo.hodgepodge.pictorial.PictorialBean;
import com.example.dllo.hodgepodge.pictorial.PictorialItemBean;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by shuaiwang on 16/12/26.
 * 懒汉式单例
 */

public class LiteOrmTool {

    private static LiteOrmTool mLiteOrmTool;
    private LiteOrm mLiteOrm;// 数据库对象
    private Handler mHandler;// 切换线程
    private Executor mThreadPool;// 线程池

    private LiteOrmTool() {
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getContext(), "Hodgepodge.db");
        // 保证传送到主线程进行UI刷新
        mHandler = new Handler(Looper.getMainLooper());
        // cpu个数
        int coreNum = Runtime.getRuntime().availableProcessors();
        // 最大线程= 核心线程   任务队列无限大
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    // 获取本类对象
    public static LiteOrmTool getmLiteOrm() {
        if (mLiteOrmTool == null) {
            synchronized (LiteOrmTool.class) {
                if (mLiteOrmTool == null) {
                    mLiteOrmTool = new LiteOrmTool();
                }
            }
        }
        return mLiteOrmTool;
    }

    // 根据字段删除
    public void deleteByTitle(CollectBean bean) {
        mLiteOrm.delete(new WhereBuilder(CollectBean.class).where("title = ?", new Object[]{bean.getTitle()}));
    }

    // 插入
    public void insertCollectBean(final CollectBean bean) {

        if (!isSave(bean)) {
            mLiteOrm.insert(bean);
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mLiteOrm.insert(bean);
//            }
//        });
    }

    // 插入集合
    public void insertItemList(final List<CollectBean> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.save(list);
            }
        });
    }

    // 查询所有
    public List<CollectBean> queryAll(Class<CollectBean> collectBeanClass) {

        return mLiteOrm.query(CollectBean.class);
    }

    // 根据某一字段查询
    public boolean isSave(CollectBean bean) {
        QueryBuilder<CollectBean> queryBuilder = new QueryBuilder<>(CollectBean.class)
                .columns(new String[]{"title"})
                .where("title = ?", new Object[]{bean.getTitle()});
        return mLiteOrm.query(queryBuilder).size() > 0 ? true : false;
    }

    // 根据某一字段查询
    public boolean isSave(String title) {
        QueryBuilder<CollectBean> queryBuilder = new QueryBuilder<>(CollectBean.class)
                .columns(new String[]{"title"})
                .where("title = ?", new Object[]{title});
        return mLiteOrm.query(queryBuilder).size() > 0 ? true : false;
    }









    // 插入
    public <T> void insertBean(final T bean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.save(bean);
            }
        });
    }

    // 插入集合
    public <T> void insertList(final List<T> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.save(list);
            }
        });
    }

    // 查询所有
    public <T> List<T> getQueryAll(Class<T> bean) {
        return mLiteOrm.query(bean);
    }

    // 查询某一字段
    public <T> List<T> querySomeBody(Class<T> bean, String id, Object[] value) {

        return mLiteOrm.<T>query(new QueryBuilder(bean).where(id + "=?", value));
    }


    // 删除一个表
    public <T> void deleteTable(Class<T> mClass) {
        mLiteOrm.delete(mClass);
    }

    // 删除
    public <T> void delete(T t) {
        mLiteOrm.delete(t);
    }

    // 删除数据库
    public <T> void deleteDatabase() {
        mLiteOrm.deleteDatabase();
    }


    // 内部接口
    public interface QueryListener {
        <T> void onQuery(List<T> bean);
    }


    // 查询
    public void queryBean(QueryListener queryListener) {
        mThreadPool.execute(new QueryRunnable(queryListener));
    }

    class QueryRunnable<T> implements Runnable {

        private QueryListener mQueryListener;

        public QueryRunnable(QueryListener queryListener) {
            mQueryListener = queryListener;
        }

        @Override
        public void run() {
//            List<T> list = mLiteOrm.<T>query(T.class);
            // post发送, 发送到主线程中, runnable内的所有方法都在主线程中运行
            // 通过接口把值传出去
//            mHandler.post(new CallBackRunnable(mQueryListener, list));
        }
    }

    private class CallBackRunnable implements Runnable {
        private QueryListener mQueryListener;
        //TODO 定义集合

        //                                                    应该是集合
        public CallBackRunnable(QueryListener queryListener, Object p1) {
            this.mQueryListener = queryListener;
        }

        @Override
        public void run() {
//            mQueryListener.onQuery();// 参数集合
        }
    }
}
