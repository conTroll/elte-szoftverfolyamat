package hu.szoftverfolyamat.enums;

import org.junit.Assert;
import org.junit.Test;

public class MessageStatusTest {

    @Test
    public void testEnumValue() {
        // then
        Assert.assertEquals(2, MessageStatus.values().length);
        Assert.assertEquals(MessageStatus.ACTIVE, MessageStatus.valueOf("ACTIVE"));
        Assert.assertEquals(MessageStatus.DELETED, MessageStatus.valueOf("DELETED"));
        Assert.assertEquals("ACTIVE", MessageStatus.ACTIVE.name());
        Assert.assertEquals("DELETED", MessageStatus.DELETED.name());
    }
}
