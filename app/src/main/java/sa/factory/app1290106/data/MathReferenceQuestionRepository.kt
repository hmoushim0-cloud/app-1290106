package sa.factory.app1290106.data

import sa.factory.app1290106.model.MathReferenceQuestion
import sa.factory.app1290106.model.MathSolutionStep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface MathReferenceQuestionRepository {
    fun getAvailableCatalog(): Flow<List<MathSolutionStep>>
    fun getActiveRequest(): Flow<MathReferenceQuestion?>
    suspend fun submitRequest(request: MathReferenceQuestion): Result<String>
}

class InMemoryMathReferenceQuestionRepository : MathReferenceQuestionRepository {
    private val _catalog = MutableStateFlow<List<MathSolutionStep>>(listOf(
        MathSolutionStep("1", "حزمة الأسئلة التعريفية والخصائص الجبرية", "توثيق 50 سؤالا في المفاهيم والخصائص مع الشواهد الدقيقة من أرقام الصفحات", 5000L, "assignment"),
        MathSolutionStep("2", "بنك المسائل التحليلية والخطوات المنهجية", "إعداد 50 مسألة متقدمة مدعمة بتسلسل خطوات الحل القابلة للتحقق الفوري", 9500L, "verified")
    ))
    private val _active = MutableStateFlow<MathReferenceQuestion?>(null)

    override fun getAvailableCatalog(): Flow<List<MathSolutionStep>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<MathReferenceQuestion?> = _active.asStateFlow()

    override suspend fun submitRequest(request: MathReferenceQuestion): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
