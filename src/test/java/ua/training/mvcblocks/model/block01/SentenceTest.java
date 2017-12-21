package ua.training.mvcblocks.model.block01;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SentenceTest {

    private Sentence sentence;

    @Before
    public void setUp() {
        sentence = new Sentence();
    }

    @Test
    public void testGetWords() {
        sentence.words = Arrays.asList("test1", "test2", "test3");

        List<String> result = sentence.getWords();
        assertEquals(sentence.words, result);
    }

    @Test
    public void testCreateFromString() {
        List<String> words = new ArrayList<>();

        sentence = new Sentence("Hello World!", " ");

        assertEquals(2, sentence.words.size());
        assertEquals("Hello", sentence.words.get(0));
        assertEquals("World!", sentence.words.get(1));
    }

    @Test
    public void testCreateFromStringWithRedundantSpace() {

        Sentence twoSpaceBetween = new Sentence("test1 test2  test3", " ");
        Sentence spaceBeyond = new Sentence(" hello world ", " ");
        Sentence manySpacesBeyond = new Sentence("      hello world     ", " ");

        assertEquals("test2", twoSpaceBetween.words.get(1));
        assertEquals("test3", twoSpaceBetween.words.get(2));
        assertEquals("hello", spaceBeyond.words.get(0));
        assertEquals("world", spaceBeyond.words.get(1));

        assertEquals("hello", manySpacesBeyond.words.get(0));
        assertEquals("world", manySpacesBeyond.words.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFormNull() {
        sentence = new Sentence(null, null);
    }

    @Test
    public void testGetWord() {
        sentence.words.add("test");

        String word = sentence.getWord(0);
        assertEquals("test", word);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWordOutOfBounds() {
        sentence.getWord(100); //sentence.words.visibleCount() is 0, actually
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndexWord() {
        sentence.getWord(-1);
    }

    @Test
    public void testAddWord() {
        sentence.addWord("test");
        assertEquals("test", sentence.words.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullWord() {
        sentence.addWord(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyWord() {
        sentence.addWord("");
    }

    @Test
    public void testRemoveWord() {
        sentence.words.add("test");
        boolean isRemoved = sentence.removeWord("test");

        assertTrue(isRemoved);
        assertFalse(sentence.words.contains("test"));
    }

    @Test
    public void testRemoveNull() {
        boolean isRemoved = sentence.removeWord(null);
        assertFalse(isRemoved);
    }

    @Test
    public void testRemoveNotContainedWord() {
        boolean isRemoved = sentence.removeWord("some weird word");
        assertFalse(isRemoved);
    }

    @Test
    public void testRemoveWordByIndex() {
        sentence.words.add("test");
        boolean isRemoved = sentence.removeWord(0);

        assertTrue(isRemoved && !sentence.words.contains("test"));
    }

    @Test
    public void testRemoveWithIndexOutOfBound() {
        boolean isRemoved = sentence.removeWord(-1);
        assertFalse(isRemoved);
    }

}