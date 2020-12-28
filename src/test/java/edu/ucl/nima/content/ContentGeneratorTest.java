package edu.ucl.nima.content;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ContentGeneratorTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     */
    @Test
    public void shouldInstantiate() throws IOException
    {
        new ContentGenerator();
        assertTrue( true );
    }
}
