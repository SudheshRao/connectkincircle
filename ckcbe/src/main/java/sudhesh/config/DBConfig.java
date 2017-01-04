package sudhesh.config;

import java.util.Properties;


import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sudhesh.model.Connects;
import sudhesh.model.Job;
import sudhesh.model.JobApplied;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;
import sudhesh.model.UploadFile;





@Configuration
@ComponentScan("sudhesh")
@EnableTransactionManagement
public class DBConfig {

	@Bean(name="dataSource")
	public DataSource getOracleDataSource()
	{
	DriverManagerDataSource datasource=new DriverManagerDataSource();
	datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	
    datasource.setUsername("SUDHESH");
    datasource.setPassword("sudhesh");
    
   Properties connectionProperties = new Properties();
    connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
    connectionProperties.setProperty("hibernate.show_sql", "true");
    connectionProperties.setProperty("hibernte.dialect", "org.hibernate.dialect.Oracle10gDialect");
    connectionProperties.setProperty("hiberanate.formate_sql", "true");
    connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
    //connectionProperties.setProperty("hibernate.default_schema", "system"); 		
    datasource.setConnectionProperties(connectionProperties);
    
    return datasource;
    
    }

	private Properties getHibernateProperties(){
		Properties properties=new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
	
	
   @Autowired
   @Bean(name="sessionfactory")
   public SessionFactory getSessionFactory(DataSource datasouce){
	   LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(getOracleDataSource());
	
   sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClass(Kin.class);
	sessionBuilder.addAnnotatedClass(Job.class);
	sessionBuilder.addAnnotatedClass(UploadFile.class);
	sessionBuilder.addAnnotatedClass(JobApplied.class);
	sessionBuilder.addAnnotatedClass(Connects.class);
	sessionBuilder.addAnnotatedClass(Notifications.class);
			
	return sessionBuilder.buildSessionFactory();
	
	}
   @Autowired
   @Bean(name="transactionManager")
   public HibernateTransactionManager getTransactionManager(
   		SessionFactory sessionfactory){
   	
   		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionfactory);
   		return transactionManager;
   	
	
   }   
}


