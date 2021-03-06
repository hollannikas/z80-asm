
data class SourceLine(
    val label: String,
    val mnemonic: String,
    val operands: String,
    val comment: String) {
    companion object {
        fun toSourceLine(line: String): SourceLine {
            val label = getLabel(line)
            val containsLabel = label.isNotEmpty()
            val mnemonic =  getMnemonic(line, containsLabel)
            
            val operands = ""
            val comment = ""
            return SourceLine(label, mnemonic, operands, comment)
        }

        private fun getLabel(line: String) =
            line.substringBefore(':').let { candidate ->
                if (candidate.isEmpty()) throw IllegalArgumentException()
                if (candidate.length == line.length) "" else candidate 
            }
        
        private fun getMnemonic(line: String, containsLabel: Boolean) =
            if (containsLabel) line.split(" ")[1]
            else line.split(" ").first()
    }
}