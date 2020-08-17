package com.cml.filestorage.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileExtensionTagAdderTest {
    private FileExtensionTagAdder fileExtensionTagAdder;

    public FileExtensionTagAdderTest() {
        this.fileExtensionTagAdder = new FileExtensionTagAdder();
    }

    @Test
    public void getExtensionTagOk() {
        String expected = "video";
        String actual = fileExtensionTagAdder.getExtensionTag("Jutland.docx.avi").get();
        assertEquals("Strings should be equal", expected, actual);
    }
}
