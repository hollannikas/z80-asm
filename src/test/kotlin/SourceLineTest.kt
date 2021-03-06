import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class SourceLineLabelTest {

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

internal class SourceLineMnemonicTest {

    @Test
    fun `when a menomic is given, it is parsed`() {
        val line = "NOP"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals("NOP", sourceLine.mnemonic)
    }

    @Test
    fun `when a menomic with operands is given, it is parsed`() {
        val line = "LD A,H"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals("LD", sourceLine.mnemonic)
    }

    @Test
    fun `when a menomic is given after a label, it is parsed`() {
        val line = "label: NOP"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals("NOP", sourceLine.mnemonic)
    }

    @Test
    fun `when a menomic with operands is given after a label, it is parsed`() {
        val line = "label: LD A,H"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals("LD", sourceLine.mnemonic)
    }

    @Test
    fun `when no menemonic is given, an exception should be raised`() {
        val line = ""
        assertThrows(IllegalArgumentException::class.java) {
            SourceLine.toSourceLine(line)
        }
    }
}