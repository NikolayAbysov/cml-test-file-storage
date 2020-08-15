package com.cml.filestorage.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileExtensionTagAdderTest {

    public FileExtensionTagAdderTest() {
    }

    @Test
    public void getExtensionTagOk() {
        String expected = "document";
        String actual = FileExtensionTagAdder.getExtensionTag("Jutland.docx");
        assertEquals("Strings should be equal", expected, actual);
    }
}
