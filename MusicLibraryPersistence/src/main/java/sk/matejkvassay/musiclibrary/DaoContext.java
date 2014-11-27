package sk.matejkvassay.musiclibrary;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Marián Macik
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class DaoContext {
	
	@Bean 
	public JpaTransactionManager transactionManager(){
		return  new JpaTransactionManager(jpaFactoryBean().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean  jpaFactoryBean(){
		LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
		jpaFactoryBean.setDataSource(db());
		jpaFactoryBean.setLoadTimeWeaver(instrumentationLoadTimeWeaver());
		jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
                jpaFactoryBean.setJpaProperties(additionalProperties());
                jpaFactoryBean.setPackagesToScan(new String[] {"sk.matejkvassay.musiclibrary"});
		return jpaFactoryBean;
	}
	
	@Bean
	public LoadTimeWeaver instrumentationLoadTimeWeaver() {
		return new InstrumentationLoadTimeWeaver();
	}

	@Bean
	public DataSource db(){
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
                
                DriverManagerDataSource db = new DriverManagerDataSource();
      db.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
      db.setUrl("jdbc:derby://localhost:1527/pa165");
      db.setUsername("pa165");
      db.setPassword("pa165");
		return db;
	}
        
        @Bean
        public PersistenceExceptionTranslationPostProcessor persistenceTranslatorBean(){
            return new PersistenceExceptionTranslationPostProcessor();
        }
        
        Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
      properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
      properties.setProperty("hibernate.show_sql", "true");
      properties.setProperty("hibernate.format_sql", "true");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
      return properties;
   }
}
