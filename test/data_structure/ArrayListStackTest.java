package data_structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListStackTest {
    private final ArrayListStack testStack = new ArrayListStack();
    private final ArrayListStack testStack1 = new ArrayListStack();

    @Test
    void push() {
        testStack.push("test1");
        assertEquals("test1", testStack.pop());
    }

    @Test
    void pop() {
        testStack.push("test1");
        assertEquals("test1", testStack.pop());
        assertNull(testStack1.pop());
    }

    @Test
    void isEmpty() {
        assertTrue(testStack1.isEmpty());
    }
}