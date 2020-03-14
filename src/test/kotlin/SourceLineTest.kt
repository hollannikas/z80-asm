import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class SourceLineTest {
    
    @Test
    fun `when a label is given, it is parsed`() {
        val line = "loop: NOP"
        val sourceLine = SourceLine.toSourceLine(line)
        
        assertEquals("loop", sourceLine.label)
    }
    
    @Test
    fun `when no label is given, it remains empty`() {
        val line = "NOP"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals("", sourceLine.label)
    }

    @Test
    fun `when an empty label is given, an exception should be raised`() {
        val line = ": NOP"
        assertThrows(IllegalArgumentException::class.java) {
            SourceLine.toSourceLine(line)
        }
    }
}