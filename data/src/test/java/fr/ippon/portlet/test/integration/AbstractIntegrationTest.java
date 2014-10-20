package fr.ippon.portlet.test.integration;

import com.liferay.portal.util.InitUtil;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * Base class for integration tests
 *
 * @author afillatre@ippon.fr
 * @since 10/10/14
 */
@RunWith(JUnitParamsRunner.class)
@PowerMockIgnore({
        "com.liferay.portal.service.*"
})
public abstract class AbstractIntegrationTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    @BeforeClass
    public static void setUp() {
        InitUtil.initWithSpring();
    }
}
