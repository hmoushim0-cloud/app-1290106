package sa.factory.app1290106.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290106.data.MathReferenceQuestionRepository
import sa.factory.app1290106.model.MathReferenceQuestion
import sa.factory.app1290106.model.MathSolutionStep
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MathReferenceQuestionViewModel(
    private val repository: MathReferenceQuestionRepository
) : ViewModel() {
    val catalog: StateFlow<List<MathSolutionStep>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<MathReferenceQuestion?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: MathReferenceQuestion) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
