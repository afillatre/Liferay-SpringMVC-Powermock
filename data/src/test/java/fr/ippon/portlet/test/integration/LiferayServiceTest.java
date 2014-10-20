package fr.ippon.portlet.test.integration;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Dummy test for Liferay services
 *
 * @author afillatre@ippon.fr
 * @since 10/10/14
 */
public class LiferayServiceTest extends AbstractIntegrationTest {

    @Test
    public void testGetAllUsers() throws SystemException {
        assertThat(UserLocalServiceUtil.getUsers(0, 10)).isNotEmpty();
    }
}
