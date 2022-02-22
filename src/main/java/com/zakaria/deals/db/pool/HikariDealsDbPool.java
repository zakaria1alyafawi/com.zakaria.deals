package com.zakaria.deals.db.pool;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

/**
 * @author Zakaria Alyafawi
 * @version 1.0
 * @since 2022-2-18
 */
public class HikariDealsDbPool {

	private static String FILE_DB_CONFIG = "./config/db/dbconfig.properties";

	private HikariConfig config;
	private HikariDataSource dataSource;
	private MBeanServer mbeanServer;
	private ObjectName poolName;
	private HikariPoolMXBean poolProxy;
	private DbPoolInfo dbPoolInfo;

	private static volatile HikariDealsDbPool hikariConnection;
	private static volatile boolean isConnected;

	private HikariDealsDbPool() {
		if (hikariConnection != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public static HikariDealsDbPool getInstance() {
		if (!isConnected) { // if there is no instance available... create new one
			synchronized (HikariDealsDbPool.class) {
				if (!isConnected) {
					hikariConnection = new HikariDealsDbPool();
					hikariConnection.init();
					hikariConnection.dbPoolInfo = hikariConnection.new DbPoolInfo();
					isConnected = true;
				}
			}
		}

		return hikariConnection;
	}

	private void init() {
		this.initConfigDev();
		this.initDataSource();
		this.initMXBeanServer();
	}

	private void initConfigDev() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
		Properties props = new Properties();
		try {
			props.load(in);
			System.out.println("load db props success");
			this.config = new HikariConfig(props);
			this.config.setPoolName("DEALS pool");
			this.config.setMaximumPoolSize(25);
			this.config.setRegisterMbeans(true);
			System.out.println("load db config success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initDataSource() {
		while (this.dataSource == null || this.isDataSourceClosed()) {
			try {
				this.dataSource = new HikariDataSource(this.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isDataSourceClosed() {
		try {
			return this.dataSource.isClosed();
		} catch (Exception e) {
			return false;
		}
	}

	private void initMXBeanServer() {
		while (this.poolProxy == null) {
			try {
				this.mbeanServer = ManagementFactory.getPlatformMBeanServer();
				this.poolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + this.dataSource.getPoolName() + ")");
				this.poolProxy = JMX.newMXBeanProxy(mbeanServer, poolName, HikariPoolMXBean.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		Connection connection = null;

		checkDatasource();
		while (connection == null) {
			try {
				connection = this.dataSource.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	private void checkDatasource() {
		if (this.isDataSourceClosed()) {
			synchronized (this) {
				try {
					if (this.isDataSourceClosed()) {
						this.init();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getActiveConnections() {
		return this.poolProxy.getActiveConnections();
	}

	public int getIdleConnections() {
		return this.poolProxy.getIdleConnections();
	}

	public int getThreadsAwaitingConnection() {
		return this.poolProxy.getThreadsAwaitingConnection();
	}

	public int getTotalConnections() {
		return this.poolProxy.getTotalConnections();
	}

	public static boolean isConnectionValid() {
		String jdbcUrl = HikariDealsDbPool.getInstance().getDataSource().getJdbcUrl();
		String user = HikariDealsDbPool.getInstance().getDataSource().getDataSourceProperties().get("user").toString();
		String password = HikariDealsDbPool.getInstance().getDataSource().getDataSourceProperties().get("password")
				.toString();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(jdbcUrl, user, password);
			con.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Make singleton from serialize and deserialize operation.
	protected HikariDealsDbPool readResolve() {
		return getInstance();
	}

	@Override
	protected void finalize() throws Throwable {
		this.dataSource.close();
	}

	public HikariDataSource getDataSource() {
		this.checkDatasource();
		return this.dataSource;
	}

	public class DbPoolInfo implements Serializable {
		private static final long serialVersionUID = -5902531019043290448L;

		private DbPoolInfo() {

		}

		public int getActiveConnections() {
			return HikariDealsDbPool.this.poolProxy.getActiveConnections();
		}

		public int getIdleConnections() {
			return HikariDealsDbPool.this.poolProxy.getIdleConnections();
		}

		public int getThreadsAwaitingConnection() {
			return HikariDealsDbPool.this.poolProxy.getThreadsAwaitingConnection();
		}

		public int getTotalConnections() {
			return HikariDealsDbPool.this.poolProxy.getTotalConnections();
		}
	}

	public DbPoolInfo getDbPoolInfo() {
		return dbPoolInfo;
	}
}
