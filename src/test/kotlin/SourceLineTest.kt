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

internal class SourceLineOperandsTest {

    @Test
    fun `when one operand is given, it is parsed`() {
        val line = "PUSH DE"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(listOf("DE"), sourceLine.operands)
    }

    @Test
    fun `when two operands are given, they are parsed`() {
        val line = "ADD HL,SP"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(listOf("HL","SP"), sourceLine.operands)
    }

    @Test
    fun `when three operands are given, they are parsed`() {
        val line = "LD A,(IX+01)"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(listOf("A","(IX+01)"), sourceLine.operands)
    }
 
    @Test
    fun `when an operand is given after a label, it is parsed`() {
        val line = "label: CALL C,8000"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(listOf("C","8000"), sourceLine.operands)
    }

    @Test
    fun `when no operands are given, the list of operands is empty`() {
        val line = "SCF"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(emptyList<String>(), sourceLine.operands)
    }

    @Test
    fun `when no operands are given after a label, the list of operands is empty`() {
        val line = "label: RRCA"
        val sourceLine = SourceLine.toSourceLine(line)

        assertEquals(emptyList<String>(), sourceLine.operands)
    }
}