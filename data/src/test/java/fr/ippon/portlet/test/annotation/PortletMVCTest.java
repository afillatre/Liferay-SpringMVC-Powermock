package fr.ippon.portlet.test.annotation;

import fr.ippon.springmvc.test.unit.mock.MockWebApplication;
import fr.ippon.springmvc.test.unit.mock.MockWebApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Handy annotation to prevent me to declare the {@link fr.ippon.springmvc.test.unit.mock.MockWebApplication} and
 * {@link org.springframework.test.context.ContextConfiguration} annotation on each test.<br/>
 *
 * <b>Warning :</b> Works only with Spring 4x ! On older version, you cannot use meta-annotations like this
 *
 * @author afillatre@ippon.fr
 * @since 20/10/14
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

@MockWebApplication(name="portlet-with-tests")
@ContextConfiguration(loader = MockWebApplicationContextLoader.class)
public @interface PortletMVCTest {
}
