package org.ni.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * @since 2024/9/6 11:06
 **/
public class HbaseClientUsage {
    public static void main(String[] args) throws IOException {
        HbaseClientUsage hbaseClientUsage = new HbaseClientUsage();

        Connection connection = hbaseClientUsage.createConnection(
                "node23.test.com:2181",
                "/hbase",
                "hbase/_HOST@TEST.COM",
                "idp",
                "/opt/idp.keytab");
        Admin admin = connection.getAdmin();
        //admin.createTable(
        //        new HTableDescriptor(TableName.valueOf("default", "table41"))
        //                .addFamily(new HColumnDescriptor("cf"))
        //);
        // 判断表是否存在的方法会一直不返回
        System.out.println(connection.getAdmin().tableExists(TableName.valueOf("default", "table4")));
    }

    public Connection createConnection(String zkAddress, String zkParentPath, String hbaseServicePrincipal, String userPrincipal, String keytabPath) {
        final Configuration conf = HBaseConfiguration.create();
        Connection connection = null;
        conf.set("hbase.zookeeper.quorum", zkAddress);
        conf.set("zookeeper.znode.parent", zkParentPath);

        try {
            conf.set("hadoop.security.authentication", "kerberos");
            conf.set("hbase.security.authentication", "kerberos");
            conf.set("hbase.master.kerberos.principal", hbaseServicePrincipal);
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab(
                    userPrincipal,
                    keytabPath
            );
            HBaseAdmin.available(conf);
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            System.out.println(e);
        }
        return connection;
    }
}