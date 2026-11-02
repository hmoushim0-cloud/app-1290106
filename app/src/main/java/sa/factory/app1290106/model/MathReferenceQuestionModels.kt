package sa.factory.app1290106.model

import kotlinx.serialization.Serializable

@Serializable
data class MathSolutionStep(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class MathReferenceQuestion(
    val requestId: String,
    val items: List<MathSolutionStep>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: MathReferenceQuestionStatus = MathReferenceQuestionStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class MathReferenceQuestionStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
