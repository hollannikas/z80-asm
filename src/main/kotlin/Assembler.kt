data class SourceLine(
    val label: String,
    val mnemonic: String,
    val operands: List<String>,
    val comment: String
) {
    companion object {
        fun toSourceLine(line: String): SourceLine {
            val label = getLabel(line)
            val containsLabel = label.isNotEmpty()
            val mnemonic = getMnemonic(line, containsLabel)

            val operands = getOperands(line, containsLabel)

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

        private fun getOperands(line: String, containsLabel: Boolean): List<String> {
            val splitLine = line.split(" ")
            return if (splitLine.size == 1
                || (containsLabel && splitLine.size == 2)) emptyList()
            else splitLine[if (containsLabel) 2 else 1]
                .split(",")
        }

    }
}
