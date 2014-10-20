package fr.ippon.portlet.test.unit;

import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * Base class for unit tests
 *
 * @author afillatre@ippon.fr
 * @since 10/10/14
 */
@RunWith(JUnitParamsRunner.class)
@PowerMockIgnore({
        "javax.xml.parsers.*",
        "org.springframework.*",
        "fr.ippon.springmvc.test.unit.mock.*",
        "org.mockito.*",
        "javax.portlet.*"
})
public abstract class AbstractUnitTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();
}
