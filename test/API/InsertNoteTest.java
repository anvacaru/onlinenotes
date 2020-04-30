/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import static org.junit.jupiter.api.Assertions.*;
import API.InsertNote;
import static API.InsertNote.randomString;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author mmunteanu
 */
public class InsertNoteTest {

    public InsertNoteTest() {
    }

    /**
     * Test of randomString method, of class InsertNote.
     */
    @Test
    public void testRandomString_CorrectLength() {
        int stringLength = 7;
        String randomString = randomString(stringLength);
        assertEquals(stringLength, randomString.length());
    }

    @Test
    public void testRandomString() {
        int stringLength = 7;
        String firstRandomString = randomString(stringLength);
        String secondRandomString = randomString(stringLength);
        assertThat(firstRandomString, not(equalTo(secondRandomString)));
    }
}
