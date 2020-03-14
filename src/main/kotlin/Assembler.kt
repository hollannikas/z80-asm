
data class SourceLine(
    val label: String,
    val mnemonic: String,
    val operands: String,
    val comment: String) {
    companion object {
        fun toSourceLine(line: String): SourceLine {
            val label = getLabel(line)
            val mnemonic =  getMnemonic(line)
            
            val operands = ""
            val comment = ""
            return SourceLine(label, mnemonic, operands, comment)
        }

        private fun getLabel(line: String) =
            line.substringBefore(':').let { candidate ->
                if (candidate.isEmpty()) throw IllegalArgumentException()
                if (candidate.length == line.length) "" else candidate
        }
        
        private fun getMnemonic(line: String) =
            if (line.contains(':')) line.split(": ".toRegex())[1].split(" ").first()
            else line.split(" ").first()
    }
}