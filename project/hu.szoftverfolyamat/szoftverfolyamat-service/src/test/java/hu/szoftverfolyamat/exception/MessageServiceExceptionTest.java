package hu.szoftverfolyamat.exception;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MessageServiceExceptionTest {

    @Test
    public void testInstantiateWithCause() {
        // GIVEN
        final Throwable expected = Mockito.mock(Throwable.class);
        final MessageServiceException testSubject = new MessageServiceException(expected);

        // WHEN
        final Throwable cause = testSubject.getCause();

        // THEN
        Assert.assertSame(expected, cause);
    }

    @Test
    public void testInstantiateWithMessage() {
        // GIVEN
        final MessageServiceException testSubject = new MessageServiceException("test");
        final String expected = "test";

        // WHEN
        final String message = testSubject.getMessage();

        // THEN
        Assert.assertEquals(expected, message);
    }

    @Test
    public void testInstantiateWithCauseAndMessage() {
        // GIVEN
        final Throwable expectedCause = Mockito.mock(Throwable.class);
        final String expectedMessage = "test";
        final MessageServiceException testSubject = new MessageServiceException(expectedMessage, expectedCause);

        // WHEN
        final Throwable cause = testSubject.getCause();
        final String message = testSubject.getMessage();

        // THEN
        Assert.assertSame(expectedCause, cause);
        Assert.assertEquals(expectedMessage, message);
    }
}
