package pl.jakpoliczyc.dao

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import pl.jakpoliczyc.config.Profiles
import pl.jakpoliczyc.config.db.DbConfig
import spock.lang.Specification

@ActiveProfiles(Profiles.TEST)
@ContextConfiguration(classes = [DbConfig.class])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class])
@DatabaseSetup(value = "/fake.xml", type = DatabaseOperation.CLEAN_INSERT)
class JakPoliczycDbSpec extends Specification {

}