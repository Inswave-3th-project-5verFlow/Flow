/**
 * 칸반보드 드래그앤드롭 전용 JavaScript
 * 웹스퀘어와 연동 - 오직 드래그앤드롭만 담당
 */

console.log("=== 칸반 드래그앤드롭 JS 로드 시작 ===");


// 전역 변수
var draggedCard = null;

/**
 * 드래그앤드롭 설정 (웹스퀘어에서 호출)
 */
function setupKanbanDragDrop() {
    console.log("=== 드래그앤드롭 이벤트 설정 시작 ===");
    
    try {
        // 기존 이벤트 제거 (중복 방지)
        removeDragDropEvents();
        
        // 드래그 시작 이벤트
        document.addEventListener('dragstart', handleDragStart);
        
        // 드래그 종료 이벤트
        document.addEventListener('dragend', handleDragEnd);
        
        // 드래그 오버 이벤트 (드롭 허용)
        document.addEventListener('dragover', handleDragOver);
        
        // 드래그 엔터 이벤트 (드롭존 진입)
        document.addEventListener('dragenter', handleDragEnter);
        
        // 드래그 리브 이벤트 (드롭존 떠남)
        document.addEventListener('dragleave', handleDragLeave);
        
        // 드롭 이벤트 (실제 드롭)
        document.addEventListener('drop', handleDrop);
        
        console.log("=== ✅ 드래그앤드롭 이벤트 설정 완료 ===");
        
    } catch (error) {
        console.error("❌ 드래그앤드롭 설정 실패:", error);
    }
}

/**
 * 기존 이벤트 제거 (중복 방지)
 */
function removeDragDropEvents() {
    document.removeEventListener('dragstart', handleDragStart);
    document.removeEventListener('dragend', handleDragEnd);
    document.removeEventListener('dragover', handleDragOver);
    document.removeEventListener('dragenter', handleDragEnter);
    document.removeEventListener('dragleave', handleDragLeave);
    document.removeEventListener('drop', handleDrop);
}

/**
 * 드래그 시작 처리
 */
function handleDragStart(e) {
    // 칸반 카드인지 확인
    if (e.target.classList.contains('kanban-card')) {
        draggedCard = e.target;
        e.target.classList.add('dragging');
        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', e.target.outerHTML);
        
        var taskId = e.target.getAttribute('data-task-id');
        console.log("🟡 드래그 시작:", taskId);
    }
}

/**
 * 드래그 종료 처리
 */
function handleDragEnd(e) {
    if (e.target.classList.contains('kanban-card')) {
        e.target.classList.remove('dragging');
        draggedCard = null;
        
        // 모든 드래그 오버 효과 제거
        clearAllDragOverEffects();
        
        console.log("🔵 드래그 종료");
    }
}

/**
 * 드래그 오버 처리 (드롭 허용)
 */
function handleDragOver(e) {
    var dropZone = e.target.closest('.kanban-cards');
    if (dropZone && draggedCard) {
        e.preventDefault(); // 드롭 허용
        e.dataTransfer.dropEffect = 'move';
    }
}

/**
 * 드래그 엔터 처리 (드롭존 진입 시각 효과)
 */
function handleDragEnter(e) {
    var dropZone = e.target.closest('.kanban-cards');
    if (dropZone && draggedCard) {
        dropZone.classList.add('drag-over');
    }
}

/**
 * 드래그 리브 처리 (드롭존 떠날 때)
 */
function handleDragLeave(e) {
    var dropZone = e.target.closest('.kanban-cards');
    if (dropZone && !dropZone.contains(e.relatedTarget)) {
        dropZone.classList.remove('drag-over');
    }
}

/**
 * 드롭 처리 (실제 카드 이동)
 */
function handleDrop(e) {
    e.preventDefault();
    
    var dropZone = e.target.closest('.kanban-cards');
    
    if (dropZone && draggedCard) {
        dropZone.classList.remove('drag-over');
        
        // 카드를 새 위치로 물리적 이동
        dropZone.appendChild(draggedCard);
        
        // 상태 정보 추출
        var taskId = draggedCard.getAttribute('data-task-id');
        var oldStatus = draggedCard.getAttribute('data-status');
        var newStatus = getStatusFromContainer(dropZone);
        
        // 카드 속성 업데이트
        draggedCard.setAttribute('data-status', newStatus);
        
        console.log("🟢 드롭 완료:", taskId, oldStatus, "->", newStatus);
        
        // 상태가 실제로 변경된 경우에만 웹스퀘어 업데이트
        if (oldStatus !== newStatus) {
            updateTaskStatusInWebsquare(taskId, newStatus);
        }
    }
}

/**
 * 컨테이너 ID로부터 상태 추출
 */
function getStatusFromContainer(container) {
    var containerId = container.id;
    
    if (containerId === 'todo-cards') return 'todo';
    if (containerId === 'progress-cards') return 'progress';
    if (containerId === 'done-cards') return 'done';
    
    // 기본값
    return 'todo';
}

/**
 * 웹스퀘어 상태 업데이트 호출
 */
function updateTaskStatusInWebsquare(taskId, newStatus) {
    try {
        // 웹스퀘어 함수 호출
        if (typeof scwin !== 'undefined' && typeof scwin.updateTaskStatus === 'function') {
            scwin.updateTaskStatus(taskId, newStatus);
            console.log("✅ 웹스퀘어 상태 업데이트 호출 완료");
        } else {
            console.error("❌ 웹스퀘어 업데이트 함수를 찾을 수 없습니다.");
        }
    } catch (error) {
        console.error("❌ 웹스퀘어 업데이트 호출 실패:", error);
    }
}

/**
 * 모든 드래그 오버 효과 제거
 */
function clearAllDragOverEffects() {
    var dropZones = document.querySelectorAll('.kanban-cards');
    for (var i = 0; i < dropZones.length; i++) {
        dropZones[i].classList.remove('drag-over');
    }
}

/**
 * 드래그앤드롭 새로고침 (웹스퀘어에서 호출 가능)
 */
function refreshKanbanDragDrop() {
    console.log("🔄 드래그앤드롭 새로고침");
    setupKanbanDragDrop();
}

// 전역 함수로 등록
window.setupKanbanDragDrop = setupKanbanDragDrop;
window.refreshKanbanDragDrop = refreshKanbanDragDrop;

console.log("=== ✅ 칸반 드래그앤드롭 JS 로드 완료 ===");
console.log("등록된 함수:", typeof window.setupKanbanDragDrop);