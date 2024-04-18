package io.github.thinkframework.data.jdbc.repository.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactoryBean;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

public class ThinkJdbcRepositoryConfigExtension extends RepositoryConfigurationExtensionSupport {

    private static final String DEFAULT_TRANSACTION_MANAGER_BEAN_NAME = "transactionManager";

    public ThinkJdbcRepositoryConfigExtension() {
    }

    public String getModuleName() {
        return "JDBC";
    }

    public String getRepositoryFactoryBeanClassName() {
        return JdbcRepositoryFactoryBean.class.getName();
    }

    protected String getModulePrefix() {
        return this.getModuleName().toLowerCase(Locale.US);
    }

    public void postProcess(BeanDefinitionBuilder builder, RepositoryConfigurationSource source) {
        source.getAttribute("jdbcOperationsRef").filter(StringUtils::hasText).ifPresent((s) -> {
            builder.addPropertyReference("jdbcOperations", s);
        });
        source.getAttribute("dataAccessStrategyRef").filter(StringUtils::hasText).ifPresent((s) -> {
            builder.addPropertyReference("dataAccessStrategy", s);
        });
        Optional<String> transactionManagerRef = source.getAttribute("transactionManagerRef");
        builder.addPropertyValue("transactionManager", transactionManagerRef.orElse("transactionManager"));
    }

    protected Collection<Class<? extends Annotation>> getIdentifyingAnnotations() {
        return Collections.singleton(Table.class);
    }
}
