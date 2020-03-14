
data class SourceLine(
    val label: String,
    val mnemonic: String,
    val operands: String,
    val comment: String) {
    companion object {
        fun toSourceLine(line: String): SourceLine {
            val label = line.substringBefore(':').let { candidate ->
                if (candidate.isEmpty()) throw IllegalArgumentException()
                if (candidate.length == line.length) "" else candidate
            }
            
            val mnemonic = ""
            val operands = ""
            val comment = ""
            return SourceLine(label, mnemonic, operands, comment)
        }

    }
}