package fr.ippon.portlet.test.unit;

import fr.ippon.springmvc.test.unit.mock.MockViewResolver;
import fr.ippon.springmvc.test.unit.mock.MockWebApplication;
import fr.ippon.springmvc.test.unit.mock.MockWebApplicationContextLoader;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.web.portlet.DispatcherPortlet;

/**
 * Allows portlet MVC testing
 *
 * @author afillatre@ippon.fr
 * @since 15/10/14
 */
@MockWebApplication(name="portlet-with-tests")
@ContextConfiguration(loader = MockWebApplicationContextLoader.class)
public class AbstractControllerUnitTest extends AbstractUnitTest {

    @Autowired
    protected DispatcherPortlet dispatcherPortlet;
    @Autowired
    protected MockViewResolver viewResolver;


    @Before
    public void setUp() throws Exception {
        new TestContextManager(getClass()).prepareTestInstance(this);
    }
}
